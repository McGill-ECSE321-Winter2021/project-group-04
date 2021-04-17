package ca.mcgill.ecse321.projectgroup04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class CustomerRegistrationActivity extends AppCompatActivity {

    String error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);

        Button registerButton = findViewById(R.id.register_btn4);
        Button loginButton = findViewById(R.id.log_in_btn4);
        Button cancelButton = findViewById(R.id.cancel_btn2);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWelcomeActivity();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLoginActivity();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    public void openWelcomeActivity(){
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }

    public void goToLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void registerUser(){
        final EditText eUserId = findViewById(R.id.et_username3);
        final EditText ePassword = findViewById(R.id.et_password3);

        final EditText eFirstName = findViewById(R.id.et_first_name);
        final EditText eLastName = findViewById(R.id.et_last_name);
        final EditText eAddress = findViewById(R.id.et_address);
        final EditText ePhoneNumber = findViewById(R.id.et_phone_number);
        final EditText eZipCode = findViewById(R.id.et_zip_code);
        final EditText eEmailAddress = findViewById(R.id.et_email_address);

        final EditText eModel = findViewById(R.id.et_model);
        final EditText eYear = findViewById(R.id.et_year);
        final EditText eColor = findViewById(R.id.et_color);

        String userId = eUserId.getText().toString();
        String password = ePassword.getText().toString();

        String firstName = eFirstName.getText().toString();
        String lastName = eLastName.getText().toString();
        String address = eAddress.getText().toString();
        String phoneNumber = ePhoneNumber.getText().toString();
        String zipCode = eZipCode.getText().toString();
        String emailAddress = eEmailAddress.getText().toString();

        String model = eModel.getText().toString();
        String year = eYear.getText().toString();
        String color = eColor.getText().toString();

        RequestParams requestParams = new RequestParams();

        requestParams.add("password", password);

        requestParams.add("firstName", firstName);
        requestParams.add("lastName", lastName);
        requestParams.add("address", address);
        requestParams.add("phoneNumber", phoneNumber);
        requestParams.add("zipCode", zipCode);
        requestParams.add("emailAddress", emailAddress);

        requestParams.add("modelNumber", model);
        requestParams.add("year", year);
        requestParams.add("color", color);

        String url = "/register/customer/" + userId + "/";
        System.out.println(url);

        error = "";

        HttpUtils.post(url, requestParams, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                Toast.makeText(CustomerRegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                goToCustomerHomePage();
                System.out.println("Registration Successful");
                refreshErrorMessage();
                eUserId.setText("");
                ePassword.setText("");

                eFirstName.setText("");
                eLastName.setText("");
                eAddress.setText("");
                ePhoneNumber.setText("");
                eZipCode.setText("");
                eEmailAddress.setText("");

                eModel.setText("");
                eYear.setText("");
                eColor.setText("");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse){
                try {
                    System.out.println(errorResponse);
                    ePassword.setText("");
                    error += errorResponse.get("message").toString();
                } catch (JSONException e){
                    error += e.getMessage();
                }

                refreshErrorMessage();
            }
        });

    }

    private void goToCustomerHomePage(){
        Intent intent = new Intent(this, MainActivity.class); // Might need to be changed
        startActivity(intent);
    }

    private void refreshErrorMessage(){

        TextView tvError = (TextView) findViewById(R.id.errorText1);
        tvError.setText(error);

        if (error == null || error.length() == 0){
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }
}