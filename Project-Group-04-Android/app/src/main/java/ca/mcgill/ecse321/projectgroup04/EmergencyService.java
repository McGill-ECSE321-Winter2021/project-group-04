package ca.mcgill.ecse321.projectgroup04;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class EmergencyService extends AppCompatActivity {

    private String error = "";
    private List<String> serviceNames = new ArrayList<>();
    private ArrayAdapter<String> serviceAdapter;
    private List<String> techNames = new ArrayList<>();
    private ArrayAdapter<String> techAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency_service);

        refreshErrorMessage();

        serviceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, serviceNames);
        techAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, techNames);

        getFieldTechnicians();
        getEmergencyServices();

        Button logoutButton = findViewById(R.id.logOut2);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        //requestEmergencyService(this.getCurrentFocus());

    }

    /**
     * @author Cesar Lahoud
     * This method adds all the available emergency services to the spinner
     */
    public void getEmergencyServices(){
        error="";
        HttpUtils.get("/emergencyServices/", new RequestParams() ,new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                System.out.println("test");
                serviceNames.clear();
                serviceNames.add("Please select...");
                for( int i = 0; i < response.length(); i++) {
                    try {
                        serviceNames.add(response.getJSONObject(i).getString("name"));
                    } catch (Exception e) {
                        error += e.getMessage();
                    }
                    refreshErrorMessage();
                }
                Spinner serviceSpinner = (Spinner) findViewById(R.id.em_service);

                serviceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                serviceSpinner.setAdapter(serviceAdapter);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                System.out.println(errorResponse);
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }

        });

    }

    public void goToBookAppointment(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


    /**
     * @author Cesar Lahoud
     * This method adds all the available field technicians to the spinner
     */
    public void getFieldTechnicians(){
        error="";
        HttpUtils.get("/fieldTechnician/", new RequestParams() ,new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                System.out.println("test");
                techNames.clear();
                techNames.add("Please select...");
                for( int i = 0; i < response.length(); i++) {
                    try {
                        techNames.add(response.getJSONObject(i).getString("name"));
                    } catch (Exception e) {
                        error += e.getMessage();
                    }
                    refreshErrorMessage();
                }

                Spinner techSpinner = (Spinner) findViewById(R.id.field_techs);

                techAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                techSpinner.setAdapter(techAdapter);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                System.out.println(errorResponse);
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }

        });

    }

    /**
     * @author Artus Julliard
     * @param name
     * @return the field technician Id
     * This method returns the field technician's Id
     */
    public int getFieldTechnicianIdByName(String name) {
        final int[] technicianId = new int[1];
        HttpUtils.post("fieldTechnicians/" + name, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                try {
                    technicianId[0] = Integer.parseInt(response.getJSONObject(0).getString("technicianId"));
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }

        });
        return technicianId[0];
    }

    /**
     * @Author Artus Julliard
     * @param view
     * This method books an emergency service
     */
    public void requestEmergencyService(View view){
        final Spinner em_service = findViewById(R.id.em_service);
        final Spinner field_techs = findViewById(R.id.field_techs);

        final String[] userId = {""};

        RequestParams rp = new RequestParams();
        TextView tv = (TextView) findViewById(R.id.location);
        String location = tv.getText().toString();

        String nameService = em_service.getSelectedItem().toString();
        String fieldTech = field_techs.getSelectedItem().toString();
        int fieldTechId = getFieldTechnicianIdByName(fieldTech);

        rp.add("serviceName", nameService);
        rp.add("Location", location);
        rp.put("fieldTechnicianId", fieldTechId);


        HttpUtils.get("/login/currentCustomer", new RequestParams(), new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response){
                try {
                    userId[0] =response.getJSONObject(0).getString("userID");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });

        HttpUtils.post("/book/emergencyService/" + userId[0], rp, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try{
                    System.out.println("success");
                    super.onSuccess(statusCode, headers, response);
                    JSONObject serverResp = new JSONObject(response.toString());
                }catch(JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    System.out.println(errorResponse);
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
    }

    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) findViewById(R.id.emergencyError);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }

    public void logoutUser(){
        error = "";
        HttpUtils.post("/logout/", new RequestParams(), new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                goToWelcomePage();
                System.out.println("Logged Out");
                refreshErrorMessage();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse){
                try {
                    System.out.println(errorResponse);
                    error += errorResponse.get("message").toString();
                } catch (JSONException e){
                    error += e.getMessage();
                }

                refreshErrorMessage();
            }
        });
    }

    public void goToWelcomePage(){
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }

}
