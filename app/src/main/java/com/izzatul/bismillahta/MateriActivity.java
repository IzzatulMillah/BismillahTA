package com.izzatul.bismillahta;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MateriActivity extends AppCompatActivity {

    private int bundleMateri;

    private static final String TAG = MainActivity.class.getName();

    private String url = "http://192.168.43.20/basic/web/services/get-data/1";

    private TextView judulMateri, deskripsi, nishabZakat, waktuZakat, besarZakat, contohZakat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_materi);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle(R.string.materi);
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);

        judulMateri = findViewById(R.id.tv_judul_materi);
        deskripsi = findViewById(R.id.tv_deskripsi_materi);
        nishabZakat = findViewById(R.id.tv_nishab_zakat);
        waktuZakat = findViewById(R.id.tv_waktu_zakat);
        besarZakat = findViewById(R.id.tv_besar_zakat);
        contohZakat = findViewById(R.id.tv_contoh_zakat);

        bundleMateri = getIntent().getExtras().getInt("kategori");
        if (bundleMateri == 1){
            getData();
//            Toast.makeText(this, "Zakat Fitrah", Toast.LENGTH_SHORT).show();
        } else if (bundleMateri == 2){
            Toast.makeText(this, "Zakat Emas", Toast.LENGTH_SHORT).show();
        } else if (bundleMateri == 3){
            Toast.makeText(this, "Zakat Perak", Toast.LENGTH_SHORT).show();
        } else if (bundleMateri == 5){
            Toast.makeText(this, "Zakat Perdagangan", Toast.LENGTH_SHORT).show();
        } else if (bundleMateri == 5){
            Toast.makeText(this, "Zakat Pertanian", Toast.LENGTH_SHORT).show();
        } else if (bundleMateri == 6){
            Toast.makeText(this, "Zakat Hewan Ternak", Toast.LENGTH_SHORT).show();
        }
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

    private void getData(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    // Parsing json object response
                    // response will be a json object
                    JSONObject data = response.getJSONObject("data");
                    String judul = data.getString("jenis_zakat");
                    String definisi = data.getString("definisi");
                    String nishab = data.getString("nishab_zakat");
                    String waktu = data.getString("waktu_pelaksanaan");
                    String besar = data.getString("besar_zakat");
                    String contoh = data.getString("contoh_perhitungan");

                    judulMateri.setText(judul);
                    deskripsi.setText(definisi);
                    nishabZakat.setText(nishab);
                    waktuZakat.setText(waktu);
                    besarZakat.setText(besar);
                    contohZakat.setText(contoh);

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

//    private void sendAndRequestResponse() {
//        //RequestQueue initialized
//        mRequestQueue = Volley.newRequestQueue(this);
//
//        //String Request initialized
//        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                deskripsi.setText("Respon = "+response.toString());
//                //Toast.makeText(getApplicationContext(), "Respon : "+response.toString(), Toast.LENGTH_SHORT).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.i(TAG,"Error :" + error.toString());
//            }
//        });
//
//        mRequestQueue.add(mStringRequest);
//    }

}
