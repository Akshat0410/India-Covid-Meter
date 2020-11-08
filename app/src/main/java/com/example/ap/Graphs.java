package com.example.ap;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.GREEN;

public class Graphs extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);

        RequestQueue queue1 = Volley.newRequestQueue(this);
        String url = "https://api.covid19india.org/data.json";
        JSONArray arr = null;
        ArrayList<String> totaldeath = new ArrayList<>();
        ArrayList<String> totalrecovered = new ArrayList<>();


        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray graphical = null;
                        JSONObject statedata = null;
                        int k;
                        try {
                            graphical = response.getJSONArray("cases_time_series");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        GraphView graph1 = (GraphView) findViewById(R.id.graph12);
                        GraphView graph2 = (GraphView) findViewById(R.id.graph13);
                        GraphView graph3 = (GraphView) findViewById(R.id.graph14);
                        GraphView graph = (GraphView) findViewById(R.id.graph11);
                        LineGraphSeries<DataPoint> series2;
                        LineGraphSeries<DataPoint> series3;
                        LineGraphSeries<DataPoint>series4;
                        LineGraphSeries<DataPoint> series1;
                        double max1 = 0;
                        double datadeceased = 0;
                        double totalcase=0;

                        //Graph for total confirmed
                        for(k=0;k<graphical.length();k++)
                        {
                            try {
                                statedata=graphical.getJSONObject(k);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                totalcase=Integer.parseInt(statedata.getString("totalconfirmed"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(totalcase>max1)
                                 max1=totalcase;
                            series1 = new LineGraphSeries<>();
                            series1.appendData(new DataPoint(k,totalcase), true, 500);
                            graph.getViewport().setMaxX(graphical.length());
                            graph.getViewport().setMaxY(max1);
                            graph.getViewport().setXAxisBoundsManual(true);
                            graph.getViewport().setYAxisBoundsManual(true);
                            series1.setColor(Color.YELLOW);
                            series1.setDrawDataPoints(true);
                            series1.setDataPointsRadius(5);
                            graph.addSeries(series1);
                            graph.getGridLabelRenderer().setHorizontalAxisTitle("Days");
                            graph.getGridLabelRenderer().setVerticalAxisTitle("TOTAL CONFIRMED");
                            graph.getGridLabelRenderer().setGridColor(Color.MAGENTA);
                            graph.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
                            graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
                            }
                        // Graph for death cases
                        for ( k = 0; k < graphical.length(); k++) {
                             max1=0.0;
                            try {
                                statedata = graphical.getJSONObject(k);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                datadeceased = Double.parseDouble(statedata.getString("totaldeceased"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if (datadeceased > max1)
                                max1 = datadeceased;
                            series2 = new LineGraphSeries<>();
                            series2.appendData(new DataPoint(k, datadeceased), true, 500);
                            graph1.getViewport().setMaxX(graphical.length());
                            graph1.getViewport().setMaxY(max1);
                            graph1.getViewport().setXAxisBoundsManual(true);
                            graph1.getViewport().setYAxisBoundsManual(true);
                            series2.setDrawDataPoints(true);
                            series2.setDataPointsRadius(5);
                            graph1.addSeries(series2);
                            series2.setColor(Color.RED);
                            series2.setDataPointsRadius(5);
                            graph1.getGridLabelRenderer().setHorizontalAxisTitle("Days");
                            graph1.getGridLabelRenderer().setVerticalAxisTitle("DEATHS");
                            graph1.getGridLabelRenderer().setGridColor(Color.MAGENTA);
                            graph1.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
                            graph1.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
                        }

                        double datarecovered = 0;
                        //Graph for total recovered
                            for ( k = 0; k < graphical.length(); k++) {

                                try {
                                    statedata = graphical.getJSONObject(k);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                try {
                                    datarecovered = Double.parseDouble(statedata.getString("totalrecovered"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                if (datarecovered > max1)
                                    max1 = datarecovered;
                                series3 = new LineGraphSeries<>();
                                series3.appendData(new DataPoint(k, datarecovered), true, 500);
                                graph2.getViewport().setMaxX(graphical.length());
                                graph2.getViewport().setMaxY(max1);
                                graph2.getViewport().setXAxisBoundsManual(true);
                                graph2.getViewport().setYAxisBoundsManual(true);
                                series3.setDrawDataPoints(true);
                                series3.setDataPointsRadius(5);
                                graph2.addSeries(series3);
                                series3.setColor(GREEN);
                                graph2.getGridLabelRenderer().setHorizontalAxisTitle("Days");
                                series3.setDataPointsRadius(5);
                                graph2.getGridLabelRenderer().setVerticalAxisTitle("RECOVERED");
                                graph2.getGridLabelRenderer().setGridColor(Color.MAGENTA);
                                graph2.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
                                graph2.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
                        }

                            //Graph for ative cases
                        for ( k = 0; k < graphical.length(); k++) {
                            double dataactive = 0;



                            try {
                                statedata = graphical.getJSONObject(k);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            double tot= 0;
                            try {
                                tot = Double.parseDouble(statedata.getString("totalconfirmed"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            double rec= 0;
                            try {
                                rec = Double.parseDouble(statedata.getString("totalrecovered"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            double dea= 0;
                            try {
                                dea = Double.parseDouble(statedata.getString("totaldeceased"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            dataactive=tot-rec-dea;


                            if (dataactive > max1)
                                max1 = dataactive;
                            series4 = new LineGraphSeries<>();
                            series4.appendData(new DataPoint(k, dataactive), true, 500);
                            graph3.getViewport().setMaxX(graphical.length());
                            graph3.getViewport().setMaxY(1200000);
                            graph3.getViewport().setXAxisBoundsManual(true);
                            graph3.getViewport().setYAxisBoundsManual(true);
                            series4.setDrawDataPoints(true);
                            series4.setDataPointsRadius(5);
                            graph3.addSeries(series4);
                            series4.setColor(BLUE);
                            series4.setDrawDataPoints(true);
                            series4.setDataPointsRadius(5);
                            graph3.getGridLabelRenderer().setHorizontalAxisTitle("Days");
                            graph3.getGridLabelRenderer().setVerticalAxisTitle("ACTIVE CASES");
                            graph3.getGridLabelRenderer().setGridColor(Color.MAGENTA);
                            graph3.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
                            graph3.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
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

    }
}




























































//
//
//                for (int j = 0; j < tcases.size(); j++) {
//                    x_axis = j;
//                    y_axis = Integer.parseInt(tcases.get(j));
//                    series1 = new LineGraphSeries<>();
//                    graph1.getViewport().setMaxX(Math.max(x_axis,0));
//                    graph1.getViewport().setMaxY(Math.max(y_axis,0));
//                    series1.appendData(new DataPoint(x_axis, y_axis), true, 100);
//                    graph1.getViewport().setXAxisBoundsManual(true);
//                    graph1.getViewport().setXAxisBoundsManual(true);
//                    series1.setDrawDataPoints(true);
//                    graph1.addSeries(series1);
//                }
//
//                for (int j = 0; j < tcases.size(); j++) {
//                    x_axis = j;
//                    y_axis = Integer.parseInt(tcases.get(j));
//                    series1 = new LineGraphSeries<>();
//                    graph2.getViewport().setMaxX(Math.max(x_axis,0));
//                    graph2.getViewport().setMaxY(Math.max(y_axis,0));
//                    series1.appendData(new DataPoint(x_axis, y_axis), true, 100);
//                    graph2.getViewport().setXAxisBoundsManual(true);
//                    graph2.getViewport().setXAxisBoundsManual(true);
//                    series1.setDrawDataPoints(true);
//                    graph2.addSeries(series1);
//                }
//
//                for (int j = 0; j < tcases.size(); j++) {
//                    x_axis = j;
//                    y_axis = Integer.parseInt(tcases.get(j));
//                    series1 = new LineGraphSeries<>();
//                    graph3.getViewport().setMaxX(Math.max(x_axis,0));
//                    graph3.getViewport().setMaxY(Math.max(y_axis,0));
//                    series1.appendData(new DataPoint(x_axis, y_axis), true, 100);
//                    graph3.getViewport().setXAxisBoundsManual(true);
//                    graph3.getViewport().setYAxisBoundsManual(true);
//                    series1.setDrawDataPoints(true);
//                    graph3.addSeries(series1);
//                }
//         LineGraphSeries<DataPoint> series2 = null;
//        ApiResponse obj = new ApiResponse();
//        JSONArray active = obj.cases_time_series();
//        ArrayList<String> st = new ArrayList<>();
//        JSONObject recovered = null;
//        for (int k = 0; k < active.length(); k++)
//        {
//            try {
//                recovered = active.getJSONObject(k);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            try {
//                st.add(recovered.getString("totalconfirmed"));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//        for (int k = 0; k < st.size() ; k++)
//        {
//          double  x_axis1 = k;
//           double y_axis1 =  Double.parseDouble(st.get(k));
//            if(y_axis1>max)
//                max=y_axis1;
//
//            series2 = new LineGraphSeries<>();
//            series2.appendData(new DataPoint(x_axis1, y_axis1), true, 500);
//            graph1.getViewport().setMaxX(tcases.size());
//            graph1.getViewport().setMaxY(max);
//            graph1.getViewport().setXAxisBoundsManual(true);
//            graph1.getViewport().setYAxisBoundsManual(true);
//            series2.setColor(Color.RED);
//            series2.setDrawDataPoints(true);
//            series2.setDataPointsRadius(5);
//            graph1.addSeries(series2);
//        }






