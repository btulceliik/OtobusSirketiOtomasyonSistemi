package com.example.otobussirketiotomasyonu;

public class Sefer {
    private String otobusSirketiAdi;
    private String kalkisYeri;
    private String varisYeri;
    private String seferZamani;
    private double biletFiyati;

    public Sefer(String otobusSirketiAdi, String kalkisYeri, String varisYeri, String seferZamani, double biletFiyati) {
        this.otobusSirketiAdi = otobusSirketiAdi;
        this.kalkisYeri = kalkisYeri;
        this.varisYeri = varisYeri;
        this.seferZamani = seferZamani;
        this.biletFiyati = biletFiyati;
    }

    // Add getters if needed
    public String getOtobusSirketiAdi() {
        return otobusSirketiAdi;
    }

    public String getKalkisYeri() {
        return kalkisYeri;
    }

    public String getVarisYeri() {
        return varisYeri;
    }

    public String getSeferZamani() {
        return seferZamani;
    }

    public double getBiletFiyati() {
        return biletFiyati;
    }
}
