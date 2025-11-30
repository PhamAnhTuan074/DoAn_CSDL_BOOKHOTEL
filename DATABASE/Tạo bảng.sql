CREATE DATABASE BOOK_HOTEL;
GO
USE BOOK_HOTEL;
GO
-- =============================================
-- 1. XÓA BẢNG CŨ (THEO THỨ TỰ NGƯỢC ĐỂ TRÁNH LỖI KHÓA NGOẠI)
-- =============================================
DROP TABLE IF EXISTS ChiTietHoaDon;
DROP TABLE IF EXISTS HoaDon;
DROP TABLE IF EXISTS SD_DichVu;
DROP TABLE IF EXISTS NguoiDiCung;
DROP TABLE IF EXISTS ChiTietDatPhong;
DROP TABLE IF EXISTS DatPhong;
DROP TABLE IF EXISTS Phong;
DROP TABLE IF EXISTS DichVu;
DROP TABLE IF EXISTS LoaiPhong;
DROP TABLE IF EXISTS NhanVien;
DROP TABLE IF EXISTS KhachHang;

-- =============================================
-- 2. TẠO CÁC BẢNG DANH MỤC (KHÔNG PHỤ THUỘC)
-- =============================================

-- Bảng KhachHang
CREATE TABLE KhachHang (
    MaKH VARCHAR(10) PRIMARY KEY,
    Ho NVARCHAR(50) NOT NULL,
    TenLot NVARCHAR(50),
    Ten NVARCHAR(50) NOT NULL,
    CCCD VARCHAR(12) NOT NULL UNIQUE, -- CCCD không được trùng
    SDT VARCHAR(15) NOT NULL UNIQUE,  -- SĐT không được trùng
    NamSinh INT CHECK (NamSinh > 1900 AND NamSinh < YEAR(GETDATE())), -- Năm sinh hợp lệ
    DiaChi NVARCHAR(255),
    GioiTinh NVARCHAR(10)
);

-- Bảng NhanVien
CREATE TABLE NhanVien (
    MaNV VARCHAR(10) PRIMARY KEY,
    Ho NVARCHAR(50) NOT NULL,
    TenLot NVARCHAR(50),
    Ten NVARCHAR(50) NOT NULL,
    ChucVu NVARCHAR(50),
    SDT VARCHAR(15) NOT NULL UNIQUE
);

-- Bảng LoaiPhong
CREATE TABLE LoaiPhong (
    MaLP VARCHAR(10) PRIMARY KEY,
    TenLP NVARCHAR(100) NOT NULL,
    GiaLP DECIMAL(10, 2) NOT NULL,
    SucChua INT DEFAULT 2 CHECK (SucChua > 0),
    MoTa NVARCHAR(255),
    CONSTRAINT CHK_GiaLP CHECK (GiaLP >= 0) -- Giá phòng không âm
);

-- Bảng DichVu
CREATE TABLE DichVu (
    MaDV VARCHAR(10) PRIMARY KEY,
    TenDV NVARCHAR(100) NOT NULL,
    GiaDV DECIMAL(10, 2) NOT NULL,
    MoTa NVARCHAR(255),
    CONSTRAINT CHK_GiaDV CHECK (GiaDV >= 0) -- Giá dịch vụ không âm
);

-- =============================================
-- 3. TẠO CÁC BẢNG NGHIỆP VỤ (CÓ LIÊN KẾT KHÓA NGOẠI)
-- =============================================

-- Bảng Phong
CREATE TABLE Phong (
    MaPhong VARCHAR(10) PRIMARY KEY,
    SoPhong VARCHAR(10) NOT NULL UNIQUE, -- Số phòng (101, 102) không trùng
    Tang INT CHECK (Tang > 0),
    TrangThai NVARCHAR(50) DEFAULT N'Trống',
    MaLP VARCHAR(10) NOT NULL,
    MaNVQL VARCHAR(10),
    FOREIGN KEY (MaLP) REFERENCES LoaiPhong(MaLP),
    FOREIGN KEY (MaNVQL) REFERENCES NhanVien(MaNV)
);

