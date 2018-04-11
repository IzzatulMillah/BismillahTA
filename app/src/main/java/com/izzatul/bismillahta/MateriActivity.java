package com.izzatul.bismillahta;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MateriActivity extends AppCompatActivity {

    private TextView tvJudulMateri, tvDeskripsi;
    private int bundleMateri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_materi);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.materi);
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);

        tvJudulMateri = findViewById(R.id.tv_judul_materi);
        tvDeskripsi = findViewById(R.id.tv_deskripsi_materi);

        // inisialisasi RequestQueue
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://localhost/basic/web/services/get-data";

        // mencari respon dari alamat yang tertera
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MateriActivity.this, "Response is : "+response, Toast.LENGTH_SHORT).show();
                        // Display the first 500 characters of the response string.
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MateriActivity.this, "It didn't work!!", Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

        bundleMateri = getIntent().getExtras().getInt("kategori");
        if (bundleMateri == 1){
            Toast.makeText(this, "Zakat Fitrah", Toast.LENGTH_SHORT).show();
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

}
