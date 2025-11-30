package com.hotel.model;

public class NhanVien {

    private String maNV;
    private String ho;
    private String tenLot;
    private String ten;
    private String chucVu;
    private String sdt;

    public NhanVien() {}

    public NhanVien(String maNV, String ho, String tenLot, String ten, String chucVu, String sdt) {
        this.maNV = maNV;
        this.ho = ho;
        this.tenLot = tenLot;
        this.ten = ten;
        this.chucVu = chucVu;
        this.sdt = sdt;
    }

    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }

    public String getHo() { return ho; }
    public void setHo(String ho) { this.ho = ho; }

    public String getTenLot() { return tenLot; }
    public void setTenLot(String tenLot) { this.tenLot = tenLot; }

    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }

    public String getChucVu() { return chucVu; }
    public void setChucVu(String chucVu) { this.chucVu = chucVu; }

    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }
}
