package com.izzatul.bismillahta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button bMateri, bLatihan, bKalkulator, bLogOut, bProfilUser;
    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new SessionManagement(getApplicationContext());

        if (!session.isLoggedIn()){
            session.checkLogin();
            finish();
        }

        bMateri = findViewById(R.id.btnMateri);
        bLatihan = findViewById(R.id.btnSoalLatihan);
        bKalkulator = findViewById(R.id.btnKalkulator);
        bProfilUser = findViewById(R.id.btnProfilUser);
        bLogOut = findViewById(R.id.btnLogOut);

        bMateri.setOnClickListener(this);
        bLatihan.setOnClickListener(this);
        bKalkulator.setOnClickListener(this);
        bProfilUser.setOnClickListener(this);
        bLogOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnMateri:
                Intent materi = new Intent(MainActivity.this, KategoriActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("menu", 1);
                materi.putExtras(bundle);
                startActivity(materi);
                break;
            case R.id.btnSoalLatihan:
                Intent latihan = new Intent(MainActivity.this, LatihanActivity.class);
                startActivity(latihan);
                break;
            case R.id.btnKalkulator:
                Intent kalkulator = new Intent(MainActivity.this, KategoriActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("menu", 3);
                kalkulator.putExtras(bundle2);
                startActivity(kalkulator);
                break;
            case R.id.btnProfilUser :
                Intent profilUser = new Intent(MainActivity.this, ProfilUserActivity.class);
                startActivity(profilUser);
                break;
            case R.id.btnLogOut:
                session.logoutUser();
                finish();
                break;
        }
    }
}
