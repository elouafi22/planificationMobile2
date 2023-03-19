package com.example.planificationmobile2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class home extends AppCompatActivity {

    private ImageView monplannig,nouveau,visualisation,analyse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.monplannig=(ImageView) findViewById(R.id.monplaging);
        this.nouveau=(ImageView) findViewById(R.id.nouveau);
        this.visualisation=(ImageView) findViewById(R.id.visualisation);
        this.analyse=(ImageView) findViewById(R.id.analyse);

        this.monplannig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nouveau = new Intent(getApplicationContext(),monPlanning.class);
                startActivity(nouveau);
                finish();
            }
        });
    }
}