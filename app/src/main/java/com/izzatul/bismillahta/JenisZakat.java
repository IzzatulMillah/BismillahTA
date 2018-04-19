package com.izzatul.bismillahta;

/**
 * Created by Izzatul on 4/18/2018.
 */

public class JenisZakat {

    private int id;
    private String jenisZakat;
    private String deskripsiZakat;
    private String nishabZakat;
    private String waktuZakat;
    private String contohHitungan;

    public JenisZakat() {
    }

    public JenisZakat(int id, String jenisZakat, String deskripsiZakat, String nishabZakat, String waktuZakat, String contohHitungan) {
        this.id = id;
        this.jenisZakat = jenisZakat;
        this.deskripsiZakat = deskripsiZakat;
        this.nishabZakat = nishabZakat;
        this.waktuZakat = waktuZakat;
        this.contohHitungan = contohHitungan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJenisZakat() {
        return jenisZakat;
    }

    public void setJenisZakat(String jenisZakat) {
        this.jenisZakat = jenisZakat;
    }

    public String getDeskripsiZakat() {
        return deskripsiZakat;
    }

    public void setDeskripsiZakat(String deskripsiZakat) {
        this.deskripsiZakat = deskripsiZakat;
    }

    public String getNishabZakat() {
        return nishabZakat;
    }

    public void setNishabZakat(String nishabZakat) {
        this.nishabZakat = nishabZakat;
    }

    public String getWaktuZakat() {
        return waktuZakat;
    }

    public void setWaktuZakat(String waktuZakat) {
        this.waktuZakat = waktuZakat;
    }

    public String getContohHitungan() {
        return contohHitungan;
    }

    public void setContohHitungan(String contohHitungan) {
        this.contohHitungan = contohHitungan;
    }
}
