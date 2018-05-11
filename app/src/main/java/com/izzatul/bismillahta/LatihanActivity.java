package com.izzatul.bismillahta;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class LatihanActivity extends AppCompatActivity implements View.OnClickListener{
    private int idSoal;

    private static final String TAG = LatihanActivity.class.getName();
    private String url = "http://192.168.43.20/basic/web/services/get-soal/";
    private String urlJawaban = "http://192.168.43.20/basic/web/services/get-jawaban/";

    private TextView noUrutSoal, jumlahSoal, textSoal, jawaban1, jawaban2, jawaban3, jawaban4, jawaban5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latihan);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.latihan);
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);

        noUrutSoal = findViewById(R.id.tv_urutan_soal);
        textSoal = findViewById(R.id.tv_soal);
        jawaban1 = findViewById(R.id.jawaban1);
        jawaban2 = findViewById(R.id.jawaban2);
        jawaban3 = findViewById(R.id.jawaban3);
        jawaban4 = findViewById(R.id.jawaban4);
        jawaban5 = findViewById(R.id.jawaban5);

        idSoal = getRandomNumber();
        getSoal(idSoal);
    }

    // tombol click back ke home atau activity sebelumnya
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int idItem = item.getItemId();
        switch (idItem) {
            case android.R.id.home :
                finish();
                break;
            default:
                Toast.makeText(this, "what are you pushing?", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private int getRandomNumber(){
        int num;
        int min = 1;
        int max = 10;

        num = new Random().nextInt((max - min) + 1) + min;
        return num;
    }

    private void getSoal(int idSoal){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url + idSoal, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    // Parsing json object response
                    // response will be a json object
                    JSONObject data = response.getJSONObject("data");
                    String soal = data.getString("soal");

                    textSoal.setText(soal);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                progressDialog.dismiss();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
    }

    private void getJawaban(int idSoal){
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url + idSoal, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    // Parsing json object response
                    // response will be a json object
                    /*
                    * Untuk mengambil data jawaban, menggunakan URL atau end point yag sama dengan mengambil data soal
                     * sebelumnya sudah bisa mengambil data soal, jawabannya belum*/
                    JSONObject data = response.getJSONObject("data");
                    String jawaban1 = data.getString("jawaban");
                    String jawaban2 = data.getString("jawaban");
                    String jawaban3 = data.getString("jawaban");
                    String jawaban4 = data.getString("jawaban");
                    String jawaban5 = data.getString("jawaban");

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
    }

    @Override
    public void onClick(View view) {

    }
}
