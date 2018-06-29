package com.izzatul.bismillahta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class HitungZakatHewanTernakActivity extends AppCompatActivity implements View.OnClickListener{
    RadioButton rbSapi, rbKambing, rbUnta;
    EditText jumlahHewan;
    TextView textHasil, btnHitung, btnUlang;
    ImageButton btnReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hitung_zakat_hewan_ternak);

        setToolbar();
        getView();
    }

    public void getView(){
        rbSapi = findViewById(R.id.rb_sapi_kerbau);
        rbKambing = findViewById(R.id.rb_kambing);
        rbUnta = findViewById(R.id.rb_unta);
        jumlahHewan = findViewById(R.id.jumlah_hewan);
        textHasil = findViewById(R.id.hasil_hitung);
        btnHitung = findViewById(R.id.btn_hitung);
        btnUlang = findViewById(R.id.btn_ulang);
        btnReset = findViewById(R.id.reset_jumlah_hewan);
    }

    public void setToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_hitung:
                if (rbSapi.isChecked()){}
                else if (rbKambing.isChecked()){}
                else if (rbUnta.isChecked()){}
                break;
            case R.id.btn_ulang :
                setNull();
                break;
        }
    }

    public void setNull(){
        jumlahHewan.setText("");
        textHasil.setText("");
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
