package com.hotel.model;

public class ChiTietHoaDon {

    private String maHD;
    private int soThuTu;
    private String noiDung;
    private int soLuong;
    private double donGia;

    public ChiTietHoaDon() {}

    public ChiTietHoaDon(String maHD, int soThuTu, String noiDung, int soLuong, double donGia) {
        this.maHD = maHD;
        this.soThuTu = soThuTu;
        this.noiDung = noiDung;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getMaHD() { return maHD; }
    public void setMaHD(String maHD) { this.maHD = maHD; }

    public int getSoThuTu() { return soThuTu; }
    public void setSoThuTu(int soThuTu) { this.soThuTu = soThuTu; }

    public String getNoiDung() { return noiDung; }
    public void setNoiDung(String noiDung) { this.noiDung = noiDung; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { this.donGia = donGia; }
}
