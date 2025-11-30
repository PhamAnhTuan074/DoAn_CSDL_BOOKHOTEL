package com.hotel.model;

import java.util.Date;

public class SuDungDichVu {

    private String maDP;
    private String maDV;
    private Date ngaySD;
    private int soLuong;

    public SuDungDichVu() {}

    public SuDungDichVu(String maDP, String maDV, Date ngaySD, int soLuong) {
        this.maDP = maDP;
        this.maDV = maDV;
        this.ngaySD = ngaySD;
        this.soLuong = soLuong;
    }

    public String getMaDP() { return maDP; }
    public void setMaDP(String maDP) { this.maDP = maDP; }

    public String getMaDV() { return maDV; }
    public void setMaDV(String maDV) { this.maDV = maDV; }

    public Date getNgaySD() { return ngaySD; }
    public void setNgaySD(Date ngaySD) { this.ngaySD = ngaySD; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
}
