package com.example.otobussirketiotomasyonu;

public class Yolcu {
    private String tcNo;
    private String ad;
    private String soyad;
    private String dogumTarihi;
    private String telefon;
    public Yolcu(String tcNo, String ad, String soyad, String dogumTarihi, String telefon) {
        this.tcNo = tcNo;
        this.ad = ad;
        this.soyad = soyad;
        this.dogumTarihi = dogumTarihi;
        this.telefon = telefon;
    }

    public String getTcNo() {
        return tcNo;
    }

    public void setTcNo(String tcNo) {
        this.tcNo = tcNo;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getDogumTarihi() {
        return dogumTarihi;
    }

    public void setDogumTarihi(String dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String  telefon) {
        this.telefon = telefon;
    }
}
