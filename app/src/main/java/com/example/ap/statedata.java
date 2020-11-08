package com.example.ap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

public class statedata extends AppCompatActivity {
    public static final String graphcoordinate="com.example.ap.good";
   public static  final ArrayList<String> y_axis=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statedata);
        Intent intent = getIntent();


        String sp = String.valueOf(intent.getIntExtra(MainActivity.stateposition, 0));
        final int i = Integer.parseInt(sp);
        final TextView tcs = (TextView) findViewById(R.id.tcsdata);
        final TextView rcs = (TextView) findViewById(R.id.rcsdata);
        final TextView acs = (TextView) findViewById(R.id.acsdata);
        final TextView dcs = (TextView) findViewById(R.id.dsdata);
        RequestQueue queue1 = Volley.newRequestQueue(this);
        String url = "https://api.covid19india.org/data.json";
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray jr = null;
                        try {
                            jr = response.getJSONArray("statewise");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONObject jo = null;
                        try {
                            jo = jr.getJSONObject(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            tcs.setText(jo.getString(("confirmed")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            rcs.setText(jo.getString("recovered"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            acs.setText(jo.getString("active"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            dcs.setText(jo.getString("deaths"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//                        JSONArray gra=null;
//                        try {
//                            gra=response.getJSONArray("cases_time_series");
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        String s="";
//                        int l=gra.length();
//                        JSONObject grap=null;
//                        for(int i=0;i<l;i++)
//                        {
//                            try {
//                                grap=gra.getJSONObject(i);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                            try {
//
//                                s= grap.getString("totalconfirmed");
//                                y_axis.add(s);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }

                    }
                },
                        new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error

                            }
                        });

        queue1.add(jsonObjectRequest);

    }
        public void showgraph (View view){
//            Toast.makeText(this,"item clicked "+y_axis, Toast.LENGTH_SHORT ).show();
           Intent graphIntent = new Intent(this, Graphs.class);
//           graphIntent.putStringArrayListExtra(graphcoordinate,y_axis);
           startActivity(graphIntent);



        }
    }


