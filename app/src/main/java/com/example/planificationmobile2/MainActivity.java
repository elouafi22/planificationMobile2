package com.example.planificationmobile2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.StrictMode;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check utilisateur et mot de passe dans la base de données

        // si l'utilisateur et le mot de passe sont corrects
        TextView username =(TextView) findViewById(R.id.username);
        TextView password =(TextView) findViewById(R.id.password);
        Button loginbtn = (Button) findViewById(R.id.loginbtn);

        // pour le test de connexion a la base de donne
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        //test connection user and password


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                baseDeDonne Session = new baseDeDonne(username.getText().toString(),password.getText().toString());
                try{
                    Session.connexion();
                    Toast.makeText(MainActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                    Intent nouveau = new Intent(getApplicationContext(),home.class);
                    startActivity(nouveau);
                    finish();

                }
                catch(ClassNotFoundException | SQLException e) {
                    Toast.makeText(MainActivity.this,"LOGIN FAILED !!!",Toast.LENGTH_SHORT).show();
                }*/

                ////////////// se code est temmporairement tanque la base de donne ne travaille pas
                Intent nouveau = new Intent(getApplicationContext(),home.class);
                startActivity(nouveau);
                finish();
            }

        });


    }
}