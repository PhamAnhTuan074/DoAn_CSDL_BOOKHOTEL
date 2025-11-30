package com.hotel.dao;

import com.hotel.database.DatabaseConnection;
import com.hotel.model.ChiTietHoaDon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChiTietHoaDonDAO {

    public List<ChiTietHoaDon> getByMaHD(String maHD) {
        List<ChiTietHoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietHoaDon WHERE MaHD=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,maHD);
            try (ResultSet rs = ps.executeQuery()) { while (rs.next()) list.add(mapRow(rs)); }
        } catch (Exception e){ e.printStackTrace(); }
        return list;
    }

    public boolean insert(ChiTietHoaDon ct) {
        String sql = "INSERT INTO ChiTietHoaDon(MaHD,SoThuTu,NoiDung,SoLuong,DonGia) VALUES(?,?,?,?,?)";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,ct.getMaHD());
            ps.setInt(2,ct.getSoThuTu());
            ps.setString(3,ct.getNoiDung());
            ps.setInt(4,ct.getSoLuong());
            ps.setDouble(5,ct.getDonGia());
            return ps.executeUpdate()>0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }

    public boolean update(ChiTietHoaDon ct) {
        String sql = "UPDATE ChiTietHoaDon SET NoiDung=?,SoLuong=?,DonGia=? WHERE MaHD=? AND SoThuTu=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,ct.getNoiDung());
            ps.setInt(2,ct.getSoLuong());
            ps.setDouble(3,ct.getDonGia());
            ps.setString(4,ct.getMaHD());
            ps.setInt(5,ct.getSoThuTu());
            return ps.executeUpdate()>0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }

    public boolean delete(String maHD, int soThuTu) {
        String sql = "DELETE FROM ChiTietHoaDon WHERE MaHD=? AND SoThuTu=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,maHD);
            ps.setInt(2,soThuTu);
            return ps.executeUpdate()>0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }

    private ChiTietHoaDon mapRow(ResultSet rs) throws SQLException {
        return new ChiTietHoaDon(
                rs.getString("MaHD"),
                rs.getInt("SoThuTu"),
                rs.getString("NoiDung"),
                rs.getInt("SoLuong"),
                rs.getDouble("DonGia")
        );
    }
}
