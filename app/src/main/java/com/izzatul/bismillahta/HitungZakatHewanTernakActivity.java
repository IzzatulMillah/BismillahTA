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

import com.mobsandgeeks.saripaar.annotation.NotEmpty;

public class HitungZakatHewanTernakActivity extends AppActivity{
    RadioButton rbSapi, rbKambing, rbUnta;

    @NotEmpty(message = "Mohon diisi dahulu")
    EditText jumlahHewan;

    TextView textHasil, btnHitung, btnUlang;

    private int jumHewan;

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

        btnHitung.setOnClickListener(this);
        btnUlang.setOnClickListener(this);
    }

    public void setToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (validated) {
            jumHewan = Integer.parseInt(jumlahHewan.getText().toString());
            switch (view.getId()){
                case R.id.btn_hitung:
                    if (rbSapi.isChecked()){
                        hitungSapi(jumHewan);
                    }
                    else if (rbKambing.isChecked()){
                        hitungKambing(jumHewan);
                    }
                    else if (rbUnta.isChecked()){
                        hitungUnta(jumHewan);
                    }
                    break;
                case R.id.btn_ulang :
                    validated = false;
                    setNull();
                    break;
            }
        }
    }

    public void hitungUnta(int jumUnta){
        if (jumUnta<5){
            textHasil.setText(R.string.tidak_wajib_zakat);
        }
        else if (jumUnta>=5 && jumUnta<=9){
            textHasil.setText("Jumlah unta : "
                    + jumUnta
                    + ". Zakat yang dibayar berupa 1 ekor syaah (kambing betina)");
        }
        else if (jumUnta>=10 && jumUnta<=14){
            textHasil.setText("Jumlah unta : "
                    + jumUnta
                    + ". Zakat yang dibayar berupa 2 ekor syaah (kambing betina)");
        }
        else if (jumUnta>=15 && jumUnta<=19){
            textHasil.setText("Jumlah unta : "
                    + jumUnta
                    + ". Zakat yang dibayar berupa 3 ekor syaah (kambing betina)");
        }
        else if (jumUnta>=20 && jumUnta<=24){
            textHasil.setText("Jumlah unta : "
                    + jumUnta
                    + ". Zakat yang dibayar berupa 4 ekor syaah atau kambing betina");
        }
        else if (jumUnta>=25 && jumUnta<=35){
            textHasil.setText("Jumlah unta : "
                    + jumUnta
                    + ". Zakat yang dibayar berupa 1 ekor bintu makhadh atau unta betina genap berusia 1 tahun masuk tahun ke-2");
        }
        else if (jumUnta>=36 && jumUnta<=45){
            textHasil.setText("Jumlah unta : "
                    + jumUnta
                    + ". Zakat yang dibayar berupa 1 ekor bintu labun atau unta betina genap berusia 2 tahun masuk tahun ke-3");
        }
        else if (jumUnta>=46 && jumUnta<=60){
            textHasil.setText("Jumlah unta : "
                    + jumUnta
                    + ". Zakat yang dibayar berupa 1 ekor hiqqah atau unta betina genap berusia 3 tahun masuk tahun ke-4");
        }
        else if (jumUnta>=61 && jumUnta<=75){
            textHasil.setText("Jumlah unta : "
                    + jumUnta
                    + ". Zakat yang dibayar berupa 1 ekor jaza'ah atau unta betina genap berusia 4 tahun masuk tahun ke-5");
        }
        else if (jumUnta>=76 && jumUnta<=90){
            textHasil.setText("Jumlah unta : "
                    + jumUnta
                    + ". Zakat yang dibayar berupa 2 ekor bintu labun");
        }
        else if (jumUnta>=91 && jumUnta<=120){
            textHasil.setText("Jumlah unta : "
                    + jumUnta
                    + ". Zakat yang dibayar berupa 3 hiqqah");
        }
        else if (jumUnta>=121 && jumUnta<=129){
            textHasil.setText("Jumlah unta : "
                    + jumUnta
                    + ". Zakat yang dibayar berupa 2 hiqqah dan seekor syaah");
        }
        else if (jumUnta>=130 && jumUnta<=134){
            textHasil.setText("Jumlah unta : "
                    + jumUnta
                    + ". Zakat yang dibayar berupa 2 hiqqah dan 2 syaah");
        }
        else if (jumUnta>=135 && jumUnta<=139){
            textHasil.setText("Jumlah unta : "
                    + jumUnta
                    + ". Zakat yang dibayar berupa 2 hiqqah dan 3 syaah");
        }
        else if (jumUnta>=140 && jumUnta<=144){
            textHasil.setText("Jumlah unta : "
                    + jumUnta
                    + ". Zakat yang dibayar berupa 2 hiqqah dan 4 syaah");
        }

    }

    public void hitungKambing(int jumKambing){
        if (jumKambing <= 4){
            textHasil.setText("Tidak wajib zakat. ");
        }
        else if (jumKambing>=5 && jumKambing<=9){
            textHasil.setText("Zakat yang dibayar berupa 1 ekor kambing betina");
        }
        else if (jumKambing>=10 && jumKambing<=14){
            textHasil.setText("Zakat yang dibayar berupa 2 ekor kambing betina");
        }
        else if (jumKambing>=15 && jumKambing<=19){
            textHasil.setText("Zakat yang dibayar berupa 3 ekor kambing betina");
        }
        else if (jumKambing>=20 && jumKambing<=25){
            textHasil.setText("Zakat yang dibayar berupa 4 ekor kambing betina");
        }
        else if (jumKambing>25){
            textHasil.setText("Zakat yang dibayarkan bertambah 1 ekor tiap kelipatan 10");
        }
    }

    public void hitungSapi(int jumSapi){
        if (jumSapi<30){
            textHasil.setText("Tidak wajib zakat");
        }
        else if (jumSapi>=30 && jumSapi<=39){
            textHasil.setText("Zakat yang dibayarkan berupa 1 ekor tabii'");
        }
        else if (jumSapi>=40 && jumSapi<=59){
            textHasil.setText("Zakat yang dibayarkan berupa 1 ekor musinnah");
        }
        else if (jumSapi>=60 && jumSapi<=69){
            textHasil.setText("Zakat yang dibayarkan berupa 2 ekor tabii'");
        }
        else if (jumSapi>=70 && jumSapi<=79){
            textHasil.setText("Zakat yang dibayarkan berupa 1 ekor tabii' dan 1 ekor musinnah");
        }
        else if (jumSapi>=80 && jumSapi<=89){
            textHasil.setText("Zakat yang dibayarkan berupa 2 ekor musinnah");
        }
        else if (jumSapi>=90 && jumSapi<=99){
            textHasil.setText("Zakat yang dibayarkan berupa 3 ekor tabii'");
        }
        else if (jumSapi>=100 && jumSapi<=109){
            textHasil.setText("Zakat yang dibayarkan berupa 1 ekor musinnah dan 2 ekor tabii'");
        }
        else if (jumSapi>=110 && jumSapi<=119){
            textHasil.setText("Zakat yang dibayarkan berupa 2 ekor musinnah dan 1 ekor tabii'");
        }
        else {
            textHasil.setText("Zakat yang dibayarkan berupa 3 ekor musinnah atau 4 ekor tabii'");
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
