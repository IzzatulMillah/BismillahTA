package com.izzatul.bismillahta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class KalkulatorActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tZakatFitrah, tZakatMaal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator);

        tZakatFitrah = findViewById(R.id.tvZakatFitrah);
        tZakatMaal = findViewById(R.id.tvZakatMaal);

        tZakatFitrah.setOnClickListener(this);
        tZakatMaal.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvZakatFitrah:
                Intent hitungZakatFitrah = new Intent(KalkulatorActivity.this, HitungZakatFitrahActivity.class);
                startActivity(hitungZakatFitrah);
                break;
            case R.id.tvZakatMaal:
                Intent hitungZakatMaal = new Intent(KalkulatorActivity.this, HitungZakatEmasActivity.class);
                startActivity(hitungZakatMaal);
                break;
        }
    }
}
