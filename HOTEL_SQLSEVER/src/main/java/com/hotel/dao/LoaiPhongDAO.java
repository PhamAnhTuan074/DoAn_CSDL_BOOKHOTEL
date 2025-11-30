package com.hotel.dao;

import com.hotel.database.DatabaseConnection;
import com.hotel.model.LoaiPhong;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoaiPhongDAO {

    public List<LoaiPhong> getAll() {
        List<LoaiPhong> list = new ArrayList<>();
        String sql = "SELECT * FROM LoaiPhong ORDER BY MaLP";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(new LoaiPhong(
                    rs.getString("MaLP"),
                    rs.getString("TenLP"),
                    rs.getDouble("GiaLP"),
                    rs.getInt("SucChua"),
                    rs.getString("MoTa")
            ));
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public LoaiPhong findById(String id) {
        String sql = "SELECT * FROM LoaiPhong WHERE MaLP=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,id);
            try (ResultSet rs=ps.executeQuery()){ if(rs.next()) return new LoaiPhong(
                    rs.getString("MaLP"),
                    rs.getString("TenLP"),
                    rs.getDouble("GiaLP"),
                    rs.getInt("SucChua"),
                    rs.getString("MoTa")
            ); }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public boolean insert(LoaiPhong lp) {
        String sql = "INSERT INTO LoaiPhong (MaLP,TenLP,GiaLP,SucChua,MoTa) VALUES(?,?,?,?,?)";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, lp.getMaLP());
            ps.setString(2, lp.getTenLP());
            ps.setDouble(3, lp.getGiaLP());
            ps.setInt(4, lp.getSucChua());
            ps.setString(5, lp.getMoTa());
            return ps.executeUpdate()>0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }

    public boolean update(LoaiPhong lp) {
        String sql = "UPDATE LoaiPhong SET TenLP=?,GiaLP=?,SucChua=?,MoTa=? WHERE MaLP=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,lp.getTenLP());
            ps.setDouble(2,lp.getGiaLP());
            ps.setInt(3,lp.getSucChua());
            ps.setString(4,lp.getMoTa());
            ps.setString(5,lp.getMaLP());
            return ps.executeUpdate()>0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }

    public boolean delete(String id) {
        String sql = "DELETE FROM LoaiPhong WHERE MaLP=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,id);
            return ps.executeUpdate()>0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }

    public List<LoaiPhong> search(String kw) {
        List<LoaiPhong> list = new ArrayList<>();
        String sql = "SELECT * FROM LoaiPhong WHERE MaLP LIKE ? OR TenLP LIKE ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            String l="%"+kw+"%";
            ps.setString(1,l); ps.setString(2,l);
            try (ResultSet rs=ps.executeQuery()){ while(rs.next()) list.add(new LoaiPhong(
                    rs.getString("MaLP"),
                    rs.getString("TenLP"),
                    rs.getDouble("GiaLP"),
                    rs.getInt("SucChua"),
                    rs.getString("MoTa")
            )); }
        } catch (Exception e){ e.printStackTrace(); }
        return list;
    }
}
