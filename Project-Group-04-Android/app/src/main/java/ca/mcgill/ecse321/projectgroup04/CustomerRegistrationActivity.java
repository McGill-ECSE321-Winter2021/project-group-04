package ca.mcgill.ecse321.projectgroup04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CustomerRegistrationActivity extends AppCompatActivity {

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
    }

    public void openWelcomeActivity(){
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}