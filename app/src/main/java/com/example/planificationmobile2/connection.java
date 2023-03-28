package com.example.planificationmobile2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class connection {
    String url1="http://20.55.44.15:5000/materiel";
    private static String url = "http://20.55.44.15:5000/authentification";

    public static void get_connection(Context context, String username ,String password){
        JSONObject postData = new JSONObject();
        try {
            postData.put("username", username);
            postData.put("password", password);
        } catch (
                JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, postData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String status = response.getString("status");
                            if (status.equals("success")) {
                                // Connexion réussie
                                Toast.makeText(context, "Connexion réussie", Toast.LENGTH_SHORT).show();
                                // stoker les inforamtion de l'utilisateur
                                user u1= user.gestInstance();
                                u1.setNom(username);
                                u1.setPassword(password);

                                //changer l'activite
                                Intent nouveau = new Intent(context.getApplicationContext(),home.class);
                                context.startActivity(nouveau);
                                ((Activity)context).finish();
                            } else {
                                // Connexion échouée
                                String message = response.getString("message");
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Erreur de connexion
                Toast.makeText(context,error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);

    }

}
