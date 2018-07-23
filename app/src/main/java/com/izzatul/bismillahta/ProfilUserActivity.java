package com.izzatul.bismillahta;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProfilUserActivity extends AppCompatActivity {
    private RecyclerView mList;
    private String url = "http://192.168.43.20/basic/web/services/get-skor/";
    private String idUser;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<SkorUser> skorUserListList;
    private SkorAdapter adapter;
    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_user);
        session = new SessionManagement(getApplicationContext());

        setTheView();
        getAllUserScore(idUser);
    }

    public void setTheView(){
        mList = findViewById(R.id.main_list);

        skorUserListList = new ArrayList<>();
        adapter = new SkorAdapter(getApplicationContext(), skorUserListList);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.addItemDecoration(dividerItemDecoration);
        mList.setAdapter(adapter);

        HashMap<String, String> user = session.getUserDetails();
        idUser = user.get(SessionManagement.KEY_EMAIL);
    }

    public void getAllUserScore(String userId){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url + userId, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++){
                        SkorUser skorUser = new SkorUser();

                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        skorUser.setTanggalSimpan(jsonObject.getString("tanggal"));
                        skorUser.setSkor(jsonObject.getInt("skor"));

                        skorUserListList.add(skorUser);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
                Log.d("ukuran", String.valueOf(skorUserListList.size()));
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
}
