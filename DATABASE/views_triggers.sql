USE BOOK_HOTEL;
GO

-------------------------------------------------------
-- 1. VIEW: Lịch phòng tổng hợp
-------------------------------------------------------
IF OBJECT_ID('dbo.vw_LichPhong', 'V') IS NOT NULL
    DROP VIEW dbo.vw_LichPhong;
GO

CREATE VIEW dbo.vw_LichPhong
AS
SELECT 
    p.MaPhong,
    p.SoPhong,
    p.Tang,
    p.TrangThai        AS TrangThaiPhong,
    lp.TenLP           AS TenLoaiPhong,
    lp.GiaLP           AS GiaCoBan,
    dp.MaDP,
    dp.NgayDat,
    dp.NgayNhan,
    dp.NgayTra,
    dp.TrangThai       AS TrangThaiDat,
    kh.MaKH,
    (kh.Ho + N' ' + ISNULL(kh.TenLot, N'') + N' ' + kh.Ten) AS TenKhachHang
FROM Phong p
JOIN LoaiPhong lp ON p.MaLP = lp.MaLP
LEFT JOIN ChiTietDatPhong ctdp ON p.MaPhong = ctdp.MaP
LEFT JOIN DatPhong dp ON ctdp.MaDP = dp.MaDP
LEFT JOIN KhachHang kh ON dp.MaKH = kh.MaKH;
GO


-------------------------------------------------------
-- 2. VIEW: Doanh thu theo tháng
-------------------------------------------------------
IF OBJECT_ID('dbo.vw_DoanhThuThang', 'V') IS NOT NULL
    DROP VIEW dbo.vw_DoanhThuThang;
GO

CREATE VIEW dbo.vw_DoanhThuThang
AS
SELECT 
    YEAR(hd.NgayLap)  AS Nam,
    MONTH(hd.NgayLap) AS Thang,
    SUM(ct.SoLuong * ct.DonGia) AS TongDoanhThu
FROM HoaDon hd
JOIN ChiTietHoaDon ct ON hd.MaHD = ct.MaHD
GROUP BY YEAR(hd.NgayLap), MONTH(hd.NgayLap);
GO


-------------------------------------------------------
-- 3. VIEW: Thống kê dịch vụ
-------------------------------------------------------
IF OBJECT_ID('dbo.vw_ThongKeDichVu', 'V') IS NOT NULL
    DROP VIEW dbo.vw_ThongKeDichVu;
GO

CREATE VIEW dbo.vw_ThongKeDichVu
AS
SELECT 
    dv.MaDV,
    dv.TenDV,
    SUM(sd.SoLuong)            AS TongSoLuong,
    SUM(sd.SoLuong * dv.GiaDV) AS TongDoanhThu
FROM DichVu dv
JOIN SD_DichVu sd ON dv.MaDV = sd.MaDV
GROUP BY dv.MaDV, dv.TenDV;
GO


-------------------------------------------------------
-- 4. TRIGGER:  Cập nhật trạng thái phòng -> "Đang ở"
-------------------------------------------------------
IF OBJECT_ID('dbo.trg_ChiTietDatPhong_Insert', 'TR') IS NOT NULL
    DROP TRIGGER dbo.trg_ChiTietDatPhong_Insert;
GO

CREATE TRIGGER dbo.trg_ChiTietDatPhong_Insert
ON ChiTietDatPhong
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE p
    SET p.TrangThai = N'Đang ở'
    FROM Phong p
    JOIN inserted i ON p.MaPhong = i.MaP
    WHERE p.TrangThai = N'Trống';
END;
GO


-------------------------------------------------------
-- 5. TRIGGER: Cập nhật trạng thái phòng -> "Trống"
-------------------------------------------------------
IF OBJECT_ID('dbo.trg_HoaDon_Insert', 'TR') IS NOT NULL
    DROP TRIGGER dbo.trg_HoaDon_Insert;
GO

CREATE TRIGGER dbo.trg_HoaDon_Insert
ON HoaDon
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE p
    SET p.TrangThai = N'Trống'
    FROM Phong p
    JOIN ChiTietDatPhong ctdp ON p.MaPhong = ctdp.MaP
    JOIN inserted i ON ctdp.MaDP = i.MaDP;
END;
GO
