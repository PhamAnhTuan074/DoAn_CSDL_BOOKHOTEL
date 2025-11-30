package com.hotel.model;

public class Phong {

    private String maPhong;
    private String soPhong;
    private int tang;
    private String trangThai;
    private String maLP;
    private String maNVQL;

    public Phong() {}

    public Phong(String maPhong, String soPhong, int tang,
                 String trangThai, String maLP, String maNVQL) {
        this.maPhong = maPhong;
        this.soPhong = soPhong;
        this.tang = tang;
        this.trangThai = trangThai;
        this.maLP = maLP;
        this.maNVQL = maNVQL;
    }

    public String getMaPhong() { return maPhong; }
    public void setMaPhong(String maPhong) { this.maPhong = maPhong; }

    public String getSoPhong() { return soPhong; }
    public void setSoPhong(String soPhong) { this.soPhong = soPhong; }

    public int getTang() { return tang; }
    public void setTang(int tang) { this.tang = tang; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public String getMaLP() { return maLP; }
    public void setMaLP(String maLP) { this.maLP = maLP; }

    public String getMaNVQL() { return maNVQL; }
    public void setMaNVQL(String maNVQL) { this.maNVQL = maNVQL; }
}
