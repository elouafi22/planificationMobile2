package com.example.planificationmobile2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.planificationmobile2.traitementPourProjet.DataRecievedListener;

import traitement_pour_lesTaches.afficherTache;
import traitement_pour_lesTaches.projetRf;

public class tacheActivity extends AppCompatActivity implements DataRecievedListener {
    private afficherTache lestache;
    private TextView nomproj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_NoActionBar);
        setContentView(R.layout.activity_tache);

        String url = "http://192.168.1.103:5000/affichertachechef";

        nomproj = findViewById(R.id.nomprojTache);


        this.lestache =new afficherTache(this);
        this.lestache.RecuperLesDonneJson(this,url);

    }

    @Override
    public void onDataRecieved() {

        user usercourant =user.gestInstance();
        if(usercourant.getVerifierchef()==1){
            String nomprojet =getIntent().getStringExtra("nomproj");
            nomproj.setText(nomprojet);
        }else{
            projetRf projetcourant =projetRf.getInstance();
            nomproj.setText(projetcourant.getNomProj());
        }

        ListView listetache =findViewById(R.id.tache);
        this.lestache.afficherEnsembletache(this,listetache);

    }
}

