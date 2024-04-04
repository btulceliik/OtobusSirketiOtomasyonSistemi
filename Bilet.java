package com.example.otobussirketiotomasyonu;

public class Bilet {

    private String otobusSirketAdi;
    private String kalkis;
    private String varis;
    private String sefer;
    private int koltukNo;

    public Bilet(String otobusSirketAdi, String kalkis, String varis, String sefer, int koltukNo) {
        this.otobusSirketAdi = otobusSirketAdi;
        this.kalkis = kalkis;
        this.varis = varis;
        this.sefer = sefer;
        this.koltukNo = koltukNo;
    }

    public String getOtobusSirketAdi() {
        return otobusSirketAdi;
    }

    public void setOtobusSirketAdi(String otobusSirketAdi) {
        this.otobusSirketAdi = otobusSirketAdi;
    }

    public String getKalkis() {
        return kalkis;
    }

    public void setKalkis(String kalkis) {
        this.kalkis = kalkis;
    }

    public String getVaris() {
        return varis;
    }

    public void setVaris(String varis) {
        this.varis = varis;
    }

    public String getSefer() {
        return sefer;
    }

    public void setSefer(String sefer) {
        this.sefer = sefer;
    }

    public int getKoltukNo() {
        return koltukNo;
    }

    public void setKoltukNo(int koltukNo) {
        this.koltukNo = koltukNo;
    }
}