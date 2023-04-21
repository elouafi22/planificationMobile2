package com.example.planificationmobile2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivityClient extends AppCompatActivity {

    private Button btn_mon_planning;
    private Button btn_reclamation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_client);

        btn_mon_planning=findViewById(R.id.btn_mon_planning);
        btn_reclamation=findViewById(R.id.btn_reclamation);

        btn_mon_planning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent nouveau = new Intent(getApplicationContext(),tacheActivity.class);
                startActivity(nouveau);
                finish();
            }
        });

        btn_reclamation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nouveau = new Intent(getApplicationContext(),reclamationActivity.class);
                startActivity(nouveau);
                finish();
            }
        });

    }
}