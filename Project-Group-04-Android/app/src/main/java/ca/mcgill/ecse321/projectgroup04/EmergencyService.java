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

    private String error = "";
    private List<String> serviceNames = new ArrayList<>();
    private ArrayAdapter<String> serviceAdapter;
    private List<String> techNames = new ArrayList<>();
    private ArrayAdapter<String> techAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);

        refreshErrorMessage();


        Spinner personSpinner = (Spinner) findViewById(R.id.em_service);
        Spinner eventSpinner = (Spinner) findViewById(R.id.field_techs);

        serviceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, serviceNames);
        serviceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        personSpinner.setAdapter(serviceAdapter);

        techAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, techNames);
        techAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eventSpinner.setAdapter(techAdapter);

        // Get initial content for spinners
        refreshLists(this.getCurrentFocus());


    }

    private void refreshLists(View currentFocus) {
        refreshList(serviceAdapter , serviceNames, "emergencyServices");
        refreshList(techAdapter, techNames, "fieldTechnicians");
    }

    public  void refreshList(final ArrayAdapter<String> adapter, final List<String> names, final String restFunctionName){
        HttpUtils.get(restFunctionName, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                names.clear();
                names.add("Please select");
                for( int i = 0; i < response.length(); i++){
                    try {
                        names.add(response.getJSONObject(i).getString("name"));
                    } catch (Exception e) {
                        error += e.getMessage();
                    }
                    refreshErrorMessage();
                }
                adapter.notifyDataSetChanged();
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
    }

    public void getEmergencyServices(){
        error="";
        final Spinner sp = (Spinner) findViewById(R.id.em_service);
        HttpUtils.get("emergencyServices/", new RequestParams() ,new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
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
