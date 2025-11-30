/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hotel.dao;

import com.hotel.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author HOANG PHI
 */
public class PhongTrongDAO {
    
    public ArrayList<Object[]> getPhongTrong(Date ngayNhan, Date ngayTra) {
        ArrayList<Object[]> list = new ArrayList<>();

        String sql =
            "SELECT " +
            "    p.MaPhong, " +
            "    p.SoPhong, " +
            "    p.Tang, " +
            "    p.TrangThai, " +
            "    lp.TenLP AS TenLoaiPhong, " +
            "    lp.GiaLP AS GiaCoBan " +
            "FROM Phong p " +
            "JOIN LoaiPhong lp ON p.MaLP = lp.MaLP " +
            "WHERE p.TrangThai = 'Trống' " +
            "  AND p.MaPhong NOT IN ( " +
            "        SELECT ctdp.MaP " +
            "        FROM ChiTietDatPhong ctdp " +
            "        JOIN DatPhong dp ON ctdp.MaDP = dp.MaDP " +
            "        WHERE dp.TrangThai <> 'Đã hủy' " +
            "          AND ? < dp.NgayTra " +
            "          AND ? > dp.NgayNhan " +
            "  )";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            // set ngày vào dấu ?
            ps.setDate(1, (java.sql.Date) ngayNhan);
            ps.setDate(2, (java.sql.Date) ngayTra);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Object[]{
                    rs.getString("MaPhong"),
                    rs.getInt("SoPhong"),
                    rs.getInt("Tang"),
                    rs.getString("TrangThai"),
                    rs.getString("TenLoaiPhong"),
                    rs.getInt("GiaCoBan")
                });
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public String getMaPhongTrong(String tenLoaiPhong) {
        String sql = """
            SELECT TOP 1 MaPhong
            FROM vw_LichPhong
            WHERE TrangThaiPhong = 'Trống' 
              AND TenLoaiPhong = ?
        """;

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, tenLoaiPhong);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("MaPhong");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
        public String getGiaPhong(String tenLoaiPhong) {
        String sql = """
            SELECT TOP 1 GiaCoBan
            FROM vw_LichPhong
            WHERE TrangThaiPhong = 'Trống' 
              AND TenLoaiPhong = ?
        """;

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, tenLoaiPhong);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("GiaCoBan");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

   
}
