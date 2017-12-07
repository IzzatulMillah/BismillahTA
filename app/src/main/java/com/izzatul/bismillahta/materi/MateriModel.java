package com.izzatul.bismillahta.materi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.izzatul.bismillahta.materi.Materi.Materi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Izzatul on 11/21/2017.
 */

public final class MateriModel extends SQLiteOpenHelper{

    //semua variabel statis. nama database, versi database, dan nama tabel
    private static final String DB_NAME = "zakatDB";
    private static final int DB_VERSION = 1;
    private static final String TABEL_MATERI = "materiZakat";

    //kolom pada tabel database
    private static final String ID_MATERI = "id_materi";
    private static final String JUDUL_MATERI = "judul_materi";
    private static final String DETAIL_MATERI = "detail_materi";

    public MateriModel(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABEL_MATERI = "CREATE TABLE " + DB_NAME +
                "(" + ID_MATERI + " INTEGER PRIMARY KEY," +
                JUDUL_MATERI + " TEXT," +
                DETAIL_MATERI + " TEXT);";
        sqLiteDatabase.execSQL(CREATE_TABEL_MATERI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int versiLama, int versiBaru) {
        //drop tabel jika sudah ada
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABEL_MATERI);
        //buat tabel yang baru
        onCreate(sqLiteDatabase);
    }

    /* --------------- CRUD OPERATIONS --------------- */
    void addMateri(Materi materi){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(JUDUL_MATERI, materi.get_judul()); //judul materi
        values.put(DETAIL_MATERI, materi.get_deskripsi()); //deskripsi materi

        //inserting row
        db.insert(TABEL_MATERI, null, values);
        db.close(); //menutup koneksi database
    }

    //mengambil semua list materi
    public List<Materi> getAllMateri(){
        List<Materi> materiList = new ArrayList<Materi>();
        //select all
        String selectAllQuery = "SELECT * FROM " + TABEL_MATERI;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectAllQuery, null);

        //looping ke seluruh list yang ada dan memasukkan ke list
        if (cursor.moveToFirst()){
            do {
                Materi materi = new Materi();
                materi.set_id(Integer.parseInt(cursor.getString(0)));
                materi.set_judul(cursor.getString(1));
                materi.set_deskripsi(cursor.getString(2));

                //menambahkan materi ke list
                materiList.add(materi);
            }while (cursor.moveToNext());
        }
        return materiList;
    }

    // mengupdate satu data
    public int updateMateri(Materi materi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(JUDUL_MATERI, materi.get_judul()); //judul materi
        values.put(DETAIL_MATERI, materi.get_deskripsi()); //deskripsi materi
        //meng-update row
        return db.update(TABEL_MATERI, values, ID_MATERI + " = ?", new String[] { String.valueOf(materi.get_id())});
    }

    public void deleteMateri(Materi materi){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABEL_MATERI, ID_MATERI + " = ? ", new String[]{ String.valueOf(materi.get_id())});
        db.close();
    }


}
