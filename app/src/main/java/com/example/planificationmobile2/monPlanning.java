package com.example.planificationmobile2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class monPlanning extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_planning);


         String url = "http://20.55.44.15:5000/materiel1";

        TextView tv = (TextView) findViewById(R.id.textView);
        JSONObject postData= new JSONObject();
        user u1 = user.gestInstance();
        try {
            postData.put("username",u1.getNom());
            postData.put("password",u1.getPassword());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest jonrequest =new JsonObjectRequest(Request.Method.POST, url, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String id = response.getString("id");
                    String nom = response.getString("nom");
                    String prenom =response.getString("prenom");
                    tv.setText(id+"::"+nom+"::"+prenom);
                } catch (JSONException e) {
                    tv.setText(e.getMessage());
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Afficher un message d'erreur à l'utilisateur
                System.out.println(error.getMessage());
                Toast.makeText(getApplicationContext(), "Erreur de connexion", Toast.LENGTH_LONG).show();

            }
        });



        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jonrequest);


    }

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

}

/*
    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            try {
                JSONObject jsonObject = response.getJSONObject(0);
                int userId = jsonObject.getInt("colonne1");
                String id = jsonObject.getString("colonne2");
                String title = jsonObject.getString("colonne3");
                tv.setText(userId+"\n"+id+"\n"+title+"\n");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            tv.setText("error "+error.getMessage());
            System.out.println(error.getMessage());
        }
    });

    //String url = "http://10.0.2.2:5000/materiel";

 */