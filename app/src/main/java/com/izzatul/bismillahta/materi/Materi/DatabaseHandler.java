package com.izzatul.bismillahta.materi.Materi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Izzatul on 11/27/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "tabelZakat";
    private static final String TABEL_MATERI = "materi";

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "judul_materi";
    private static final String KEY_DESC = "deskripsi_materi";

    public DatabaseHandler(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABEL_MATERI = "CREATE TABLE " +  TABEL_MATERI + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_TITLE + " TEXT, "
                + KEY_DESC + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_TABEL_MATERI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versiBaru, int versiLama) {
        //drop older tabel
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_MATERI);

        //create again
        onCreate(db);
    }

    /*=========================== C R U D OPERATIONS =========================*/
    // TODO tambah materi baru
    public void addMateri(Materi newMateri){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, newMateri.get_judul());
        values.put(KEY_DESC, newMateri.get_deskripsi());

        //memasukkan ke baris
        db.insert(TABEL_MATERI, null, values);
        db.close();
    }

    //TODO mengambil satu materi
    public Materi getMateri(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABEL_MATERI,
                new String[]{ KEY_ID, KEY_TITLE, KEY_DESC },
                KEY_ID + "=?",
                new String[] { String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor != null)
            cursor.moveToFirst();

        Materi materi = new Materi(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2));

        return materi;
    }

    //TODO mengambil semua materi di database
    public List<Materi> getAllMateri(){
        List<Materi> materiList = new ArrayList<Materi>();

        //query select all
        String selectQuery = "SELECT * FROM " + TABEL_MATERI;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping ke semua data dan menambahkan e list
        if (cursor.moveToFirst()){
            do{
                Materi materi = new Materi();
                materi.set_id(Integer.parseInt(cursor.getString(0)));
                materi.set_judul(cursor.getString(1));
                materi.set_deskripsi(cursor.getString(2));

                //menambahkan ke kontak list
                materiList.add(materi);
            } while (cursor.moveToNext());
        }

        return materiList;
    }

    //TODO mengupdate record atau materi
    public int updateMateri (Materi newMateri){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, newMateri.get_judul());
        values.put(KEY_DESC, newMateri.get_deskripsi());

        //mengupdate baris
        return db.update(TABEL_MATERI, values, KEY_ID + " = ?", new String[] { String.valueOf(newMateri.get_id())});

    }

    //TODO menghapus materi
    public void deleteMateri (Materi materi){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABEL_MATERI, KEY_ID + " = ?", new String[] { String.valueOf(materi.get_id())});
        db.close();
    }
}
