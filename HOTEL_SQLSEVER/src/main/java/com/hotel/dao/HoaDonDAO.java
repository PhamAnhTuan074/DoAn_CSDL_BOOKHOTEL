package com.hotel.dao;

import com.hotel.database.DatabaseConnection;
import com.hotel.model.HoaDon;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonDAO {

    public List<HoaDon> getAll() {
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon ORDER BY NgayLap DESC";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (Exception e){ e.printStackTrace(); }
        return list;
    }

    public HoaDon findById(String id) {
        String sql = "SELECT * FROM HoaDon WHERE MaHD=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,id);
            try (ResultSet rs=ps.executeQuery()){ if(rs.next()) return mapRow(rs); }
        } catch (Exception e){ e.printStackTrace(); }
        return null;
    }

    public boolean insert(HoaDon h) {
        String sql = "INSERT INTO HoaDon(MaHD,NgayLap,MaNV,MaDP) VALUES(?,?,?,?)";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,h.getMaHD());
            ps.setTimestamp(2,new Timestamp(h.getNgayLap().getTime()));
            ps.setString(3,h.getMaNV());
            ps.setString(4,h.getMaDP());
            return ps.executeUpdate()>0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }

    public boolean update(HoaDon h) {
        String sql = "UPDATE HoaDon SET NgayLap=?,MaNV=?,MaDP=? WHERE MaHD=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setTimestamp(1,new Timestamp(h.getNgayLap().getTime()));
            ps.setString(2,h.getMaNV());
            ps.setString(3,h.getMaDP());
            ps.setString(4,h.getMaHD());
            return ps.executeUpdate()>0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }

    public boolean delete(String id) {
        String sql = "DELETE FROM HoaDon WHERE MaHD=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,id);
            return ps.executeUpdate()>0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }

    private HoaDon mapRow(ResultSet rs) throws SQLException {
        return new HoaDon(
                rs.getString("MaHD"),
                rs.getTimestamp("NgayLap"),
                rs.getString("MaNV"),
                rs.getString("MaDP")
        );
    }
}
