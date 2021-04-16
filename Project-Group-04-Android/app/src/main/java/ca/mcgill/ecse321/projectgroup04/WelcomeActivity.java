package ca.mcgill.ecse321.projectgroup04;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button loginButton1 = findViewById(R.id.log_in_btn1);
        Button registerButton1 = findViewById(R.id.register_btn1);

        loginButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openLoginActivity();
            }
        });

        registerButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerRegistrationActivity();
            }
        });

    }

    public void openLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

    public void openCustomerRegistrationActivity(){
        Intent intent = new Intent(this, CustomerRegistrationActivity.class);
        startActivity(intent);
    }
}