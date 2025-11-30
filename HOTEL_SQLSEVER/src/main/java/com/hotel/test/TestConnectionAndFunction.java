package com.hotel.test;

import com.hotel.database.DatabaseConnection;
import com.hotel.dao.*;
import com.hotel.model.*;

import java.sql.Connection;
import java.util.List;

public class TestConnectionAndFunction {
    public static void main(String[] args) {

        // ======= TEST KẾT NỐI =======
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("Kết nối CSDL OK");
        } catch (Exception e) {
            System.out.println("Lỗi kết nối: " + e.getMessage());
            return;
        }

        System.out.println("===== TEST DAO =====");

        PhongDAO pDAO = new PhongDAO();
        List<Phong> list = pDAO.getAll();

        if (list.isEmpty()) {
            System.out.println("Không có phòng nào!");
        } else {
            for (Phong p : list) {
                System.out.println(p.getMaPhong() + " - " + p.getSoPhong() + " - " + p.getTrangThai());
            }
        }

        System.out.println("===== TEST PHÒNG TRỐNG =====");
        for (Phong p : pDAO.getPhongTheoTrangThai("Trống")) {
            System.out.println(p.getMaPhong() + " - " + p.getSoPhong());
        }

        System.out.println("===== TEST DỊCH VỤ =====");
        DichVuDAO dvDAO = new DichVuDAO();
        for (DichVu dv : dvDAO.getAll()) {
            System.out.println(dv.getMaDV() + " - " + dv.getTenDV());
        }
    }
}
