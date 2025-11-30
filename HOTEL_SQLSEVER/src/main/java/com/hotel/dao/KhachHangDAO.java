package com.hotel.dao;

import com.hotel.database.DatabaseConnection;
import com.hotel.model.KhachHang;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {

    public List<KhachHang> getAll() {
        List<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang ORDER BY MaKH";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public KhachHang findById(String id) {
        String sql = "SELECT * FROM KhachHang WHERE MaKH = ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) { if (rs.next()) return mapRow(rs); }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }
    
    public KhachHang findByUser(String soDienThoai) {
        String sql = "SELECT * FROM KhachHang WHERE SDT = ?";
        System.out.println("Tìm SDT: " + soDienThoai);

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, soDienThoai.trim());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Tìm thấy KH!");
                    return mapRow(rs);
                } else {
                    System.out.println("KH không tồn tại trong DB!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public boolean insert(KhachHang k) {
        String sql = "INSERT INTO KhachHang(MaKH,Ho,TenLot,Ten,CCCD,SDT,NamSinh,DiaChi,GioiTinh) VALUES(?,?,?,?,?,?,?,?,?)";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, k.getMaKH());
            ps.setString(2, k.getHo());
            ps.setString(3, k.getTenLot());
            ps.setString(4, k.getTen());
            ps.setString(5, k.getCCCD());
            ps.setString(6, k.getSDT());
            ps.setInt(7, k.getNamSinh());
            ps.setString(8, k.getDiaChi());
            ps.setString(9, k.getGioiTinh());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public boolean update(KhachHang k) {
        String sql = "UPDATE KhachHang SET Ho=?,TenLot=?,Ten=?,CCCD=?,SDT=?,NamSinh=?,DiaChi=?,GioiTinh=? WHERE MaKH=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, k.getHo());
            ps.setString(2, k.getTenLot());
            ps.setString(3, k.getTen());
            ps.setString(4, k.getCCCD());
            ps.setString(5, k.getSDT());
            ps.setInt(6, k.getNamSinh());
            ps.setString(7, k.getDiaChi());
            ps.setString(8, k.getGioiTinh());
            ps.setString(9, k.getMaKH());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public boolean delete(String id) {
        String sql = "DELETE FROM KhachHang WHERE MaKH=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public List<KhachHang> search(String kw) {
        List<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang WHERE MaKH LIKE ? OR Ho LIKE ? OR Ten LIKE ? OR CCCD LIKE ? OR SĐT LIKE ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            String like = "%" + kw + "%";
            for (int i=1;i<=5;i++) ps.setString(i, like);
            try (ResultSet rs = ps.executeQuery()) { while (rs.next()) list.add(mapRow(rs)); }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public List<KhachHang> getByField(String field, String value) {
        List<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang WHERE " + field + " = ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, value);
            try (ResultSet rs = ps.executeQuery()) { while (rs.next()) list.add(mapRow(rs)); }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    private KhachHang mapRow(ResultSet rs) throws SQLException {
        return new KhachHang(
                rs.getString("MaKH"),
                rs.getString("Ho"),
                rs.getString("TenLot"),
                rs.getString("Ten"),
                rs.getString("CCCD"),
                rs.getString("SDT"),
                rs.getInt("NamSinh"),
                rs.getString("DiaChi"),
                rs.getString("GioiTinh")
        );
    }
}
