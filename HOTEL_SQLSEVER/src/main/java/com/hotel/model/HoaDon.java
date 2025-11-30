package com.hotel.model;

import java.util.Date;

public class HoaDon {

    private String maHD;
    private Date ngayLap;
    private String maNV;
    private String maDP;

    public HoaDon() {}

    public HoaDon(String maHD, Date ngayLap, String maNV, String maDP) {
        this.maHD = maHD;
        this.ngayLap = ngayLap;
        this.maNV = maNV;
        this.maDP = maDP;
    }

    public String getMaHD() { return maHD; }
    public void setMaHD(String maHD) { this.maHD = maHD; }

    public Date getNgayLap() { return ngayLap; }
    public void setNgayLap(Date ngayLap) { this.ngayLap = ngayLap; }

    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }

    public String getMaDP() { return maDP; }
    public void setMaDP(String maDP) { this.maDP = maDP; }
}
