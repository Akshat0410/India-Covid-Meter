package com.example.ap;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiResponse extends MainActivity {
    RequestQueue queue1 = Volley.newRequestQueue(this);
    String url = "https://api.covid19india.org/data.json";
    JSONArray arr=null;
    public JSONArray statewise() {
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            arr=response.getJSONArray("statewise");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                        new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error

                            }
                        });

        queue1.add(jsonObjectRequest);
        return arr;
    }
    public JSONArray cases_time_series() {
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            arr=response.getJSONArray("cases_time_series");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                        new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error

                            }
                        });

        queue1.add(jsonObjectRequest);
        return arr;
    }
    public JSONArray tested() {
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            arr=response.getJSONArray("tested");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                        new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error

                            }
                        });

        queue1.add(jsonObjectRequest);
        return arr;
    }
}




