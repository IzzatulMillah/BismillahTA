package com.izzatul.bismillahta;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

import java.util.HashMap;

public class ResultActivity extends AppCompatActivity {
    private static final String TAG = ResultActivity.class.getName();
    private TextView skorAkhir;
    private int bundleSkor;
    private String namaUser, idUser;
    SessionManagement session;

    private String url = "http://millah.cyber1011.com/web/services/simpan-skor/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        session = new SessionManagement(getApplicationContext());
        setupToolbar();
        getView();
        simpanSkor(idUser,bundleSkor);
    }

    private void setupToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.skor_akhir);
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
    }

    public void getView(){
        skorAkhir = findViewById(R.id.tv_skor_akhir);
        bundleSkor = getIntent().getExtras().getInt("skorAkhir");

        HashMap<String, String> user = session.getUserDetails();
        namaUser = user.get(SessionManagement.KEY_NAME);
        idUser = user.get(SessionManagement.KEY_EMAIL);
        skorAkhir.setText(namaUser + "\n" + bundleSkor);//ambil username + id; nb : Key_email = id
    }

    public void simpanSkor(String userId, int skorUser){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url + userId + "/" + skorUser, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    // Parsing json object response
                    // response will be a json object
                    int status = response.getInt("status");

                    if (status == 1){
                        Toast.makeText(ResultActivity.this, "Data skor telah disimpan", Toast.LENGTH_SHORT).show();
                    } else if (status == 0){
                        Toast.makeText(ResultActivity.this, "Error. Data belum tersimpan", Toast.LENGTH_SHORT).show();
                    }

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
