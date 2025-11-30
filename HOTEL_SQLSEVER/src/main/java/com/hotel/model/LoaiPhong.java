package com.hotel.model;

public class LoaiPhong {

    private String maLP;
    private String tenLP;
    private double giaLP;
    private int sucChua;
    private String moTa;

    public LoaiPhong() {}

    public LoaiPhong(String maLP, String tenLP, double giaLP, int sucChua, String moTa) {
        this.maLP = maLP;
        this.tenLP = tenLP;
        this.giaLP = giaLP;
        this.sucChua = sucChua;
        this.moTa = moTa;
    }

    public String getMaLP() { return maLP; }
    public void setMaLP(String maLP) { this.maLP = maLP; }

    public String getTenLP() { return tenLP; }
    public void setTenLP(String tenLP) { this.tenLP = tenLP; }

    public double getGiaLP() { return giaLP; }
    public void setGiaLP(double giaLP) { this.giaLP = giaLP; }

    public int getSucChua() { return sucChua; }
    public void setSucChua(int sucChua) { this.sucChua = sucChua; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
}
