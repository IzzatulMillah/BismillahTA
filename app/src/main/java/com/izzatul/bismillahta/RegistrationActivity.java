package com.izzatul.bismillahta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = RegistrationActivity.class.getName();;
    private EditText etUsername, etPassword;
    private Button btnRegis;
    private String username, password;

    private String url = "http://192.168.43.20/basic/web/services/login/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getView();
    }

    public void getView(){
        etUsername = findViewById(R.id.editTextUsername);
        etPassword = findViewById(R.id.editTextPassword);
        btnRegis = findViewById(R.id.buttonRegis);

        btnRegis.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonLogin :
                fungsiRegis();
                break;
        }
    }

    private void fungsiRegis() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        username = etUsername.getText().toString();
        password = etPassword.getText().toString();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url + username + "/" + password, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                try {
                    // Parsing json object response
                    // response will be a json object
                    int status = response.getInt("status");
                    if (status == 1){
                        Toast.makeText(RegistrationActivity.this, R.string.login_sukses, Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(RegistrationActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else if (status == 0){
                        Toast.makeText(RegistrationActivity.this, R.string.login_gagal, Toast.LENGTH_SHORT).show();
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
