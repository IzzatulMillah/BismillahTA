package com.izzatul.bismillahta.materi.Materi;

/**
 * Created by Izzatul on 11/21/2017.
 */

public class Materi {
    // private variabel
    int _id;
    String _judul, _deskripsi;

    //constructor kosongan
    public Materi(){}

    //constructor 1
    public Materi(int id, String judul, String deskripsi) {
        this._id = id;
        this._judul = judul;
        this._deskripsi = deskripsi;
    }

    //constructor 2
    public Materi(String judul, String deskripsi) {
        this._judul = judul;
        this._deskripsi = deskripsi;
    }

    public void set_id(int id) {
        this._id = id;
    }

    public void set_judul(String judul) {
        this._judul = judul;
    }

    public void set_deskripsi(String deskripsi) {
        this._deskripsi = deskripsi;
    }

    public int get_id() {
        return _id;
    }

    public String get_judul() {
        return _judul;
    }

    public String get_deskripsi() {
        return _deskripsi;
    }
}
