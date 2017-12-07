package com.izzatul.bismillahta;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.izzatul.bismillahta.materi.Materi.DatabaseHandler;
import com.izzatul.bismillahta.materi.Materi.Materi;

import java.util.ArrayList;
import java.util.List;

public class MateriActivity extends Activity {

    ArrayList<String> listitem1 = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    public ListView materi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_materi);

        materi = (ListView) findViewById(R.id.list_materi);
        DatabaseHandler db = new DatabaseHandler(this);

        //menambahkan materi
        Log.d("Insert : ", "Inserting ..");
        db.addMateri(new Materi(1, "Zakat Fitrah", "Zakat yang wajib dikeluarkan oleh muslim dan muslimah"));
        db.addMateri(new Materi(2, "Zakat Maal", "Zakat harta yang wajib dikeluarkan apabila harta yang dimiliki sudah mencapai nisab"));

        //membaca semua materi
        Log.d("Reading : ", "Reading all materi..");
        List<Materi> materiList = db.getAllMateri();

        for (Materi materi : materiList){
            String log = "ID : " + materi.get_id() + ", Judul : " + materi.get_judul();
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listitem1);

        materi.setAdapter(adapter);
    }
}
