package com.izzatul.bismillahta;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.text.NumberFormat;
import java.util.Locale;

public class HitungZakatPerakActivity extends AppActivity{

    @NotEmpty(message = "Mohon diisi dahulu")
    EditText editLamaKepemilikan, editJumlahTotal, editJumlahDipakai, editHargaPerak;

    ImageButton btResetKepemilikan, btResetJumlahTotal, btResetJumlahDipakai, btResetHarga;
    TextView bHitung, bUlang, textHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hitung_zakat_perak);

        setToolbar();
        getView();
    }

    public void setToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
    }

    public void getView(){
        editLamaKepemilikan = findViewById(R.id.etLamaKepemilikan);
        editJumlahTotal = findViewById(R.id.etTotalPerak);
        editJumlahDipakai = findViewById(R.id.etTotalDipakai);
        editHargaPerak = findViewById(R.id.etHargaPerak);

        btResetKepemilikan = findViewById(R.id.btnResetLamaKepemilikan);
        btResetJumlahTotal = findViewById(R.id.btnResetTotalPerak);
        btResetJumlahDipakai = findViewById(R.id.btnResetTotalDipakai);
        btResetHarga = findViewById(R.id.btnHargaPerak);

        bHitung = findViewById(R.id.btnHitung);
        bUlang = findViewById(R.id.btnUlangi);
        textHasil = findViewById(R.id.tvHasil);

        btResetKepemilikan.setOnClickListener(this);
        btResetJumlahTotal.setOnClickListener(this);
        btResetJumlahDipakai.setOnClickListener(this);
        btResetHarga.setOnClickListener(this);
        bHitung.setOnClickListener(this);
        bUlang.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (validated) {
            switch (view.getId()){
                case R.id.btnResetLamaKepemilikan :
                    editLamaKepemilikan.setText("");
                    break;
                case R.id.btnResetTotalPerak :
                    editJumlahTotal.setText("");
                    break;
                case R.id.btnResetTotalDipakai :
                    editJumlahDipakai.setText("");
                    break;
                case R.id.btnHargaPerak :
                    editHargaPerak.setText("");
                    break;
                case R.id.btnHitung :
                    hitung();
                    break;
                case R.id.btnUlangi :
                    validated = false;
                    setNull();
                    break;
            }
        }
    }

    public void hitung(){
        float NISAB_Perak = 595; // nisab Perak
        int HAUL_Perak = 1; // haul Perak
        double PERSEN_ZAKAT = 0.025;

        int lama = Integer.parseInt(editLamaKepemilikan.getText().toString());
        float jumlahPerak = Float.parseFloat(editJumlahTotal.getText().toString());
        float jumlahDipakai = Float.parseFloat(editJumlahDipakai.getText().toString());
        float hargaPerak = Float.parseFloat(editHargaPerak.getText().toString());

        /* membuat format untuk menampilkan harga dalam rupiah */
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        if (jumlahPerak >= NISAB_Perak && lama >= HAUL_Perak){
            double hitung = PERSEN_ZAKAT * (jumlahPerak - jumlahDipakai);
            double rupiah = hitung * hargaPerak;
            textHasil.setText("Harta yang dizakatkan sejumlah " + formatRupiah.format(rupiah));
        }
        else
            textHasil.setText(R.string.tidak_wajib_zakat);
    }

    public void setNull(){
        editLamaKepemilikan.setText("");
        editJumlahTotal.setText("");
        editJumlahDipakai.setText("");
        editHargaPerak.setText("");
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
