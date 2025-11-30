USE BOOK_HOTEL;
-- =============================================
-- XÓA DỮ LIỆU CŨ (Làm sạch Database trước khi nạp)
-- =============================================
DELETE FROM ChiTietHoaDon;
DELETE FROM HoaDon;
DELETE FROM SD_DichVu;
DELETE FROM NguoiDiCung;
DELETE FROM ChiTietDatPhong;
DELETE FROM DatPhong;
DELETE FROM Phong;
DELETE FROM DichVu;
DELETE FROM LoaiPhong;
DELETE FROM NhanVien;
DELETE FROM KhachHang;

-- =============================================
-- 1. BẢNG LOẠI PHÒNG (7 dòng)
-- =============================================
INSERT INTO LoaiPhong (MaLP, TenLP, GiaLP, SucChua, MoTa) VALUES
('LP01', N'Standard Single', 400000, 1, N'Phòng đơn tiêu chuẩn'),
('LP02', N'Standard Double', 600000, 2, N'Phòng đôi tiêu chuẩn'),
('LP03', N'Deluxe City View', 900000, 2, N'Phòng cao cấp hướng phố'),
('LP04', N'Deluxe Ocean View', 1200000, 2, N'Phòng cao cấp hướng biển'),
('LP05', N'Family Suite', 2000000, 4, N'Căn hộ gia đình 2 phòng ngủ'),
('LP06', N'Executive Suite', 3500000, 2, N'Phòng hạng sang cho doanh nhân'),
('LP07', N'Presidential', 15000000, 4, N'Phòng Tổng thống sang trọng nhất');

-- =============================================
-- 2. BẢNG DỊCH VỤ (10 dòng)
-- =============================================
INSERT INTO DichVu (MaDV, TenDV, GiaDV, MoTa) VALUES
('DV01', N'Giặt ủi thường', 30000, N'Tính theo kg'),
('DV02', N'Giặt hấp cao cấp', 100000, N'Tính theo bộ'),
('DV03', N'Ăn sáng Buffet', 150000, N'Vé người lớn'),
('DV04', N'Ăn sáng trẻ em', 80000, N'Vé trẻ em dưới 1.2m'),
('DV05', N'Thuê xe máy tay ga', 150000, N'Tính theo ngày'),
('DV06', N'Đón tiễn sân bay', 300000, N'Xe 4 chỗ/lượt'),
('DV07', N'Massage Body', 450000, N'Vé 60 phút'),
('DV08', N'Sauna & Steam', 200000, N'Vé xông hơi'),
('DV09', N'MiniBar - Bia', 40000, N'Lon 330ml'),
('DV10', N'MiniBar - Nước suối', 20000, N'Chai 500ml');

-- =============================================
-- 3. BẢNG NHÂN VIÊN (10 dòng - Đã tách tên)
-- =============================================
INSERT INTO NhanVien (MaNV, Ho, TenLot, Ten, ChucVu, SDT) VALUES
('NV01', N'Nguyễn', N'Thị', N'Mai', N'Lễ tân trưởng', '0901111222'),
('NV02', N'Trần', N'Văn', N'Hùng', N'Bảo vệ', '0902222333'),
('NV03', N'Lê', N'Thị', N'Thu', N'Buồng phòng', '0903333444'),
('NV04', N'Phạm', N'Minh', N'Tuấn', N'Quản lý', '0904444555'),
('NV05', N'Hoàng', N'Ngọc', N'Hà', N'Kế toán', '0905555666'),
('NV06', N'Đỗ', N'Văn', N'Nam', N'Bellman', '0906666777'),
('NV07', N'Vũ', N'Thị', N'Yến', N'Lễ tân', '0907777888'),
('NV08', N'Bùi', N'Quang', N'Huy', N'IT Support', '0908888999'),
('NV09', N'Đặng', N'Thị', N'Hoa', N'Buồng phòng', '0909999000'),
('NV10', N'Ngô', N'Văn', N'Quyền', N'Bếp trưởng', '0910000111');

