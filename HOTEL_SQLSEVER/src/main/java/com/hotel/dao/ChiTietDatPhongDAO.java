package com.hotel.dao;

import com.hotel.database.DatabaseConnection;
import com.hotel.model.ChiTietDatPhong;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChiTietDatPhongDAO {

    public List<ChiTietDatPhong> getAll() {
        List<ChiTietDatPhong> list = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietDatPhong";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public List<ChiTietDatPhong> findByMaDP(String maDP) {
        List<ChiTietDatPhong> list = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietDatPhong WHERE MaDP=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,maDP);
            try (ResultSet rs=ps.executeQuery()){ while(rs.next()) list.add(mapRow(rs)); }
        } catch (Exception e){ e.printStackTrace(); }
        return list;
    }

    public boolean insert(ChiTietDatPhong ct) {
        String sql = "INSERT INTO ChiTietDatPhong(MaDP,MaP,GiaDP) VALUES(?,?,?)";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,ct.getMaDP());
            ps.setString(2,ct.getMaPhong());
            ps.setDouble(3,ct.getGiaDP());
            return ps.executeUpdate()>0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }

    public boolean update(ChiTietDatPhong ct) {
        String sql = "UPDATE ChiTietDatPhong SET GiaDP=? WHERE MaDP=? AND MaP=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setDouble(1,ct.getGiaDP());
            ps.setString(2,ct.getMaDP());
            ps.setString(3,ct.getMaPhong());
            return ps.executeUpdate()>0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }

    public boolean delete(String maDP, String maP) {
        String sql = "DELETE FROM ChiTietDatPhong WHERE MaDP=? AND MaP=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,maDP);
            ps.setString(2,maP);
            return ps.executeUpdate()>0;
        } catch (Exception e){ e.printStackTrace(); }
        return false; 
    }

    private ChiTietDatPhong mapRow(ResultSet rs) throws SQLException {
        return new ChiTietDatPhong(
                rs.getString("MaDP"),
                rs.getString("MaP"),
                rs.getDouble("GiaDP")
        );
    }
}
