package com.example.learnrealm.model;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SiswaModel extends RealmObject {
    @PrimaryKey
    private int id;
    private int absen;
    private int umur;
    private String nama;
    private String kelas;
    private String alamat;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setAbsen(int absen) {
        this.absen = absen;
    }

    public int getAbsen() {
        return absen;
    }

    public void setUmur(int umur){
        this.umur = umur;
    }

    public int getUmur (){
        return umur;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getKelas() {
        return kelas;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getAlamat() {
        return alamat;
    }

}
