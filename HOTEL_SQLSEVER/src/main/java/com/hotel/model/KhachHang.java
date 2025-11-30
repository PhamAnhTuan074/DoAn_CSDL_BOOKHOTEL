package com.hotel.model;

public class KhachHang {

    private String maKH;
    private String ho;
    private String tenLot;
    private String ten;
    private String cccd;
    private String sdt;
    private int namSinh;
    private String diaChi;
    private String gioiTinh;

    public KhachHang() {
    }

    public KhachHang(String maKH, String ho, String tenLot, String ten,
                     String cccd, String sdt, int namSinh, String diaChi, String gioiTinh) {
        this.maKH = maKH;
        this.ho = ho;
        this.tenLot = tenLot;
        this.ten = ten;
        this.cccd = cccd;
        this.sdt = sdt;
        this.namSinh = namSinh;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
    }

    public String getMaKH() { return maKH; }
    public void setMaKH(String maKH) { this.maKH = maKH; }

    public String getHo() { return ho; }
    public void setHo(String ho) { this.ho = ho; }

    public String getTenLot() { return tenLot; }
    public void setTenLot(String tenLot) { this.tenLot = tenLot; }

    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }

    public String getCCCD() { return cccd; }
    public void setCCCD(String cccd) { this.cccd = cccd; }

    public String getSDT() { return sdt; }
    public void setSDT(String sdt) { this.sdt = sdt; }

    public int getNamSinh() { return namSinh; }
    public void setNamSinh(int namSinh) { this.namSinh = namSinh; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }
}
