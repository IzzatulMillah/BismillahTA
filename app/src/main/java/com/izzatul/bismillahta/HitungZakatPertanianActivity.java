package com.izzatul.bismillahta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.text.NumberFormat;
import java.util.Locale;

public class HitungZakatPertanianActivity extends AppActivity{

    @NotEmpty(message = "Mohon diisi dahulu")
    private EditText editJenis, editBerat;

    private TextView textHasil, btHitung, btUlang;
    private ImageButton resetJenis, resetBerat;
    private RadioButton rbManual, rbHujan;
    String selectedMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hitung_zakat_pertanian);

        setToolbar();
        getView();
    }

    public void setToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
    }

    public void getView(){
        editJenis = findViewById(R.id.etJenis);
        editBerat = findViewById(R.id.etBerat);
        textHasil = findViewById(R.id.tvHasil);
        resetJenis = findViewById(R.id.btnResetJenis);
        resetBerat = findViewById(R.id.btnResetBerat);
        rbManual = findViewById(R.id.rbManual);
        rbHujan = findViewById(R.id.rbHujan);
        btHitung = findViewById(R.id.btnHitung);
        btUlang = findViewById(R.id.btnUlangi);

        resetJenis.setOnClickListener(this);
        resetBerat.setOnClickListener(this);
        btHitung.setOnClickListener(this);
        btUlang.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnResetJenis :
                editJenis.setText("");
                break;
            case R.id.btnResetBerat :
                editBerat.setText("");
                break;
            case R.id.btnHitung :
                hitung();
                break;
            case R.id.btnUlangi :
                setNull();
                break;
        }
    }

    public void hitung(){
        // manual, zakatnya 5% ; hujan, zakatnya 10%
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        float NISAB_TANAMAN = 520; // nisab perdagangan adalah 520 kg, jika kurang dari itu, tidak perlu dizakatkan
        double PERSEN_ZAKAT_MANUAL = 0.05;
        double PERSEN_ZAKAT_HUJAN = 0.1;
        double zakat;

        int beratTanaman = Integer.parseInt(editBerat.getText().toString());
        String jenisTanaman = editJenis.getText().toString();

        if (beratTanaman >= NISAB_TANAMAN) {
            if (rbManual.isChecked()){
                zakat = beratTanaman * PERSEN_ZAKAT_MANUAL;
            } else {
                selectedMethod = rbHujan.getText().toString();
                zakat = beratTanaman * PERSEN_ZAKAT_HUJAN;
            }
            textHasil.setText("Zakat yang wajib dikeluarkan adalah " + zakat + " kg " + jenisTanaman);
        } else {
            textHasil.setText("Anda tidak wajib zakat");
        }
    }

    public void setNull(){
        editJenis.setText("");
        editBerat.setText("");
        textHasil.setText("");
    }
}