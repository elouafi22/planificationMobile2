package com.example.planificationmobile2.traitementPourProjet;

import android.content.Context;
import android.widget.ListView;
import android.widget.Toast;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class afficherProjet {
    private  ArrayList<projet> ensembleProjet;
    private DataRecievedListener listener;


    public afficherProjet(DataRecievedListener listener) {
        this.ensembleProjet= new ArrayList<>();
        this.listener=listener;

    }


    public void RecuperLesDonneJson(Context context, String url){

        // recuperer la valeur de la cle et onvoyee dans la requete


        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String session_key = sharedPreferences.getString("session_key", "");
        System.out.println(session_key);
        JSONArray donnerenvoyer= new JSONArray();
        JSONObject postData = new JSONObject();
        try {
            postData.put("session_id",session_key);
            donnerenvoyer.put(postData);
        } catch (
                JSONException e) {
            throw new RuntimeException(e);
        }


        /// envoier la requete vers le serveur

        JsonArrayRequest jonrequest =new JsonArrayRequest(Request.Method.POST, url,donnerenvoyer, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        //  ensembleProjet.add(new projet(obj.getString("idprojet"),obj.getString("nomprojet"),obj.getString("description"),obj.getString("dateDebut"),obj.getString("chefProjet"),obj.getString("etatProjet")));
                        ensembleProjet.add(new projet(obj.getString("IDPROJ"),obj.getString("NOMPROJ"),obj.getString("DATEDEB"),obj.getString("DESCRIPTION"),obj.getString("PN"),obj.getString("ETATPROJ")));
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                listener.onDataRecieved();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getMessage();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jonrequest);

    }

    public void afficherEnsembleProjet(Context context,ListView toutProjet ) {
        if (ensembleProjet.size() > 0) {
            toutProjet.setAdapter(new projetAdapeter(context, ensembleProjet));
        } else {
            // Affichage d'un message si la liste est vide
            Toast.makeText(context, "Aucun projet n'a été trouvé", Toast.LENGTH_SHORT).show();
        }



    }

}

