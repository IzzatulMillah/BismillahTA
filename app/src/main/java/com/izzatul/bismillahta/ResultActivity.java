package com.izzatul.bismillahta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView skorAkhir;
    private int bundleSkor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        setupToolbar();
        getView();
    }

    private void setupToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.skor_akhir);
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
    }

    public void getView(){
        skorAkhir = findViewById(R.id.tv_skor_akhir);
        bundleSkor = getIntent().getExtras().getInt("skorAkhir");

        skorAkhir.setText(String.valueOf(bundleSkor));
    }
}
