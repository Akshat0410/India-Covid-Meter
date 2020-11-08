package com.example.ap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

import static java.lang.Double.parseDouble;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String stateposition="com.example.ap.data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "https://api.covid19india.org/data.json";
        RequestQueue queue = Volley.newRequestQueue(this);
        final TextView tc = (TextView) findViewById(R.id.tcdata);
        final TextView ac = (TextView) findViewById(R.id.acdata);
        final TextView rc = (TextView) findViewById(R.id.rcdata);
        final TextView dc = (TextView) findViewById(R.id.ddata);
        final TextView td = (TextView) findViewById(R.id.utdata);
        final TextView nc= (TextView) findViewById(R.id.ncdata);
        final TextView dd = (TextView) findViewById(R.id.dtdata);
        final TextView rt = (TextView) findViewById(R.id.rtdata);
        final TextView tdd = (TextView) findViewById(R.id.ttdata);
        final TextView stt = (TextView) findViewById(R.id.stdata);
        final TextView ptd = (TextView) findViewById(R.id.ptdata);
        Spinner sp=(Spinner)findViewById(R.id.spinner3);
        sp.setOnItemSelectedListener(this);



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray statearray = null;
                        try {
                            statearray = response.getJSONArray("statewise");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONObject india = null;
                        try {
                            india = statearray.getJSONObject(0);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            tc.setText(india.getString("confirmed"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            ac.setText(india.getString("active"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            rc.setText(india.getString("recovered"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            dc.setText(india.getString("deaths"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            td.setText(india.getString("lastupdatedtime"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONArray casewise= null;
                        try {
                            casewise = response.getJSONArray("cases_time_series");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONObject data= null;
                        try {
                            int len=casewise.length();
                            data = casewise.getJSONObject(len-1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            nc.setText(" + "+data.getString("dailyconfirmed"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            dd.setText(" + "+data.getString("dailydeceased"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            rt.setText(" + "+data.getString("dailyrecovered"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONArray tested= null;
                        try {
                            tested = response.getJSONArray("tested");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONObject test=null;
                        try {

                             test=tested.getJSONObject(tested.length()-1);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            tdd.setText(test.getString(("totalsamplestested")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            stt.setText(test.getString(("samplereportedtoday")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        double prate;
                        double tt=0.0;
                        try {
                            tt = parseDouble((data.getString("dailyconfirmed")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        double tdt=0.0;
                        try {
                            tdt = parseDouble(test.getString("samplereportedtoday"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        prate=(tt/tdt)*100;
                        String res=String.valueOf(prate);
                        ptd.setText(res.substring(0,4));




                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        queue.add(jsonObjectRequest);
    }



    @Override
    public void onItemSelected (AdapterView < ? > parent, View view,int position, long id){
        Toast.makeText(this,"item clicked "+position,Toast.LENGTH_SHORT ).show();
        Intent intent = new Intent(this, statedata.class);
        intent.putExtra(stateposition,position);
        startActivity(intent);


    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}







