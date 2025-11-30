USE BOOK_HOTEL;
GO

-------------------------------------------------------
-- 1. Danh sách PHÒNG TRỐNG theo khoảng ngày
-------------------------------------------------------
DECLARE @NgayNhan DATETIME = '2025-12-01';
DECLARE @NgayTra  DATETIME = '2025-12-03';

SELECT 
    p.MaPhong,
    p.SoPhong,
    p.Tang,
    p.TrangThai,
    lp.TenLP   AS TenLoaiPhong,
    lp.GiaLP   AS GiaCoBan
FROM Phong p
JOIN LoaiPhong lp ON p.MaLP = lp.MaLP
WHERE p.TrangThai = N'Trống'
  AND p.MaPhong NOT IN (
        SELECT ctdp.MaP
        FROM ChiTietDatPhong ctdp
        JOIN DatPhong dp ON ctdp.MaDP = dp.MaDP
        WHERE dp.TrangThai <> N'Đã hủy'
          AND @NgayNhan < dp.NgayTra
          AND @NgayTra  > dp.NgayNhan
  );
GO


-------------------------------------------------------
-- 2. Doanh thu theo THÁNG 
-------------------------------------------------------
SELECT 
    YEAR(hd.NgayLap)  AS Nam,
    MONTH(hd.NgayLap) AS Thang,
    SUM(ct.SoLuong * ct.DonGia) AS TongDoanhThu
FROM HoaDon hd
JOIN ChiTietHoaDon ct ON hd.MaHD = ct.MaHD
GROUP BY YEAR(hd.NgayLap), MONTH(hd.NgayLap)
ORDER BY Nam, Thang;
GO


-------------------------------------------------------
-- 3. Doanh thu theo NĂM
-------------------------------------------------------
SELECT 
    YEAR(hd.NgayLap) AS Nam,
    SUM(ct.SoLuong * ct.DonGia) AS TongDoanhThu
FROM HoaDon hd
JOIN ChiTietHoaDon ct ON hd.MaHD = ct.MaHD
GROUP BY YEAR(hd.NgayLap)
ORDER BY Nam;
GO


-------------------------------------------------------
-- 4. Thống kê DỊCH VỤ dùng nhiều nhất (TOP 10)
-------------------------------------------------------
SELECT TOP 10
    dv.MaDV,
    dv.TenDV,
    SUM(sd.SoLuong)              AS TongSoLuong,
    SUM(sd.SoLuong * dv.GiaDV)   AS TongDoanhThu
FROM DichVu dv
JOIN SD_DichVu sd ON dv.MaDV = sd.MaDV
GROUP BY dv.MaDV, dv.TenDV
ORDER BY TongSoLuong DESC, TongDoanhThu DESC;
GO


-------------------------------------------------------
-- 5. Top KHÁCH HÀNG thân thiết
--    Tiêu chí: số lần đặt 
-------------------------------------------------------
SELECT TOP 10
    kh.MaKH,
    (kh.Ho + N' ' + ISNULL(kh.TenLot, N'') + N' ' + kh.Ten) AS HoTen,
    COUNT(DISTINCT dp.MaDP) AS SoLanDat
FROM KhachHang kh
JOIN DatPhong dp ON kh.MaKH = dp.MaKH
WHERE dp.TrangThai <> N'Đã hủy' 
GROUP BY kh.MaKH, kh.Ho, kh.TenLot, kh.Ten
ORDER BY SoLanDat DESC;
GO