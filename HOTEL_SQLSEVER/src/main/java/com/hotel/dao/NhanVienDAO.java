package com.hotel.dao;

import com.hotel.database.DatabaseConnection;
import com.hotel.model.NhanVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {

    public List<NhanVien> getAll() {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien ORDER BY MaNV";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public NhanVien findById(String id) {
        String sql = "SELECT * FROM NhanVien WHERE MaNV = ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) { if (rs.next()) return mapRow(rs); }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public boolean insert(NhanVien nv) {
        String sql = "INSERT INTO NhanVien(MaNV,Ho,TenLot,Ten,ChucVu,SDT) VALUES(?,?,?,?,?,?)";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, nv.getMaNV());
            ps.setString(2, nv.getHo());
            ps.setString(3, nv.getTenLot());
            ps.setString(4, nv.getTen());
            ps.setString(5, nv.getChucVu());
            ps.setString(6, nv.getSdt());
            return ps.executeUpdate() > 0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }

    public boolean update(NhanVien nv) {
        String sql = "UPDATE NhanVien SET Ho=?,TenLot=?,Ten=?,ChucVu=?,SDT=? WHERE MaNV=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, nv.getHo());
            ps.setString(2, nv.getTenLot());
            ps.setString(3, nv.getTen());
            ps.setString(4, nv.getChucVu());
            ps.setString(5, nv.getSdt());
            ps.setString(6, nv.getMaNV());
            return ps.executeUpdate() > 0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }

    public boolean delete(String id) {
        String sql = "DELETE FROM NhanVien WHERE MaNV=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }

    public List<NhanVien> search(String kw) {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien WHERE MaNV LIKE ? OR Ho LIKE ? OR Ten LIKE ? OR ChucVu LIKE ? OR SDT LIKE ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            String l = "%" + kw + "%";
            for (int i=1;i<=5;i++) ps.setString(i, l);
            try (ResultSet rs = ps.executeQuery()) { while (rs.next()) list.add(mapRow(rs)); }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public List<NhanVien> getByField(String field, String value) {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien WHERE " + field + " = ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,value);
            try (ResultSet rs=ps.executeQuery()){ while(rs.next()) list.add(mapRow(rs)); }
        } catch(Exception e){ e.printStackTrace();}
        return list;
    }

    private NhanVien mapRow(ResultSet rs) throws SQLException {
        return new NhanVien(rs.getString("MaNV"), rs.getString("Ho"), rs.getString("TenLot"),
                rs.getString("Ten"), rs.getString("ChucVu"), rs.getString("SDT"));
    }
}
