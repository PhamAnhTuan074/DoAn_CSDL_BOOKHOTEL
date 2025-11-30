package com.hotel.dao;

import com.hotel.database.DatabaseConnection;
import com.hotel.model.NguoiDiCung;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NguoiDiCungDAO {

    public List<NguoiDiCung> getAll() {
        List<NguoiDiCung> list = new ArrayList<>();
        String sql = "SELECT * FROM NguoiDiCung";
        try (Connection c=DatabaseConnection.getConnection();
             PreparedStatement ps=c.prepareStatement(sql);
             ResultSet rs=ps.executeQuery()){
            while(rs.next()) list.add(mapRow(rs));
        } catch(Exception e){ e.printStackTrace(); }
        return list;
    }

    public List<NguoiDiCung> findByMaDP(String maDP) {
        List<NguoiDiCung> list = new ArrayList<>();
        String sql = "SELECT * FROM NguoiDiCung WHERE MaDP=?";
        try (Connection c=DatabaseConnection.getConnection();
             PreparedStatement ps=c.prepareStatement(sql)) {
            ps.setString(1,maDP);
            try (ResultSet rs=ps.executeQuery()){ while(rs.next()) list.add(mapRow(rs)); }
        } catch(Exception e){ e.printStackTrace(); }
        return list;
    }

    public boolean insert(NguoiDiCung n) {
        String sql = "INSERT INTO NguoiDiCung(MaNDD,CCCD,HoTen,GioiTinh,NamSinh,MoiQuanHe,MaDP) VALUES(?,?,?,?,?,?,?)";
        try (Connection c=DatabaseConnection.getConnection();
             PreparedStatement ps=c.prepareStatement(sql)) {
            ps.setString(1,n.getMaNDD());
            ps.setString(2,n.getCCCD());
            ps.setString(3,n.getHoTen());
            ps.setString(4,n.getGioiTinh());
            ps.setInt(5,n.getNamSinh());
            ps.setString(6,n.getMoiQuanHe());
            ps.setString(7,n.getMaDP());
            return ps.executeUpdate()>0;
        } catch(Exception e){ e.printStackTrace(); }
        return false;
    }

    public boolean update(NguoiDiCung n) {
        String sql = "UPDATE NguoiDiCung SET CCCD=?,HoTen=?,GioiTinh=?,NamSinh=?,MoiQuanHe=?,MaDP=? WHERE MaNDD=?";
        try (Connection c=DatabaseConnection.getConnection();
             PreparedStatement ps=c.prepareStatement(sql)) {
            ps.setString(1,n.getCCCD());
            ps.setString(2,n.getHoTen());
            ps.setString(3,n.getGioiTinh());
            ps.setInt(4,n.getNamSinh());
            ps.setString(5,n.getMoiQuanHe());
            ps.setString(6,n.getMaDP());
            ps.setString(7,n.getMaNDD());
            return ps.executeUpdate()>0;
        } catch(Exception e){ e.printStackTrace(); }
        return false;
    }

    public boolean delete(String maNDD) {
        String sql = "DELETE FROM NguoiDiCung WHERE MaNDD=?";
        try (Connection c=DatabaseConnection.getConnection();
             PreparedStatement ps=c.prepareStatement(sql)) {
            ps.setString(1,maNDD);
            return ps.executeUpdate()>0;
        } catch(Exception e){ e.printStackTrace(); }
        return false;
    }

    private NguoiDiCung mapRow(ResultSet rs) throws SQLException {
        return new NguoiDiCung(
                rs.getString("MaNDD"),
                rs.getString("CCCD"),
                rs.getString("HoTen"),
                rs.getString("GioiTinh"),
                rs.getInt("NamSinh"),
                rs.getString("MoiQuanHe"),
                rs.getString("MaDP")
        );
    }
}