-- =============================================
-- 4. BẢNG KHÁCH HÀNG (12 dòng - Đã tách tên)
-- =============================================
INSERT INTO KhachHang (MaKH, Ho, TenLot, Ten, CCCD, SDT, NamSinh, DiaChi, GioiTinh) VALUES
('KH01', N'Lý', N'Hải', N'Đăng', '001090000001', '0911111111', 1990, N'Quận 1, TPHCM', N'Nam'),
('KH02', N'Trần', N'Thu', N'Hương', '001090000002', '0922222222', 1995, N'Cầu Giấy, Hà Nội', N'Nữ'),
('KH03', N'John', NULL, N'Smith', 'P12345678', '0933333333', 1985, N'USA', N'Nam'),
('KH04', N'Nguyễn', N'Bá', N'Thắng', '001090000004', '0944444444', 1980, N'Đà Nẵng', N'Nam'),
('KH05', N'Phạm', N'Thanh', N'Thảo', '001090000005', '0955555555', 1998, N'Cần Thơ', N'Nữ'),
('KH06', N'Lê', N'Văn', N'Sơn', '001090000006', '0966666666', 1992, N'Hải Phòng', N'Nam'),
('KH07', N'Hoàng', N'Thị', N'Lan', '001090000007', '0977777777', 1988, N'Nha Trang', N'Nữ'),
('KH08', N'Vũ', N'Trọng', N'Phụng', '001090000008', '0988888888', 2000, N'Huế', N'Nam'),
('KH09', N'Đặng', N'Thu', N'Thủy', '001090000009', '0999999999', 1993, N'Đà Lạt', N'Nữ'),
('KH10', N'Yamada', NULL, N'Taro', 'JP87654321', '0910101010', 1982, N'Japan', N'Nam'),
('KH11', N'Trương', N'Vô', N'Kỵ', '001090000011', '0912121212', 1996, N'Bình Dương', N'Nam'),
('KH12', N'Triệu', NULL, N'Mẫn', '001090000012', '0913131313', 1997, N'Đồng Nai', N'Nữ');

-- =============================================
-- 5. BẢNG PHÒNG (15 dòng)
-- =============================================
INSERT INTO Phong (MaPhong, SoPhong, Tang, TrangThai, MaLP, MaNVQL) VALUES
('P101', '101', 1, N'Trống', 'LP01', 'NV03'),
('P102', '102', 1, N'Đang ở', 'LP01', 'NV03'),
('P103', '103', 1, N'Trống', 'LP02', 'NV03'),
('P201', '201', 2, N'Đang dọn dẹp', 'LP02', 'NV09'),
('P202', '202', 2, N'Đang ở', 'LP03', 'NV09'),
('P203', '203', 2, N'Đang ở', 'LP03', 'NV09'),
('P301', '301', 3, N'Trống', 'LP04', 'NV03'),
('P302', '302', 3, N'Đang sửa', 'LP04', 'NV03'),
('P401', '401', 4, N'Đang ở', 'LP05', 'NV09'),
('P402', '402', 4, N'Trống', 'LP05', 'NV09'),
('P501', '501', 5, N'Đang ở', 'LP06', 'NV03'),
('P601', '601', 6, N'Trống', 'LP07', 'NV09'),
('P104', '104', 1, N'Trống', 'LP01', 'NV03'),
('P105', '105', 1, N'Trống', 'LP02', 'NV03'),
('P204', '204', 2, N'Đang ở', 'LP03', 'NV09');