-- Bảng DatPhong
CREATE TABLE DatPhong (
    MaDP VARCHAR(10) PRIMARY KEY,
    MaKH VARCHAR(10) NOT NULL,
    MaNV VARCHAR(10),
    NgayDat DATETIME DEFAULT GETDATE(),
    NgayNhan DATETIME NOT NULL,
    NgayTra DATETIME NOT NULL,
    TrangThai NVARCHAR(50) DEFAULT N'Đã xác nhận',
    FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH),
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
    -- Ràng buộc quan trọng: Ngày nhận phải trước ngày trả
    CONSTRAINT CHK_ThoiGianDat CHECK (NgayNhan < NgayTra)
);

-- Bảng ChiTietDatPhong
CREATE TABLE ChiTietDatPhong (
    MaDP VARCHAR(10) NOT NULL,
    MaP VARCHAR(10) NOT NULL,
    GiaDP DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (MaDP, MaP),
    FOREIGN KEY (MaDP) REFERENCES DatPhong(MaDP) ON DELETE CASCADE,
    FOREIGN KEY (MaP) REFERENCES Phong(MaPhong),
    -- Giá chốt tại thời điểm đặt phải >= 0
    CONSTRAINT CHK_GiaPhongLuu CHECK (GiaDP >= 0)
);

-- Bảng NguoiDiCung
CREATE TABLE NguoiDiCung (
    MaNDD VARCHAR(10) PRIMARY KEY,
    CCCD VARCHAR(12),
    HoTen NVARCHAR(100) NOT NULL,
    GioiTinh NVARCHAR(10),
    NamSinh INT,
    MoiQuanHe NVARCHAR(50),
    MaDP VARCHAR(10) NOT NULL,
    FOREIGN KEY (MaDP) REFERENCES DatPhong(MaDP) ON DELETE CASCADE
);

-- Bảng SD_DichVu (Sử dụng dịch vụ)
CREATE TABLE SD_DichVu (
    MaDP VARCHAR(10) NOT NULL,
    MaDV VARCHAR(10) NOT NULL,
    NgaySD DATETIME NOT NULL DEFAULT GETDATE(),
    SoLuong INT NOT NULL,
    PRIMARY KEY (MaDP, MaDV, NgaySD),
    FOREIGN KEY (MaDP) REFERENCES DatPhong(MaDP) ON DELETE CASCADE,
    FOREIGN KEY (MaDV) REFERENCES DichVu(MaDV),
    -- Số lượng dùng phải lớn hơn 0
    CONSTRAINT CHK_SoLuongSD CHECK (SoLuong > 0)
);

-- Bảng HoaDon
CREATE TABLE HoaDon (
    MaHD VARCHAR(10) PRIMARY KEY,
    NgayLap DATETIME DEFAULT GETDATE(),
    MaNV VARCHAR(10) NOT NULL,
    MaDP VARCHAR(10) NOT NULL UNIQUE, -- 1 đơn đặt chỉ có 1 hóa đơn
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
    FOREIGN KEY (MaDP) REFERENCES DatPhong(MaDP)
);

-- Bảng ChiTietHoaDon (Thực thể yếu - ĐÃ BỔ SUNG RÀNG BUỘC)
CREATE TABLE ChiTietHoaDon (
    MaHD VARCHAR(10) NOT NULL,
    SoThuTu INT NOT NULL,
    NoiDung NVARCHAR(255),
    SoLuong INT NOT NULL,
    DonGia DECIMAL(10, 2) NOT NULL,
    
    PRIMARY KEY (MaHD, SoThuTu),
    FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD) ON DELETE CASCADE,
    
    -- >>> RÀNG BUỘC BẠN YÊU CẦU Ở ĐÂY <<<
    CONSTRAINT CHK_CTHD_SoLuong CHECK (SoLuong > 0), -- Số lượng phải dương (ví dụ: 1, 2 cái)
    CONSTRAINT CHK_CTHD_DonGia CHECK (DonGia >= 0)   -- Đơn giá phải không âm
);