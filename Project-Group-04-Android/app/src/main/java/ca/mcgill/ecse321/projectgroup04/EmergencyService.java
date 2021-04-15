package ca.mcgill.ecse321.projectgroup04;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class EmergencyService extends AppCompatActivity {
    Spinner serviceSpinner;
    Spinner techSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency_service_booking);

        //Services spinner
        serviceSpinner = findViewById(R.id.Services);
        List<String> services = new ArrayList<>();
        List<String> backendServices = new ArrayList<>();
        services.add(0, "Services");
        for(int i=0;i<backendServices.size();i++){
            String string = backendServices.get(i);
            services.add(i+1, string);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, services);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        serviceSpinner.setAdapter(arrayAdapter);

        //techs spinner
        techSpinner = findViewById(R.id.Services);
        List<String> techs = new ArrayList<>();
        List<String> backendTechs = new ArrayList<>();
        techs.add(0, "Technicians");
        for(int i=0;i<backendTechs.size();i++){
            String string = backendTechs.get(i);
            services.add(i+1, string);
        }
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, techs);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        techSpinner.setAdapter(arrayAdapter);
    }

}
