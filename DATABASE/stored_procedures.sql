USE BOOK_HOTEL;
GO

-------------------------------------------------------
-- 1. FUNCTION: Tính tổng tiền hóa đơn từ ChiTietHoaDon
-------------------------------------------------------
IF OBJECT_ID('dbo.fn_TinhTongTienHoaDon', 'FN') IS NOT NULL
    DROP FUNCTION dbo.fn_TinhTongTienHoaDon;
GO

CREATE FUNCTION dbo.fn_TinhTongTienHoaDon (
    @MaHD VARCHAR(10)
)
RETURNS DECIMAL(18,2)
AS
BEGIN
    DECLARE @Tong DECIMAL(18,2);

    SELECT @Tong = SUM(SoLuong * DonGia)
    FROM ChiTietHoaDon
    WHERE MaHD = @MaHD;

    RETURN ISNULL(@Tong, 0);
END;
GO


-------------------------------------------------------
-- 2. PROC: Trả về tổng tiền 1 hóa đơn
-------------------------------------------------------
IF OBJECT_ID('dbo.sp_TinhTongTienHoaDon', 'P') IS NOT NULL
    DROP PROCEDURE dbo.sp_TinhTongTienHoaDon;
GO

CREATE PROCEDURE dbo.sp_TinhTongTienHoaDon
    @MaHD VARCHAR(10)
AS
BEGIN
    SET NOCOUNT ON;

    SELECT 
        MaHD     = @MaHD,
        TongTien = dbo.fn_TinhTongTienHoaDon(@MaHD);
END;
GO


-------------------------------------------------------
-- 3. PROC: Kiểm tra phòng trống cho khoảng ngày
--      RETURN 1 = đặt được, RETURN 0 = không đặt được
-------------------------------------------------------
IF OBJECT_ID('dbo.sp_KiemTraPhongTrong', 'P') IS NOT NULL
    DROP PROCEDURE dbo.sp_KiemTraPhongTrong;
GO

CREATE PROCEDURE dbo.sp_KiemTraPhongTrong
    @MaPhong    VARCHAR(10),
    @NgayNhanMoi DATETIME,
    @NgayTraMoi  DATETIME
AS
BEGIN
    SET NOCOUNT ON;

    IF NOT EXISTS (
        SELECT 1
        FROM Phong
        WHERE MaPhong = @MaPhong
          AND TrangThai = N'Trống'
    )
    BEGIN
        PRINT N'Phòng hiện không ở trạng thái Trống.';
        RETURN 0;
    END

    IF EXISTS (
        SELECT 1
        FROM ChiTietDatPhong ctdp
        JOIN DatPhong dp ON ctdp.MaDP = dp.MaDP
        WHERE ctdp.MaP = @MaPhong
          AND dp.TrangThai <> N'Đã hủy'
          AND @NgayNhanMoi < dp.NgayTra
          AND @NgayTraMoi  > dp.NgayNhan
    )
    BEGIN
        PRINT N'Phòng đã có người đặt trùng khoảng thời gian này.';
        RETURN 0;
    END

    PRINT N'Phòng TRỐNG, có thể đặt.';
    RETURN 1;
END;
GO


-------------------------------------------------------
-- 4. PROC: HỦY đặt phòng 
-------------------------------------------------------
IF OBJECT_ID('dbo.sp_HuyDatPhong', 'P') IS NOT NULL
    DROP PROCEDURE dbo.sp_HuyDatPhong;
GO

CREATE PROCEDURE dbo.sp_HuyDatPhong
    @MaDP VARCHAR(10)
AS
BEGIN
    SET NOCOUNT ON;

    IF NOT EXISTS (SELECT 1 FROM DatPhong WHERE MaDP = @MaDP)
    BEGIN
        RAISERROR(N'Mã đặt phòng không tồn tại.', 16, 1);
        RETURN;
    END

    UPDATE DatPhong
    SET TrangThai = N'Đã hủy'
    WHERE MaDP = @MaDP;

    PRINT N'Đã hủy đặt phòng thành công.';
END;
GO
