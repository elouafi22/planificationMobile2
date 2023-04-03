package com.example.planificationmobile2.traitementPourProjet;

import android.content.Context;
import android.widget.ListView;
import android.widget.Toast;

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

        JsonArrayRequest jonrequest =new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        ensembleProjet.add(new projet(obj.getString("idprojet"),obj.getString("nomprojet"),obj.getString("description"),obj.getString("dateDebut"),obj.getString("chefProjet"),obj.getString("etatProjet")));
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
