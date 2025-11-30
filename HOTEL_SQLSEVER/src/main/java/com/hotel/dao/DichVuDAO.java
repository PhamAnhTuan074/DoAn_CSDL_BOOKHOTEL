package com.hotel.dao;

import com.hotel.database.DatabaseConnection;
import com.hotel.model.DichVu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DichVuDAO {
    public List<DichVu> getAll() {
        List<DichVu> list = new ArrayList<>();
        String sql = "SELECT * FROM DichVu ORDER BY MaDV";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(new DichVu(
                    rs.getString("MaDV"),
                    rs.getString("TenDV"),
                    rs.getDouble("GiaDV"),
                    rs.getString("MoTa")
            ));
        } catch (Exception e){ e.printStackTrace(); }
        return list;
    }

    public DichVu findById(String id) {
        String sql = "SELECT * FROM DichVu WHERE MaDV=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,id);
            try (ResultSet rs=ps.executeQuery()){ if(rs.next()) return new DichVu(
                    rs.getString("MaDV"),
                    rs.getString("TenDV"),
                        rs.getDouble("GiaDV"),
                    rs.getString("MoTa")
            ); }
        } catch (Exception e){ e.printStackTrace(); }
        return null;
    }

    public boolean insert(DichVu dv) {
        String sql = "INSERT INTO DichVu(MaDV,TenDV,GiaDV,MoTa) VALUES(?,?,?,?)";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,dv.getMaDV());
            ps.setString(2,dv.getTenDV());
            ps.setDouble(3,dv.getGiaDV());
            ps.setString(4,dv.getMoTa());
            return ps.executeUpdate()>0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }

    public boolean update(DichVu dv) {
        String sql = "UPDATE DichVu SET TenDV=?,GiaDV=?,MoTa=? WHERE MaDV=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,dv.getTenDV());
            ps.setDouble(2,dv.getGiaDV());
            ps.setString(3,dv.getMoTa());
            ps.setString(4,dv.getMaDV());
            return ps.executeUpdate()>0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }

    public boolean delete(String id) {
        String sql = "DELETE FROM DichVu WHERE MaDV=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,id);
            return ps.executeUpdate()>0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }

    public List<DichVu> search(String kw) {
        List<DichVu> list = new ArrayList<>();
        String sql = "SELECT * FROM DichVu WHERE MaDV LIKE ? OR TenDV LIKE ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            String l="%"+kw+"%";
            ps.setString(1,l); ps.setString(2,l);
            try (ResultSet rs=ps.executeQuery()){ while(rs.next()) list.add(new DichVu(
                    rs.getString("MaDV"),
                    rs.getString("TenDV"),
                    rs.getDouble("GiaDV"),
                    rs.getString("MoTa")
            )); }
        } catch (Exception e){ e.printStackTrace(); }
        return list;
    }
}
