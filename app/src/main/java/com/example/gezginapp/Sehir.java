package com.example.gezginapp;

public class Sehir {

   private String isim;
   private String bilgi;
   private int fotografID;

    public Sehir(String isim, String bilgi, int fotografID) {
        this.isim = isim;
        this.bilgi = bilgi;
        this.fotografID = fotografID;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getBilgi() {
        return bilgi;
    }

    public void setBilgi(String bilgi) {
        this.bilgi = bilgi;
    }

    public int getFotografID() {
        return fotografID;
    }

    public void setFotografID(int fotografID) {
        this.fotografID = fotografID;
    }
}
