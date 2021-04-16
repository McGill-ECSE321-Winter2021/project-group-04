package ca.mcgill.ecse321.projectgroup04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_select);

        Button customerRegisterBtn1 = findViewById(R.id.customer_btn);
        Button employeeRegisterBtn1 = findViewById(R.id.employee_btn);
        Button cancelBtn1 = findViewById(R.id.cancel_btn1);

        customerRegisterBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerRegistrationActivity();
            }
        });

        employeeRegisterBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmployeeRegistrationActivity();
            }
        });

        cancelBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWelcomeActivity();
            }
        });

    }

    public void openCustomerRegistrationActivity(){
        Intent intent = new Intent(this, CustomerRegistrationActivity.class);
        startActivity(intent);
    }

    public void openEmployeeRegistrationActivity(){
        Intent intent = new Intent(this, EmployeeRegistrationActivity.class);
        startActivity(intent);
    }

    public void openWelcomeActivity(){
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}