package com.hotel.dao;

import com.hotel.database.DatabaseConnection;
import com.hotel.model.Phong;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhongDAO {

    public List<Phong> getAll() {
        List<Phong> list = new ArrayList<>();
        String sql = "SELECT * FROM Phong ORDER BY MaPhong";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public Phong findById(String id) {
        String sql = "SELECT * FROM Phong WHERE MaPhong=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,id);
            try (ResultSet rs=ps.executeQuery()){ if(rs.next()) return mapRow(rs);}
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public boolean insert(Phong p) {
        String sql = "INSERT INTO Phong(MaPhong,SoPhong,Tang,TrangThai,MaLP,MaNVQL) VALUES(?,?,?,?,?,?)";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,p.getMaPhong());
            ps.setString(2,p.getSoPhong());
            ps.setInt(3,p.getTang());
            ps.setString(4,p.getTrangThai());
            ps.setString(5,p.getMaLP());
            ps.setString(6,p.getMaNVQL());
            return ps.executeUpdate()>0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }

    public boolean update(Phong p) {
        String sql = "UPDATE Phong SET SoPhong=?,Tang=?,TrangThai=?,MaLP=?,MaNVQL=? WHERE MaPhong=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,p.getSoPhong());
            ps.setInt(2,p.getTang());
            ps.setString(3,p.getTrangThai());
            ps.setString(4,p.getMaLP());
            ps.setString(5,p.getMaNVQL());
            ps.setString(6,p.getMaPhong());
            return ps.executeUpdate()>0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }

    public boolean delete(String id) {
        String sql = "DELETE FROM Phong WHERE MaPhong=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,id);
            return ps.executeUpdate()>0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }

    public List<Phong> search(String kw) {
        List<Phong> list = new ArrayList<>();
        String sql = "SELECT * FROM Phong WHERE MaPhong LIKE ? OR SoPhong LIKE ? OR TrangThai LIKE ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            String l="%"+kw+"%";
            ps.setString(1,l); ps.setString(2,l); ps.setString(3,l);
            try (ResultSet rs=ps.executeQuery()){ while(rs.next()) list.add(mapRow(rs)); }
        } catch (Exception e){ e.printStackTrace(); }
        return list;
    }

    public List<Phong> getByField(String field, String value) {
        List<Phong> list = new ArrayList<>();
        String sql = "SELECT * FROM Phong WHERE " + field + " = ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, value);
            try (ResultSet rs = ps.executeQuery()) { while (rs.next()) list.add(mapRow(rs)); }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    // === Business methods ===
    // 1. Check available rooms (TrangThai = 'Trống')
    public List<Phong> checkPhongTrong() {
        return getPhongTheoTrangThai("Trống");
    }

    public List<Phong> getPhongDangO() {
        return getPhongTheoTrangThai("Đang ở");
    }

    public List<Phong> getPhongTheoTang(int tang) {
        List<Phong> list = new ArrayList<>();
        String sql = "SELECT * FROM Phong WHERE Tang = ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, tang);
            try (ResultSet rs = ps.executeQuery()) { while (rs.next()) list.add(mapRow(rs));}
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public List<Phong> getPhongTheoLoai(String maLP) {
        return getByField("MaLP", maLP);
    }

    public List<Phong> getPhongTheoTrangThai(String trangThai) {
        List<Phong> list = new ArrayList<>();
        String sql = "SELECT * FROM Phong WHERE TrangThai = ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, trangThai);
            try (ResultSet rs = ps.executeQuery()) { while (rs.next()) list.add(mapRow(rs)); }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    private Phong mapRow(ResultSet rs) throws SQLException {
        return new Phong(
                rs.getString("MaPhong"),
                rs.getString("SoPhong"),
                rs.getInt("Tang"),
                rs.getString("TrangThai"),
                rs.getString("MaLP"),
                rs.getString("MaNVQL")
        );
    }
}
