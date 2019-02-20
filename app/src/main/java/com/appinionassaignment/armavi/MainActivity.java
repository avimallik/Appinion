package com.appinionassaignment.armavi;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import static android.R.layout.simple_spinner_item;

public class MainActivity extends AppCompatActivity {

    private String URLstring = "https://raw.githubusercontent.com/appinion-dev/intern-dcr-data/master/data.json";


    private ArrayList<PGModel> pgModelArrayListLit;

    //For Product Group
    private ArrayList<String> productGroupTemp = new ArrayList<String>();

    //For Literature
    private ArrayList<String> literatureListTemp = new ArrayList<String>();

    //For Physician Sample
    private ArrayList<String> physicianSampleTemp = new ArrayList<String>();

    //For Gift
    private ArrayList<String> giftTemp = new ArrayList<String>();

    //Product Group Spinner
    Spinner spinnerProductGroup;

    //Literature List Spinner
    Spinner literatureList;

    //Physician Sample Spinner
    Spinner spinnerPhysicicianSample;

    //Gift Spinner
    Spinner giftSpinner;

    //Done Button
    Button doneButton ;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getColor(R.color.toolbar_title_color));
        toolbar.setTitle(R.string.main_activity_title);
        setSupportActionBar(toolbar);

       spinnerProductGroup = (Spinner) findViewById(R.id.spinner_product_group);
       literatureList = (Spinner) findViewById(R.id.spinner_literature_list);
       spinnerPhysicicianSample = (Spinner) findViewById(R.id.phsysician_sample_spinner);
       giftSpinner = (Spinner) findViewById(R.id.gift_spinner);
       doneButton = (Button) findViewById(R.id.doneBtn);

       doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), getString(R.string.done_alert), Toast.LENGTH_SHORT).show();
            }
       });

       pgGroup();
       literature();
       physicianSample();
       gift();


    }

    private void pgGroup() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);

                                pgModelArrayListLit = new ArrayList<>();
                                JSONArray dataArray  = obj.getJSONArray("product_group_list");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    PGModel pgModel = new PGModel();
                                    JSONObject dataobj = dataArray.getJSONObject(i);
                                    pgModel.setProduct_group(dataobj.getString("product_group"));
                                    pgModelArrayListLit.add(pgModel);

                                }

                                for (int i = 0; i < pgModelArrayListLit.size(); i++){
                                    productGroupTemp.add(pgModelArrayListLit.get(i).getProduct_group().toString());
                                }

                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(MainActivity.this, simple_spinner_item, productGroupTemp);
                                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinnerProductGroup.setAdapter(spinnerArrayAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    private void literature() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject obj = new JSONObject(response);
                            pgModelArrayListLit = new ArrayList<>();
                            JSONArray dataArray  = obj.getJSONArray("literature_list");

                            for (int i = 0; i < dataArray.length(); i++) {

                                PGModel pgModel = new PGModel();
                                JSONObject dataobj = dataArray.getJSONObject(i);
                                pgModel.setLiterature(dataobj.getString("literature"));
                                pgModelArrayListLit.add(pgModel);

                            }

                            for (int i = 0; i < pgModelArrayListLit.size(); i++){
                                literatureListTemp.add(pgModelArrayListLit.get(i).getLiterature().toString());
                            }

                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(MainActivity.this, simple_spinner_item, literatureListTemp);
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            literatureList.setAdapter(spinnerArrayAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void physicianSample() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject obj = new JSONObject(response);
                            pgModelArrayListLit = new ArrayList<>();
                            JSONArray dataArray  = obj.getJSONArray("physician_sample_list");

                            for (int i = 0; i < dataArray.length(); i++) {
                                PGModel pgModel = new PGModel();
                                JSONObject dataobj = dataArray.getJSONObject(i);
                                pgModel.setSample(dataobj.getString("sample"));
                                pgModelArrayListLit.add(pgModel);
                            }

                            for (int i = 0; i < pgModelArrayListLit.size(); i++){
                                physicianSampleTemp.add(pgModelArrayListLit.get(i).getSample().toString());
                            }

                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(MainActivity.this, simple_spinner_item, physicianSampleTemp);
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerPhysicicianSample.setAdapter(spinnerArrayAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void gift() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            pgModelArrayListLit = new ArrayList<>();
                            JSONArray dataArray  = obj.getJSONArray("gift_list");

                            for (int i = 0; i < dataArray.length(); i++) {

                                PGModel pgModel = new PGModel();
                                JSONObject dataobj = dataArray.getJSONObject(i);
                                pgModel.setGift(dataobj.getString("gift"));
                                pgModelArrayListLit.add(pgModel);

                            }

                            for (int i = 0; i < pgModelArrayListLit.size(); i++){
                                giftTemp.add(pgModelArrayListLit.get(i).getGift().toString());
                            }

                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(MainActivity.this, simple_spinner_item, giftTemp);
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            giftSpinner.setAdapter(spinnerArrayAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
