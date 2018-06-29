package com.izzatul.bismillahta;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
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

import java.util.ArrayList;
import java.util.Random;

public class LatihanActivity extends AppCompatActivity implements View.OnClickListener{
    private int idLatihan;
    private int nomorSoal = 1, jumSoal = 10;
    private int i = 1;
    private int skor;
    ArrayList<Integer> idSoal;

    private static final String TAG = LatihanActivity.class.getName();
    private String url = "http://millah.cyber1011.com/web/services/get-latihan/";

    private TextView textUrutanSoal, jumlahSoal, textSoal, btnNext, btnPrev, textSkor;
    private RadioButton jawaban1, jawaban2, jawaban3, jawaban4;
    private String jawabanBenar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latihan);

        setUpToolbar();

        getElements();

        idSoal = new ArrayList<>();

        getKuis();

        btnPrev.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    public void getKuis(){
        Log.d("ayam","jalan");
        textUrutanSoal.setText(String.valueOf(nomorSoal));
        do{
            idLatihan = getRandomNumber();
            Log.d("ayam",String.valueOf(idLatihan));
        } while (idSoal.contains(idLatihan));
        idSoal.add(idLatihan);

        getSoal(idLatihan);
        nomorSoal++;
    }

    void setUpToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.latihan);
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
    }

    void getElements(){
        textUrutanSoal = findViewById(R.id.tv_urutan_soal);
        textSoal = findViewById(R.id.tv_soal);
        jawaban1 = findViewById(R.id.jawaban1);
        jawaban2 = findViewById(R.id.jawaban2);
        jawaban3 = findViewById(R.id.jawaban3);
        jawaban4 = findViewById(R.id.jawaban4);
        btnPrev = findViewById(R.id.btn_prev);
        btnNext = findViewById(R.id.btn_next);
        textSkor = findViewById(R.id.tv_skor);
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
                    String jawab1 = data.getString("jawaban_1");
                    String jawab2 = data.getString("jawaban_2");
                    String jawab3 = data.getString("jawaban_3");
                    String jawab4 = data.getString("jawaban_4");
                    String jawaban_benar = data.getString("jawaban_benar");

                    textSoal.setText(soal);
                    jawaban1.setText(jawab1);
                    jawaban2.setText(jawab2);
                    jawaban3.setText(jawab3);
                    jawaban4.setText(jawab4);

                    jawabanBenar = jawaban_benar;

//                    Toast.makeText(LatihanActivity.this, jawaban_benar, Toast.LENGTH_SHORT).show();
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

    public void cekJawaban(){
        if (jawaban1.isChecked()){
            if (jawaban1.getText().toString().equals(jawabanBenar)){
                skor = skor + 10;
                textSkor.setText(""+skor);
                Toast.makeText(this, "Jawaban Benar", Toast.LENGTH_SHORT).show();
            }
            else {
                textSkor.setText(""+skor);
                Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
            }
        }
        else if (jawaban2.isChecked()){
            if (jawaban2.getText().toString().equals(jawabanBenar)){
                skor = skor + 10;
                textSkor.setText(""+skor);
                Toast.makeText(this, "Jawaban Benar", Toast.LENGTH_SHORT).show();
            }
            else {
                textSkor.setText(""+skor);
                Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
            }
        }
        else if (jawaban3.isChecked()){
            if (jawaban3.getText().toString().equals(jawabanBenar)){
                skor = skor + 10;
                textSkor.setText(""+skor);
                Toast.makeText(this, "Jawaban Benar", Toast.LENGTH_SHORT).show();
            }
            else {
                textSkor.setText(""+skor);
                Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
            }
        }
        else if (jawaban4.isChecked()){
            if (jawaban4.getText().toString().equals(jawabanBenar)){
                skor = skor + 10;
                textSkor.setText(""+skor);
                Toast.makeText(this, "Jawaban Benar", Toast.LENGTH_SHORT).show();
            }
            else {
                textSkor.setText(""+skor);
                Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_prev :
                Toast.makeText(this, "TOMBOL PREV CLICKED", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_next :
                cekJawaban();
                getKuis();
//                Toast.makeText(this, "TOMBOL NEXT CLICKED", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
