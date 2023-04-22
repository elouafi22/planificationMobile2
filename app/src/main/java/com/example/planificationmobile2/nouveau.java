package com.example.planificationmobile2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

public class nouveau extends AppCompatActivity {

    TextInputLayout ProjetName , DescriptionProjet;
    DatePicker dateDebut, dateFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_NoActionBar);
        setContentView(R.layout.activity_nouveau);

        ProjetName = (TextInputLayout) findViewById(R.id.ProjetName);
        DescriptionProjet = (TextInputLayout) findViewById(R.id.ProjetDescription);
        dateDebut = (DatePicker) findViewById(R.id.dateDebutPickerProjet);
        dateFin = (DatePicker) findViewById(R.id.dateFinPickerProjet);

        // set par default date debut on current date et date fin on current date + 20 days
        DatePicker currentDate = new DatePicker(this);
        dateDebut.updateDate(currentDate.getYear(), currentDate.getMonth(), currentDate.getDayOfMonth());
        dateFin.updateDate(currentDate.getYear(), currentDate.getMonth(), currentDate.getDayOfMonth() + 20);



    }
    /*
    // i don't need it i just need form of cree projet :
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.exemple, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemAnalyse:
                Intent nouveau = new Intent(getApplicationContext(),analyser.class);
                startActivity(nouveau);
                finish();
                return true;
            case R.id.itemMonPlanning:
                Intent nouveau2 = new Intent(getApplicationContext(),monPlanning.class);
                startActivity(nouveau2);
                finish();
                return true;
            case R.id.itemNouveau:
                Intent nouveau3 = new Intent(getApplicationContext(), com.example.planificationmobile2.nouveau.class);
                startActivity(nouveau3);
                finish();
                return true;
            case R.id.itemvisualisation:
                Intent nouveau4 = new Intent(getApplicationContext(),visualisation.class);
                startActivity(nouveau4);
                finish();
                return true;
            case R.id.itemDeconnection:
                Intent nouveau5 = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(nouveau5);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    */
    /**
     * validation du form pour creer un nouveau projet
     */
    private void creer() {
        if (!validateNameProjet() | !validateDescriptionProjet() | !validateDebutDate() | !validateFinDate()) {
            return;
        }
        // make json object with value of form and send it to the server flask

        SharedPreferences sharedPreferences = this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String session_key = sharedPreferences.getString("session_key", "");

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("session_id", session_key);
            jsonObject.put("name", ProjetName.getEditText().getText().toString().trim());
            jsonObject.put("description", DescriptionProjet.getEditText().getText().toString().trim());
            jsonObject.put("dateDeb", dateDebut.getYear() + "-" + dateDebut.getMonth() + "-" + dateDebut.getDayOfMonth());
            jsonObject.put("dateFin", dateFin.getYear() + "-" + dateFin.getMonth() + "-" + dateFin.getDayOfMonth());
        } catch (Exception e) {
            e.printStackTrace();
        }


        //String url = "http://192.168.1.105:5000/ajouterProjet";
        String url = "http://localhost:5000/ajouterProjet"; // <<-- need to be created
        //String url = "http://20.55.44.15:5000/ajouterProjet";
        // envoie de la requete au serveur flask

        JsonObjectRequest jonrequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String message = response.getString("message");
                    if (message.equals("success")) {
                        Intent intent = new Intent(getApplicationContext(), monPlanning.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Erreur lors de la création du projet", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            /**
             * @param error
             */
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Erreur lors de la création du projet", Toast.LENGTH_SHORT).show();
            }

        });


    }


    /**
     * annuler la creation du projet
     */
    private void annuler() {
        Intent intent = new Intent(getApplicationContext(), monPlanning.class);
        startActivity(intent);
        finish();
    }

    private boolean validateNameProjet() {
        String val = ProjetName.getEditText().getText().toString().trim();
        String checkspaces = "Aw{1,20}z";
        if (val.isEmpty()) {
            ProjetName.setError("Le champ ne peut pas être vide");
            return false;
        } else if (val.length() > 20) {
            ProjetName.setError("le nom est trop grand !");
            return false;
        } else if (!val.matches(checkspaces)) {
            ProjetName.setError("Aucun espace blanc n'est autorisé !");
            return false;
        } else {
            ProjetName.setError(null);
            ProjetName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateDescriptionProjet() {
        String val = ProjetName.getEditText().getText().toString().trim();
        String checkspaces = "Aw{1,20}z";
        if (val.isEmpty()) {
            ProjetName.setError("Le champ ne peut pas être vide");
            return false;
        } else if (!val.matches(checkspaces)) {
            ProjetName.setError("Aucun espace blanc n'est autorisé !");
            return false;
        } else {
            ProjetName.setError(null);
            ProjetName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateDebutDate() {

        DatePicker currentDate = new DatePicker(this);
        int currentDay = currentDate.getDayOfMonth();
        int currentMonth = currentDate.getMonth();
        int currentYear = currentDate.getYear();

        int day = dateDebut.getDayOfMonth();
        int month = dateDebut.getMonth();
        int year = dateDebut.getYear();

        if (year < currentYear) {
            return false;
        } else if (year == currentYear) {
            if (month < currentMonth) {
                return false;
            } else if (month == currentMonth) {
                if (day < currentDay) {
                    return false;
                } else {
                    return true;
                }

            }

        } else {
            return true;
        }
        return false; // TODO: 2021-05-03
    }

    private boolean validateFinDate() {
        if (dateFin.getYear() < dateDebut.getYear()) {
            return false;
        } else if (dateFin.getYear() == dateDebut.getYear()) {
            if (dateFin.getMonth() < dateDebut.getMonth()) {
                return false;
            } else if (dateFin.getMonth() == dateDebut.getMonth()) {
                if (dateFin.getDayOfMonth() < dateDebut.getDayOfMonth()) {
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            return true;
        }
        return false; // TODO: 2021-05-03
    }

}