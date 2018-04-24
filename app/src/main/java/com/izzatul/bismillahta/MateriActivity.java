package com.izzatul.bismillahta;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MateriActivity extends AppCompatActivity {

    private int bundleMateri;
    Bundle fitrah, emas, perak, dagang, tani, ternak;
    Intent intent;

    private static final String TAG = MainActivity.class.getName();
    private String url = "http://192.168.43.20/basic/web/services/get-data/";

    private TextView judulMateri, deskripsi, nishabZakat, waktuZakat, besarZakat, contohZakat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_materi);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.materi);
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);

        judulMateri = findViewById(R.id.tv_judul_materi);
        deskripsi = findViewById(R.id.tv_deskripsi_materi);
        nishabZakat = findViewById(R.id.tv_nishab_zakat);
        waktuZakat = findViewById(R.id.tv_waktu_zakat);
        besarZakat = findViewById(R.id.tv_besar_zakat);
        contohZakat = findViewById(R.id.tv_contoh_zakat);

        bundleMateri = getIntent().getExtras().getInt("kategori");
        getData(bundleMateri);
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

    private void getData(int idZakat){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url+idZakat , null, new Response.Listener<JSONObject>() {

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
}
