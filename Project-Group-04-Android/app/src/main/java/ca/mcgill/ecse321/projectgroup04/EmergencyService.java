package ca.mcgill.ecse321.projectgroup04;

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
    Spinner serviceSpinner;
    Spinner techSpinner;
    private String error ="";
    private List<String> serviceNames = new ArrayList<>();
    private ArrayAdapter<String> serviceAdapter;
    private List<String> techNames = new ArrayList<>();
    private ArrayAdapter<String> techniciansAdapter;


//    @Override
    protected void onCreate(@NonNull View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency_service_booking);
        refreshErrorMessage();

        getEmergencyServices(view);

//        //Services spinner
//        serviceSpinner = (Spinner) findViewById(R.id.Services);
//        serviceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, serviceNames);
//        serviceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        serviceSpinner.setAdapter(serviceAdapter);
//
//        //techs spinner
//        techSpinner = (Spinner) findViewById(R.id.Technicians);
//        techniciansAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, techNames);
//        techniciansAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        techSpinner.setAdapter(techniciansAdapter);

//        refreshLists(this.getCurrentFocus());
    }

    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }

//    public void refreshLists(View view) {
//        refreshList(serviceAdapter , serviceNames, "getAllEmergencyServices");
//        refreshList(techniciansAdapter, techNames, "getFieldTechnicians");
//    }
//
//    public void refreshList(final ArrayAdapter<String> adapter, final List<String> names, final String restFunctionName){
//        error = "";
////        final Spinner sp= (Spinner) findViewById(R.id.Services);
//        HttpUtils.get(restFunctionName, new RequestParams(), new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                names.clear();
//                names.add("Please select:");
//
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        names.add(response.getJSONObject(i).getString("name"));
//                    } catch (Exception e) {
//                        error += e.getMessage();
//                    }
//                    refreshErrorMessage();
//                }
//                adapter.notifyDataSetChanged();
//            }
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                try {
//                    error += errorResponse.get("message").toString();
//                } catch (JSONException e) {
//                    error += e.getMessage();
//                }
//                refreshErrorMessage();
//            }
//        });
//    }

    public void getEmergencyServices(View v){
        error="";

        HttpUtils.get("emergencyServices/", new RequestParams() ,new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {

                try {
                    Spinner emergencyServicesSpinner = findViewById(R.id.Services);
//                    Spinner serviceUpdateAppointmentSpinner = findViewById(R.id.newServiceUpdateAppointment);
                    String serviceArray[] = new String[response.length()];
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject service = response.getJSONObject(i);
                        serviceArray[i] = service.getString("name");
                    }
                    ArrayList<String> list = new ArrayList<String>(Arrays.asList(serviceArray));

                    serviceAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, list);
                    serviceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    emergencyServicesSpinner.setAdapter(serviceAdapter);
                    emergencyServicesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view,
                                                   int position, long id) {
                            Object item = adapterView.getItemAtPosition(position);
                            if (item != null) {
                                Toast.makeText(EmergencyService.this, item.toString(),
                                        Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(EmergencyService.this, "Selected",
                                    Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
//                    serviceUpdateAppointmentSpinner.setAdapter(appointmentAdapter);
//                    serviceUpdateAppointmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//                        @Override
//                        public void onItemSelected(AdapterView<?> adapterView, View view,
//                                                   int position, long id) {
//                            Object item = adapterView.getItemAtPosition(position);
//                            if (item != null) {
//                                Toast.makeText(EmergencyService.this, item.toString(),
//                                        Toast.LENGTH_SHORT).show();
//                            }
//                            Toast.makeText(EmergencyService.this, "Selected",
//                                    Toast.LENGTH_SHORT).show();
//
//                        }
//
//                        @Override
//                        public void onNothingSelected(AdapterView<?> adapterView) {
//
//                        }
//                    });


                } catch (JSONException e) {
                    error += e.getMessage();
                }
                // refreshErrorMessage();
                //  ((TextView) v.findViewById(R.id.newevent_name)).setText("");
            }
            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });

    }
}