-- =============================================
-- 6. BẢNG ĐẶT PHÒNG (10 dòng - Quá khứ và Hiện tại)
-- =============================================
INSERT INTO DatPhong (MaDP, MaKH, MaNV, NgayDat, NgayNhan, NgayTra, TrangThai) VALUES
('DP01', 'KH01', 'NV01', '2025-10-01', '2025-10-05', '2025-10-07', N'Đã trả phòng'), -- Đã xong
('DP02', 'KH02', 'NV07', '2025-10-02', '2025-10-10', '2025-10-15', N'Đã trả phòng'), -- Đã xong
('DP03', 'KH03', 'NV01', '2025-11-01', '2025-11-20', '2025-11-22', N'Đã trả phòng'), -- Đã xong
('DP04', 'KH04', 'NV07', '2025-11-15', '2025-11-21', '2025-11-25', N'Đang ở'),      -- Đang ở P102
('DP05', 'KH05', 'NV01', '2025-11-16', '2025-11-21', '2025-11-24', N'Đang ở'),      -- Đang ở P202
('DP06', 'KH06', 'NV07', '2025-11-18', '2025-11-21', '2025-11-23', N'Đang ở'),      -- Đang ở P203
('DP07', 'KH11', 'NV01', '2025-11-19', '2025-11-21', '2025-11-26', N'Đang ở'),      -- Đang ở P401 (Vợ chồng Trương Vô Kỵ)
('DP08', 'KH10', 'NV07', '2025-11-20', '2025-11-21', '2025-11-30', N'Đang ở'),      -- Đang ở P501
('DP09', 'KH07', 'NV01', '2025-11-21', '2025-12-01', '2025-12-05', N'Đã xác nhận'), -- Sắp tới
('DP10', 'KH08', 'NV07', '2025-11-21', '2025-12-10', '2025-12-12', N'Đã xác nhận'); -- Sắp tới

-- =============================================
-- 7. BẢNG CHI TIẾT ĐẶT PHÒNG
-- =============================================
INSERT INTO ChiTietDatPhong (MaDP, MaP, GiaDP) VALUES
('DP01', 'P101', 400000),  -- KH01 ở P101 (Standard Single)
('DP02', 'P401', 2000000), -- KH02 ở P401 (Family)
('DP03', 'P102', 400000),  -- KH03 ở P102
('DP04', 'P102', 400000),  -- KH04 đang ở P102
('DP05', 'P202', 900000),  -- KH05 đang ở P202
('DP06', 'P203', 900000),  -- KH06 đang ở P203
('DP07', 'P401', 2000000), -- KH11 đang ở P401
('DP08', 'P501', 3500000), -- KH10 đang ở P501
('DP09', 'P101', 400000),  -- KH07 đặt trước P101
('DP10', 'P301', 1200000); -- KH08 đặt trước P301

-- =============================================
-- 8. BẢNG NGƯỜI ĐI CÙNG (10 dòng)
-- =============================================
INSERT INTO NguoiDiCung (MaNDD, CCCD, HoTen, GioiTinh, NamSinh, MoiQuanHe, MaDP) VALUES
('ND01', '001090999999', N'Nguyễn Văn Con', N'Nam', 2015, N'Con', 'DP02'), -- Con KH02
('ND02', '001090888888', N'Trần Thị Mẹ', N'Nữ', 1960, N'Mẹ', 'DP02'),     -- Mẹ KH02
('ND03', NULL, N'Baby Shark', N'Nam', 2022, N'Con', 'DP02'),              -- Em bé KH02
('ND04', '001090777777', N'Lê Thị Vợ', N'Nữ', 1993, N'Vợ', 'DP06'),       -- Vợ KH06
('ND05', '001090000012', N'Triệu Mẫn', N'Nữ', 1997, N'Vợ', 'DP07'),       -- Vợ KH11 (Lấy từ danh sách KH cũng được)
('ND06', NULL, N'Trương Vô Hối', N'Nam', 2020, N'Con', 'DP07'),           -- Con KH11
('ND07', 'JP12345678', N'Yamada Hanako', N'Nữ', 1985, N'Vợ', 'DP08'),     -- Vợ KH10
('ND08', '001090111222', N'Bạn Gái', N'Nữ', 1998, N'Bạn', 'DP05'),        -- Bạn KH05
('ND09', '001090333444', N'Đồng Nghiệp A', N'Nam', 1990, N'Đồng nghiệp', 'DP09'),
('ND10', '001090555666', N'Đồng Nghiệp B', N'Nữ', 1992, N'Đồng nghiệp', 'DP09');

