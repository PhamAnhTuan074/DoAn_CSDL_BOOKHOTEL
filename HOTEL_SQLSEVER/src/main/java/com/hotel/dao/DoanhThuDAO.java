/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hotel.dao;

import com.hotel.database.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;

public class DoanhThuDAO {

    public ArrayList<Object[]> getDoanhThuTheoThang() {
        ArrayList<Object[]> list = new ArrayList<>();

        String sql = "SELECT YEAR(hd.NgayLap) AS Nam, "
                   + "MONTH(hd.NgayLap) AS Thang, "
                   + "SUM(ct.SoLuong * ct.DonGia) AS TongDoanhThu "
                   + "FROM HoaDon hd "
                   + "JOIN ChiTietHoaDon ct ON hd.MaHD = ct.MaHD "
                   + "GROUP BY YEAR(hd.NgayLap), MONTH(hd.NgayLap) "
                   + "ORDER BY Nam, Thang";
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Object[]{
                    rs.getInt("Nam"),
                    rs.getInt("Thang"),
                    rs.getDouble("TongDoanhThu")
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
    
    public ArrayList<Object[]> getDoanhThuTheoNam() {
        ArrayList<Object[]> list = new ArrayList<>();

        String sql = 
            "SELECT YEAR(hd.NgayLap) AS Nam, " +
            "       SUM(ct.SoLuong * ct.DonGia) AS TongDoanhThu " +
            "FROM HoaDon hd " +
            "JOIN ChiTietHoaDon ct ON hd.MaHD = ct.MaHD " +
            "GROUP BY YEAR(hd.NgayLap) " +
            "ORDER BY Nam";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Object[] {
                    rs.getInt("Nam"),
                    rs.getDouble("TongDoanhThu")
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
}
