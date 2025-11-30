package com.hotel.model;

public class ChiTietDatPhong {

    private String maDP;
    private String maPhong;
    private double giaDP;

    public ChiTietDatPhong() {}

    public ChiTietDatPhong(String maDP, String maPhong, double giaDP) {
        this.maDP = maDP;
        this.maPhong = maPhong;
        this.giaDP = giaDP;
    }

    public String getMaDP() { return maDP; }
    public void setMaDP(String maDP) { this.maDP = maDP; }

    public String getMaPhong() { return maPhong; }
    public void setMaPhong(String maPhong) { this.maPhong = maPhong; }

    public double getGiaDP() { return giaDP; }
    public void setGiaDP(double giaDP) { this.giaDP = giaDP; }
}
