package com.hotel.dao;

import com.hotel.database.DatabaseConnection;
import com.hotel.model.DatPhong;
import com.hotel.model.ChiTietDatPhong;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatPhongDAO {

    public List<DatPhong> getAll() {
        List<DatPhong> list = new ArrayList<>();
        String sql = "SELECT * FROM DatPhong ORDER BY NgayDat DESC";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                DatPhong d = mapRow(rs);
                list.add(d);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public DatPhong findById(String maDP) {
        String sql = "SELECT * FROM DatPhong WHERE MaDP=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,maDP);
            try (ResultSet rs = ps.executeQuery()){ if(rs.next()) return mapRow(rs); }
        } catch (Exception e){ e.printStackTrace(); }
        return null;
    }

    public boolean insert(DatPhong d) {
        String sql = "INSERT INTO DatPhong(MaDP,MaKH,MaNV,NgayDat,NgayNhan,NgayTra,TrangThai) VALUES(?,?,?,?,?,?,?)";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,d.getMaDP());
            ps.setString(2,d.getMaKH());
            ps.setString(3,d.getMaNV());
            ps.setTimestamp(4, new Timestamp(d.getNgayDat().getTime()));
            ps.setTimestamp(5, new Timestamp(d.getNgayNhan().getTime()));
            ps.setTimestamp(6, new Timestamp(d.getNgayTra().getTime()));
            ps.setString(7, d.getTrangThai());
            return ps.executeUpdate()>0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }

    public boolean update(DatPhong d) {
        String sql = "UPDATE DatPhong SET MaKH=?,MaNV=?,NgayDat=?,NgayNhan=?,NgayTra=?,TrangThai=? WHERE MaDP=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,d.getMaKH());
            ps.setString(2,d.getMaNV());
            ps.setTimestamp(3,new Timestamp(d.getNgayDat().getTime()));
            ps.setTimestamp(4,new Timestamp(d.getNgayNhan().getTime()));
            ps.setTimestamp(5,new Timestamp(d.getNgayTra().getTime()));
            ps.setString(6,d.getTrangThai());
            ps.setString(7,d.getMaDP());
            return ps.executeUpdate()>0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }

    public boolean delete(String maDP) {
        String sql = "DELETE FROM DatPhong WHERE MaDP=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,maDP);
            return ps.executeUpdate()>0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }

    public List<DatPhong> search(String kw) {
        List<DatPhong> list = new ArrayList<>();
        String sql = "SELECT * FROM DatPhong WHERE MaDP LIKE ? OR MaKH LIKE ? OR MaNV LIKE ? OR TrangThai LIKE ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            String l="%"+kw+"%";
            ps.setString(1,l); ps.setString(2,l); ps.setString(3,l); ps.setString(4,l);
            try (ResultSet rs=ps.executeQuery()){ while(rs.next()) list.add(mapRow(rs)); }
        } catch (Exception e){ e.printStackTrace(); }
        return list;
    }

    public List<DatPhong> getByField(String field, String value) {
        List<DatPhong> list = new ArrayList<>();
        String sql = "SELECT * FROM DatPhong WHERE " + field + " = ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,value);
            try (ResultSet rs=ps.executeQuery()){ while(rs.next()) list.add(mapRow(rs)); }
        } catch (Exception e){ e.printStackTrace(); }
        return list;
    }

    // === Business ===
    // 1. Đặt phòng: insert DatPhong + insert ChiTietDatPhong entries (caller phải manage transaction)
    public boolean datPhong(DatPhong d, List<ChiTietDatPhong> chiTiets) throws Exception {
        String sqlDP = "INSERT INTO DatPhong(MaDP,MaKH,MaNV,NgayDat,NgayNhan,NgayTra,TrangThai) VALUES(?,?,?,?,?,?,?)";
        String sqlCT = "INSERT INTO ChiTietDatPhong(MaDP,MaP,GiaDP) VALUES(?,?,?)";

        Connection c = null;
        try {
            c = DatabaseConnection.getConnection();
            c.setAutoCommit(false);

            try (PreparedStatement ps = c.prepareStatement(sqlDP)) {
                ps.setString(1,d.getMaDP());
                ps.setString(2,d.getMaKH());
                ps.setString(3,d.getMaNV());
                ps.setTimestamp(4,new Timestamp(d.getNgayDat().getTime()));
                ps.setTimestamp(5,new Timestamp(d.getNgayNhan().getTime()));
                ps.setTimestamp(6,new Timestamp(d.getNgayTra().getTime()));
                ps.setString(7,d.getTrangThai());
                ps.executeUpdate();
            }

            try (PreparedStatement ps2 = c.prepareStatement(sqlCT)) {
                for (ChiTietDatPhong ct : chiTiets) {
                    ps2.setString(1, ct.getMaDP());
                    ps2.setString(2, ct.getMaPhong());
                    ps2.setDouble(3, ct.getGiaDP());
                    ps2.addBatch();
                }
                ps2.executeBatch();
            }

            c.commit();
            return true;
        } catch (Exception e) {
            if (c != null) try { c.rollback(); } catch (Exception ex) { ex.printStackTrace(); }
            throw e;
        } finally {
            if (c != null) try { c.setAutoCommit(true); c.close(); } catch (Exception ex) { ex.printStackTrace(); }
        }
    }

    // 2. Check-in: set DatPhong.TrangThai = 'Đang ở' và Phong.TrangThai = 'Đang ở'
    public boolean checkIn(String maDP, String maPhong) {
        String sql1 = "UPDATE DatPhong SET TrangThai='Đang ở' WHERE MaDP=?";
        String sql2 = "UPDATE Phong SET TrangThai='Đang ở' WHERE MaPhong=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps1 = c.prepareStatement(sql1);
             PreparedStatement ps2 = c.prepareStatement(sql2)) {
            c.setAutoCommit(false);
            ps1.setString(1, maDP); ps1.executeUpdate();
            ps2.setString(1, maPhong); ps2.executeUpdate();
            c.commit();
            return true;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    // 3. Check-out: set DatPhong.TrangThai='Đã trả phòng' and Phong.TrangThai='Trống'
    public boolean checkOut(String maDP, String maPhong) {
        String sql1 = "UPDATE DatPhong SET TrangThai='Đã trả phòng' WHERE MaDP=?";
        String sql2 = "UPDATE Phong SET TrangThai='Trống' WHERE MaPhong=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps1 = c.prepareStatement(sql1);
             PreparedStatement ps2 = c.prepareStatement(sql2)) {
            c.setAutoCommit(false);
            ps1.setString(1, maDP); ps1.executeUpdate();
            ps2.setString(1, maPhong); ps2.executeUpdate();
            c.commit();
            return true;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public List<DatPhong> getDatPhongTheoTrangThai(String trangThai) {
        List<DatPhong> list = new ArrayList<>();
        String sql = "SELECT * FROM DatPhong WHERE TrangThai = ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,trangThai);
            try (ResultSet rs=ps.executeQuery()){ while(rs.next()) list.add(mapRow(rs)); }
        } catch (Exception e){ e.printStackTrace(); }
        return list;
    }

    private DatPhong mapRow(ResultSet rs) throws SQLException {
        DatPhong d = new DatPhong();
        d.setMaDP(rs.getString("MaDP"));
        d.setMaKH(rs.getString("MaKH"));
        d.setMaNV(rs.getString("MaNV"));
        d.setNgayDat(new Date(rs.getTimestamp("NgayDat").getTime()));
        d.setNgayNhan(new Date(rs.getTimestamp("NgayNhan").getTime()));
        d.setNgayTra(new Date(rs.getTimestamp("NgayTra").getTime()));
        d.setTrangThai(rs.getString("TrangThai"));
        return d;
    }
}
