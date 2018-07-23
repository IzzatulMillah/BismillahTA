package com.izzatul.bismillahta;

/**
 * Created by Izzatul on 7/23/2018.
 */

public class SkorUser {
    public int skor;
    public String tanggalSimpan;

    public SkorUser() {
    }

    public SkorUser(int skor, String tanggalSimpan) {
        this.skor = skor;
        this.tanggalSimpan = tanggalSimpan;
    }

    public int getSkor() {
        return skor;
    }

    public void setSkor(int skor) {
        this.skor = skor;
    }

    public String getTanggalSimpan() {
        return tanggalSimpan;
    }

    public void setTanggalSimpan(String tanggalSimpan) {
        this.tanggalSimpan = tanggalSimpan;
    }
}
