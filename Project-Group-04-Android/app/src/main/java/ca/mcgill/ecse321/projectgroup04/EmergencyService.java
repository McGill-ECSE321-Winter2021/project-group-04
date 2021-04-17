package ca.mcgill.ecse321.projectgroup04;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

    }

    public void getEmergencyServices(){
        error="";
        HttpUtils.get("/emergencyServices/", new RequestParams() ,new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                System.out.println("test");
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
    public void getFieldTechnicians(){
        error="";
        HttpUtils.get("/fieldTechnician/", new RequestParams() ,new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                System.out.println("test");
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



}
