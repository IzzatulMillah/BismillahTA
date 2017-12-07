package com.izzatul.bismillahta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class HitungZakatFitrahActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editHargaBeras;
    ImageButton btResetHrgBeras;
    TextView bHitung, bUlang, textHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hitung_zakat_fitrah);

        editHargaBeras = (EditText) findViewById(R.id.etHargaBeras);
        btResetHrgBeras = (ImageButton) findViewById(R.id.btnResetBeras);
        bHitung = (TextView) findViewById(R.id.btnHitung);
        bUlang = (TextView) findViewById(R.id.btnUlangi);
        textHasil = (TextView) findViewById(R.id.tvHasil);

        btResetHrgBeras.setOnClickListener(this);
        bHitung.setOnClickListener(this);
        bUlang.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnResetBeras :
                editHargaBeras.setText("");
                break;
            case R.id.btnHitung :
                // TODO cari tahu format titik untuk hasil perhitungan dan inputan edittext
                if (editHargaBeras.getText().toString().equals("")){
                    Toast.makeText(this, "Isi harga beras terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
                else{
                    final float KADAR_ZAKAT_FITRAH = (float) 3.5;
                    Float hargaBeras = Float.parseFloat(editHargaBeras.getText().toString());
                    float hasilHitung = KADAR_ZAKAT_FITRAH * hargaBeras;
                    textHasil.setText("Jumlah yang dizakatkan adalah 3,5 liter beras atau uang sejumlah "+ hasilHitung);
                }
                break;
            case R.id.btnUlangi :
                editHargaBeras.setText("");
                textHasil.setText("");
                break;
        }
    }
}
