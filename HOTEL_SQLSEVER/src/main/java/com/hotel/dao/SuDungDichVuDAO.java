package com.hotel.dao;

import com.hotel.database.DatabaseConnection;
import com.hotel.model.SuDungDichVu;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SuDungDichVuDAO {

    public List<SuDungDichVu> getAll() {
        List<SuDungDichVu> list = new ArrayList<>();
        String sql = "SELECT * FROM SD_DichVu";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (Exception e){ e.printStackTrace(); }
        return list;
    }

    public List<SuDungDichVu> findByMaDP(String maDP) {
        List<SuDungDichVu> list = new ArrayList<>();
        String sql = "SELECT * FROM SD_DichVu WHERE MaDP=?";
        try (Connection c=DatabaseConnection.getConnection();
             PreparedStatement ps=c.prepareStatement(sql)) {
            ps.setString(1,maDP);
            try (ResultSet rs=ps.executeQuery()){ while(rs.next()) list.add(mapRow(rs)); }
        } catch (Exception e){ e.printStackTrace(); }
        return list;
    }

    public boolean insert(SuDungDichVu s) {
        String sql = "INSERT INTO SD_DichVu(MaDP,MaDV,NgaySD,SoLuong) VALUES(?,?,?,?)";
        try (Connection c=DatabaseConnection.getConnection();
             PreparedStatement ps=c.prepareStatement(sql)) {
            ps.setString(1,s.getMaDP());
            ps.setString(2,s.getMaDV());
            ps.setTimestamp(3,new Timestamp(s.getNgaySD().getTime()));
            ps.setInt(4,s.getSoLuong());
            return ps.executeUpdate()>0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }

    public boolean update(SuDungDichVu s) {
        String sql = "UPDATE SD_DichVu SET SoLuong=? WHERE MaDP=? AND MaDV=? AND NgaySD=?";
        try (Connection c=DatabaseConnection.getConnection();
             PreparedStatement ps=c.prepareStatement(sql)) {
            ps.setInt(1,s.getSoLuong());
            ps.setString(2,s.getMaDP());
            ps.setString(3,s.getMaDV());
            ps.setTimestamp(4,new Timestamp(s.getNgaySD().getTime()));
            return ps.executeUpdate()>0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }

    public boolean delete(String maDP, String maDV, Date ngaySD) {
        String sql = "DELETE FROM SD_DichVu WHERE MaDP=? AND MaDV=? AND NgaySD=?";
        try (Connection c=DatabaseConnection.getConnection();
             PreparedStatement ps=c.prepareStatement(sql)) {
            ps.setString(1,maDP);
            ps.setString(2,maDV);
            ps.setTimestamp(3,new Timestamp(ngaySD.getTime()));
            return ps.executeUpdate()>0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }

    private SuDungDichVu mapRow(ResultSet rs) throws SQLException {
        return new SuDungDichVu(
                rs.getString("MaDP"),
                rs.getString("MaDV"),
                rs.getTimestamp("NgaySD"),
                rs.getInt("SoLuong")
        );
    }
}
