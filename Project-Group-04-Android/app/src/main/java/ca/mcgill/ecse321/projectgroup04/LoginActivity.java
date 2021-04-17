package ca.mcgill.ecse321.projectgroup04;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class LoginActivity extends AppCompatActivity {

    String error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button loginButton1 = findViewById(R.id.log_in_btn1);
        Button registerButton1 = findViewById(R.id.register_btn1);
        Button cancelButton1 = findViewById(R.id.cancel_btn3);


        cancelButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWelcomeActivity();
            }
        });

        loginButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        registerButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegistrationActivity();
            }
        });
    }

    public void openWelcomeActivity(){
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }

    public void openRegistrationActivity(){
        Intent intent = new Intent (this, CustomerRegistrationActivity.class);
        startActivity(intent);
    }

    public void loginUser(){

        final EditText eUserId = findViewById(R.id.et_username);
        final EditText ePassword = findViewById(R.id.et_password);

        String userId = eUserId.getText().toString();
        String password = ePassword.getText().toString();

        RequestParams requestParams = new RequestParams();
        requestParams.add("password", password);

        String url = "/login/" + userId;

        System.out.println(url);

        error = "";

        if (userId.isEmpty() || password.isEmpty()){
            Toast.makeText(LoginActivity.this, "Please enter your login credentials!", Toast.LENGTH_SHORT).show();
        }

        HttpUtils.post(url, requestParams, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){

                goToCustomerHomePage();
                System.out.println("Login Successful");
                refreshErrorMessage();

                eUserId.setText("");
                ePassword.setText("");
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