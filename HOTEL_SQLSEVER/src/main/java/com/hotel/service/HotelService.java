package com.hotel.service;

import com.hotel.dao.*;
import com.hotel.model.*;

import java.util.List;

public class HotelService {

    private final KhachHangDAO khDAO = new KhachHangDAO();
    private final NhanVienDAO nvDAO = new NhanVienDAO();
    private final LoaiPhongDAO lpDAO = new LoaiPhongDAO();
    private final DichVuDAO dvDAO = new DichVuDAO();
    private final PhongDAO pDAO = new PhongDAO();
    private final DatPhongDAO dpDAO = new DatPhongDAO();
    private final ChiTietDatPhongDAO ctdpDAO = new ChiTietDatPhongDAO();
    private final NguoiDiCungDAO ndcDAO = new NguoiDiCungDAO();
    private final SuDungDichVuDAO sdDAO = new SuDungDichVuDAO();
    private final HoaDonDAO hdDAO = new HoaDonDAO();
    private final ChiTietHoaDonDAO cthdDAO = new ChiTietHoaDonDAO();

    // =============================
    // KHÁCH HÀNG
    // =============================
    public boolean addKhachHang(KhachHang k) {
        return khDAO.insert(k);
    }

    public List<KhachHang> getAllKhachHang() {
        return khDAO.getAll();
    }

    public KhachHang findKhachHang(String id) {
        return khDAO.findById(id);
    }

    // =============================
    // PHÒNG
    // =============================
    public List<Phong> getAllPhong() {
        return pDAO.getAll();
    }

    public List<Phong> getPhongTrong() {
        return pDAO.getPhongTheoTrangThai("Trống");
    }

    public Phong findPhong(String maP) {
        return pDAO.findById(maP);
    }

    // =============================
    // ĐẶT PHÒNG
    // =============================
    public boolean datPhong(DatPhong dp, ChiTietDatPhong ctdp) {
        boolean ok1 = dpDAO.insert(dp);
        boolean ok2 = ctdpDAO.insert(ctdp);
        return ok1 && ok2;
    }

    public List<DatPhong> getAllDatPhong() {
        return dpDAO.getAll();
    }

    // =============================
    // DỊCH VỤ
    // =============================
    public List<DichVu> getAllDichVu() {
        return dvDAO.getAll();
    }

}
