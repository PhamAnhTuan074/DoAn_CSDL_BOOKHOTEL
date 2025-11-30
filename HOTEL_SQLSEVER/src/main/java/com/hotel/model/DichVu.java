package com.hotel.model;

public class DichVu {

    private String maDV;
    private String tenDV;
    private double giaDV;
    private String moTa;

    public DichVu() {}

    public DichVu(String maDV, String tenDV, double giaDV, String moTa) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.giaDV = giaDV;
        this.moTa = moTa;
    }

    public String getMaDV() { return maDV; }
    public void setMaDV(String maDV) { this.maDV = maDV; }

    public String getTenDV() { return tenDV; }
    public void setTenDV(String tenDV) { this.tenDV = tenDV; }

    public double getGiaDV() { return giaDV; }
    public void setGiaDV(double giaDV) { this.giaDV = giaDV; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
}
