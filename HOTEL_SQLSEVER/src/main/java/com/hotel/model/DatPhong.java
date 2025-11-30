package com.hotel.model;

import java.util.Date;

public class DatPhong {

    private String maDP;
    private String maKH;
    private String maNV;
    private Date ngayDat;
    private Date ngayNhan;
    private Date ngayTra;
    private String trangThai;

    public DatPhong() {}

    public DatPhong(String maDP, String maKH, String maNV,
                    Date ngayDat, Date ngayNhan, Date ngayTra, String trangThai) {
        this.maDP = maDP;
        this.maKH = maKH;
        this.maNV = maNV;
        this.ngayDat = ngayDat;
        this.ngayNhan = ngayNhan;
        this.ngayTra = ngayTra;
        this.trangThai = trangThai;
    }

    public String getMaDP() { return maDP; }
    public void setMaDP(String maDP) { this.maDP = maDP; }

    public String getMaKH() { return maKH; }
    public void setMaKH(String maKH) { this.maKH = maKH; }

    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }

    public Date getNgayDat() { return ngayDat; }
    public void setNgayDat(Date ngayDat) { this.ngayDat = ngayDat; }

    public Date getNgayNhan() { return ngayNhan; }
    public void setNgayNhan(Date ngayNhan) { this.ngayNhan = ngayNhan; }

    public Date getNgayTra() { return ngayTra; }
    public void setNgayTra(Date ngayTra) { this.ngayTra = ngayTra; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}
