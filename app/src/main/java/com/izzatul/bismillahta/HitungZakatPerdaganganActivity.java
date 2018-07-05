package com.izzatul.bismillahta;

import android.support.v7.app.AppCompatActivity;
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

public class HitungZakatPerdaganganActivity extends AppActivity{

    @NotEmpty(message = "Mohon diisi dahulu")
    EditText editModal, editKeuntungan, editPiutang, editHutang, editKerugian, editHaul, editEmas;

    TextView bHitung, bUlang, textHasil;
    ImageButton btResetModal, btResetKeuntungan, btResetPiutang, btResetHutang, btResetKerugian, btResetHaul, btResetEmas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hitung_zakat_perdagangan);

        setToolbar();
        setTheView();
    }

    public void setToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
    }

    public void setTheView(){
        editHaul = findViewById(R.id.etHaul);
        editEmas = findViewById(R.id.etHargaEmas);
        editModal = findViewById(R.id.etModal);
        editKeuntungan = findViewById(R.id.etKeuntungan);
        editPiutang = findViewById(R.id.etPiutang);
        editHutang = findViewById(R.id.etHutang);
        editKerugian = findViewById(R.id.etKerugian);

        bHitung = findViewById(R.id.btnHitung);
        bUlang = findViewById(R.id.btnUlangi);
        textHasil = findViewById(R.id.tvHasil);

        btResetHaul = findViewById(R.id.btnResetHaul);
        btResetEmas = findViewById(R.id.btnResetHargaEmas);
        btResetModal = findViewById(R.id.btnResetModal);
        btResetKeuntungan = findViewById(R.id.btnResetKeuntungan);
        btResetPiutang = findViewById(R.id.btnResetPiutang);
        btResetHutang = findViewById(R.id.btnResetHutang);
        btResetKerugian = findViewById(R.id.btnResetKerugian);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnResetHaul:
                editHaul.setText("");
                break;
            case R.id.btnResetModal :
                editModal.setText("");
                break;
            case R.id.btnResetKeuntungan :
                editKeuntungan.setText("");
                break;
            case R.id.btnResetPiutang :
                editPiutang.setText("");
                break;
            case R.id.btnResetHutang :
                editHutang.setText("");
                break;
            case R.id.btnResetKerugian :
                editKerugian.setText("");
                break;
            case R.id.btnHitung :
                getHasilHitung();
                break;
            case R.id.btnUlangi:
                setAllNull();
                break;
        }
    }

    public void getHasilHitung(){
        // membuat format untuk menampilkan harga dalam rupiah
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        float NISAB_DAGANG = 85; // nisab perdagangan sama dengan emas, yaitu 85 gram
        int HAUL_PERAK = 1; // haul harta dagang juga sama dengan emas
        double PERSEN_ZAKAT = 0.025;

        int kepemilikan = Integer.parseInt(editHaul.getText().toString());
        int hargaEmas = Integer.parseInt(editEmas.getText().toString());
        int modal = Integer.parseInt(editModal.getText().toString());
        int keuntungan = Integer.parseInt(editKeuntungan.getText().toString());
        int piutang = Integer.parseInt(editPiutang.getText().toString());
        int hutang = Integer.parseInt(editHutang.getText().toString());
        int kerugian = Integer.parseInt(editKerugian.getText().toString());

        double total = (float) ((modal + keuntungan + piutang) - (hutang - kerugian));
        double nisab = total / hargaEmas;
        if (kepemilikan >= HAUL_PERAK & nisab >= NISAB_DAGANG) {
            double zakat = total * PERSEN_ZAKAT;
            textHasil.setText("Harta yang dizakatkan sejumlah " + formatRupiah.format(zakat));
        }
        else
            textHasil.setText("Anda tidak wajib membayar zakat karena harta yang dimiliki belum mencapai nisab dan haul");
    }

    public void setAllNull(){
        editHaul.setText("");
        editModal.setText("");
        editKeuntungan.setText("");
        editPiutang.setText("");
        editHutang.setText("");
        editKerugian.setText("");
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
