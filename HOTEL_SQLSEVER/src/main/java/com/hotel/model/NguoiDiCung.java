package com.hotel.model;

public class NguoiDiCung {

    private String maNDD;
    private String cccd;
    private String hoTen;
    private String gioiTinh;
    private int namSinh;
    private String moiQuanHe;
    private String maDP;

    public NguoiDiCung() {
    }

    public NguoiDiCung(String maNDD, String cccd, String hoTen, String gioiTinh,
                       int namSinh, String moiQuanHe, String maDP) {
        this.maNDD = maNDD;
        this.cccd = cccd;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.namSinh = namSinh;
        this.moiQuanHe = moiQuanHe;
        this.maDP = maDP;
    }

    public String getMaNDD() { return maNDD; }
    public void setMaNDD(String maNDD) { this.maNDD = maNDD; }

    public String getCCCD() { return cccd; }
    public void setCCCD(String cccd) { this.cccd = cccd; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }

    public int getNamSinh() { return namSinh; }
    public void setNamSinh(int namSinh) { this.namSinh = namSinh; }

    public String getMoiQuanHe() { return moiQuanHe; }
    public void setMoiQuanHe(String moiQuanHe) { this.moiQuanHe = moiQuanHe; }

    public String getMaDP() { return maDP; }
    public void setMaDP(String maDP) { this.maDP = maDP; }
}