-- =============================================
-- 9. BẢNG SỬ DỤNG DỊCH VỤ (15 dòng)
-- =============================================
INSERT INTO SD_DichVu (MaDP, MaDV, NgaySD, SoLuong) VALUES
('DP01', 'DV01', '2025-10-06', 2), -- DP01 giặt 2kg
('DP01', 'DV10', '2025-10-05', 1), -- DP01 uống 1 nước suối
('DP02', 'DV06', '2025-10-02', 1), -- DP02 đón sân bay
('DP02', 'DV03', '2025-10-11', 3), -- DP02 ăn sáng 3 người
('DP02', 'DV03', '2025-10-12', 3), -- DP02 ăn sáng 3 người (ngày tiếp theo)
('DP02', 'DV06', '2025-10-15', 1), -- DP02 tiễn sân bay
('DP03', 'DV09', '2025-11-21', 2), -- DP03 uống 2 bia
('DP04', 'DV05', '2025-11-22', 1), -- DP04 thuê xe máy
('DP07', 'DV07', '2025-11-22', 2), -- DP07 massage 2 người
('DP07', 'DV08', '2025-11-22', 2), -- DP07 xông hơi 2 người
('DP08', 'DV06', '2025-11-20', 1), -- DP08 đón sân bay
('DP08', 'DV03', '2025-11-21', 2), -- DP08 ăn sáng 2 người
('DP08', 'DV01', '2025-11-22', 5), -- DP08 giặt 5kg
('DP05', 'DV10', '2025-11-21', 1), -- DP05 uống nước
('DP06', 'DV09', '2025-11-21', 4); -- DP06 uống 4 bia

-- =============================================
-- 10. BẢNG HÓA ĐƠN (Cho 3 đơn đã trả phòng DP01, DP02, DP03)
-- =============================================
INSERT INTO HoaDon (MaHD, NgayLap, MaNV, MaDP) VALUES
('HD01', '2025-10-07', 'NV01', 'DP01'),
('HD02', '2025-10-15', 'NV07', 'DP02'),
('HD03', '2025-11-22', 'NV01', 'DP03');

-- =============================================
-- 11. BẢNG CHI TIẾT HÓA ĐƠN (Nhập liệu dựa trên tính toán thực tế)
-- =============================================

-- >>> Chi tiết cho HD01 (DP01): 2 đêm (400k) + Giặt 2kg (30k) + 1 Nước (20k)
INSERT INTO ChiTietHoaDon (MaHD, SoThuTu, NoiDung, SoLuong, DonGia) VALUES
('HD01', 1, N'Tiền phòng P101 (2 đêm)', 2, 400000),
('HD01', 2, N'Dịch vụ Giặt ủi thường', 2, 30000),
('HD01', 3, N'MiniBar - Nước suối', 1, 20000);

-- >>> Chi tiết cho HD02 (DP02): 5 đêm (2tr) + 2 lượt xe (300k) + 6 vé ăn sáng (150k)
INSERT INTO ChiTietHoaDon (MaHD, SoThuTu, NoiDung, SoLuong, DonGia) VALUES
('HD02', 1, N'Tiền phòng P401 (5 đêm)', 5, 2000000),
('HD02', 2, N'Đón tiễn sân bay', 2, 300000),
('HD02', 3, N'Ăn sáng Buffet', 6, 150000);

-- >>> Chi tiết cho HD03 (DP03): 2 đêm (400k) + 2 bia (40k)
INSERT INTO ChiTietHoaDon (MaHD, SoThuTu, NoiDung, SoLuong, DonGia) VALUES
('HD03', 1, N'Tiền phòng P102 (2 đêm)', 2, 400000),
('HD03', 2, N'MiniBar - Bia', 2, 40000);