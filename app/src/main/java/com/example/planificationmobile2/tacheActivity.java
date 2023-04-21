package com.example.planificationmobile2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.planificationmobile2.traitementPourProjet.DataRecievedListener;

import traitement_pour_lesTaches.afficherTache;

public class tacheActivity extends AppCompatActivity implements DataRecievedListener {
    private afficherTache lestache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tache);

        String url = "http://192.168.1.105:5000/affichertachechef";

        TextView nomproj = findViewById(R.id.nomprojTache);
        String nomprojet =getIntent().getStringExtra("nomproj");
        System.out.println("-------------------------------------"+nomprojet);
        nomproj.setText(nomprojet);

        this.lestache =new afficherTache(this);
        this.lestache.RecuperLesDonneJson(this,url);

    }

    @Override
    public void onDataRecieved() {
        ListView listetache =findViewById(R.id.tache);
        this.lestache.afficherEnsembletache(this,listetache);
    }
}

