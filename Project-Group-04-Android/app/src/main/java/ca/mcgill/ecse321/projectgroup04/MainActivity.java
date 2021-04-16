package ca.mcgill.ecse321.projectgroup04;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import ca.mcgill.ecse321.projectgroup04.HttpUtils;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private String error = null;
    private List<String> serviceNames = new ArrayList<>();
    private ArrayAdapter<String> serviceAdapter;
    private List<String> technicianNames = new ArrayList<>();
    private ArrayAdapter<String> techniciansAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        refreshErrorMessage();
        Spinner serviceSpinner = (Spinner) findViewById(R.id.servicespinner);
        Spinner technicianSpinner = (Spinner) findViewById(R.id.technicianspinner);
        Spinner garageSpotSpinner = (Spinner) findViewById(R.id.spotspinner);

        ArrayAdapter<CharSequence> spotAdapter = ArrayAdapter.createFromResource(this,R.array.spotspinner,android.R.layout.simple_spinner_item);
        spotAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        garageSpotSpinner.setAdapter(spotAdapter);
// Get initial content for spinners
        getBookableServices(this.getCurrentFocus());
        getGarageTechnicians(this.getCurrentFocus());

        serviceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, serviceNames);
        serviceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        serviceSpinner.setAdapter(serviceAdapter);

        techniciansAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, technicianNames);
        techniciansAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        technicianSpinner.setAdapter(techniciansAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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



    private Bundle getTimeFromLabel(String text) {
        Bundle rtn = new Bundle();
        String comps[] = text.toString().split(":");
        int hour = 12;
        int minute = 0;

        if (comps.length == 2) {
            hour = Integer.parseInt(comps[0]);
            minute = Integer.parseInt(comps[1]);
        }

        rtn.putInt("hour", hour);
        rtn.putInt("minute", minute);

        return rtn;
    }

    private Bundle getDateFromLabel(String text) {
        Bundle rtn = new Bundle();
        String comps[] = text.toString().split("-");
        int day = 1;
        int month = 1;
        int year = 1;

        if (comps.length == 3) {
            day = Integer.parseInt(comps[0]);
            month = Integer.parseInt(comps[1]);
            year = Integer.parseInt(comps[2]);
        }

        rtn.putInt("day", day);
        rtn.putInt("month", month-1);
        rtn.putInt("year", year);

        return rtn;
    }

    public void showTimePickerDialog(View v) {
        TextView tf = (TextView) v;
        Bundle args = getTimeFromLabel(tf.getText().toString());
        args.putInt("id", v.getId());

        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v) {
        TextView tf = (TextView) v;
        Bundle args = getDateFromLabel(tf.getText().toString());
        args.putInt("id", v.getId());

        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void setTime(int id, int h, int m) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(String.format("%02d:%02d", h, m));
    }

    public void setDate(int id, int d, int m, int y) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(String.format("%02d-%02d-%04d", d, m + 1, y));
    }

    public void getBookableServices(View view){
        getBookableServicesBackend(serviceAdapter,serviceNames,"bookableServices");
    }
    public void getGarageTechnicians(View view){
        getGarageTechniciansBackend(techniciansAdapter,technicianNames,"garageTechnicians");
    }
    private void getBookableServicesBackend(final ArrayAdapter<String> serviceAdapter, final List<String> bookableServices, final String restFunctionName) {
        System.out.println("Getting services");
        HttpUtils.get(restFunctionName, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                System.out.println("Got services");
                bookableServices.clear();
                bookableServices.add("Please select...");
                for( int i = 0; i < response.length(); i++){
                    try {
                        bookableServices.add(response.getJSONObject(i).getString("name"));
                    } catch (Exception e) {
                        error += e.getMessage();
                    }
                    refreshErrorMessage();
                }
                serviceAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                System.out.println("Failed services");
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
    }

    private void getGarageTechniciansBackend(final ArrayAdapter<String> techniciansAdapter, final List<String> technicians, final String restFunctionName) {
        HttpUtils.get(restFunctionName, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                technicians.clear();
                technicians.add("Please select...");
                for( int i = 0; i < response.length(); i++){
                    try {
                        technicians.add(response.getJSONObject(i).getString("name"));
                    } catch (Exception e) {
                        error += e.getMessage();
                    }
                    refreshErrorMessage();
                }
                serviceAdapter.notifyDataSetChanged();
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

}