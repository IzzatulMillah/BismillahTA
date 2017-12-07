package com.izzatul.bismillahta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class HitungZakatMaalActivity extends AppCompatActivity implements View.OnClickListener{
    EditText totalHarta, hargaBeras;
    ImageButton resetHarta, resetBeras;
    TextView hitungUlang, hitung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hitung_zakat_maal);

        totalHarta = (EditText) findViewById(R.id.etTotalHarga);
        hargaBeras = (EditText) findViewById(R.id.etHargaBeras);

        resetHarta = (ImageButton) findViewById(R.id.btnResetHarta);
        resetBeras = (ImageButton) findViewById(R.id.btnResetBeras);

        hitung = (TextView) findViewById(R.id.btnHitung);
        hitungUlang = (TextView) findViewById(R.id.btnUlangi);

        resetHarta.setOnClickListener(this);
        resetBeras.setOnClickListener(this);

        hitung.setOnClickListener(this);
        hitungUlang.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnResetHarta:
                totalHarta.setText("");
                break;
            case R.id.btnResetBeras:
                hargaBeras.setText("");
                break;
            case R.id.btnHitung:
                Toast.makeText(this, "INI MENGHITUNG", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnUlangi:
                totalHarta.setText("");
                hargaBeras.setText("");
                break;
        }
    }
}
