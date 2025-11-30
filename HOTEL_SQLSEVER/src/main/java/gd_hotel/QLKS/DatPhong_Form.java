/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gd_hotel.QLKS;

import com.hotel.dao.ChiTietDatPhongDAO;
import com.hotel.dao.DatPhongDAO;
import com.hotel.dao.LoaiPhongDAO;
import com.hotel.dao.PhongDAO;
import com.hotel.database.DatabaseConnection;
import com.hotel.model.ChiTietDatPhong;
import com.hotel.model.DatPhong;
import com.hotel.model.LoaiPhong;
import com.hotel.model.Phong;
import gd_hotel.MANAGER_PAGE;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HOANG PHI
 */
public class DatPhong_Form extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DatPhong_Form.class.getName());

    /**
     * Creates new form DatPhong_Form
     */
    public DatPhong_Form() {
        initComponents();
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            DefaultTableModel model = (DefaultTableModel) tbShowDP.getModel();
            DatPhongDAO DPD = new DatPhongDAO();
            List <DatPhong> LDP = DPD.getAll();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            for(DatPhong dp : LDP){
                model.addRow(new Object[] {
                    dp.getMaDP(),
                    dp.getMaKH(),
                    dp.getMaNV(),
                    sdf.format(dp.getNgayDat()),
                    sdf.format(dp.getNgayNhan()),
                    sdf.format(dp.getNgayTra()),
                    dp.getTrangThai()
                });
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + ex.getMessage());
        }
        
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            DefaultTableModel model = (DefaultTableModel) tbShowCTDP.getModel();
            ChiTietDatPhongDAO CTDPD = new ChiTietDatPhongDAO();
            List <ChiTietDatPhong> LCTDP = CTDPD.getAll();
            
            for(ChiTietDatPhong ctdp : LCTDP){
                model.addRow(new Object[] {
                    ctdp.getMaDP(),
                    ctdp.getMaPhong(),
                    ctdp.getGiaDP()
                });
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + ex.getMessage());
        }
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            DefaultTableModel model = (DefaultTableModel) tbShowPhong.getModel();
            PhongDAO PD = new PhongDAO();
            List <Phong> LP = PD.getAll();
            
            for(Phong p : LP){
                model.addRow(new Object[] {     
                    p.getMaPhong(),
                    p.getSoPhong(),
                    p.getTang(),
                    p.getTrangThai(),
                    p.getMaLP(),
                    p.getMaNVQL()
                });
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + ex.getMessage());
        }
                
        try (Connection conn = DatabaseConnection.getConnection()) {
            DefaultTableModel model = (DefaultTableModel) tbShowLP.getModel();
            LoaiPhongDAO LPD = new LoaiPhongDAO();
            List <LoaiPhong> LLP = LPD.getAll();
            
            for(LoaiPhong lp : LLP){
                model.addRow(new Object[] {
                    lp.getMaLP(),
                    lp.getTenLP(),
                    lp.getGiaLP(),
                    lp.getSucChua(),
                    lp.getMoTa()
                });
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + ex.getMessage());
        }
        
        
        
//        setTitle("Auto Resizable Layout");
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        JPanel center = new JPanel(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//
//        gbc.fill = GridBagConstraints.BOTH;
//        gbc.weightx = 1;
//        gbc.weighty = 1;
//
//
//        setExtendedState(MAXIMIZED_BOTH); // tự full và chỉnh theo màn hình
        

        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                DatPhong_Form.this.setVisible(false);

                java.awt.EventQueue.invokeLater(() -> new MANAGER_PAGE().setVisible(true));
            }
        });
        
    }
    
    int check = 0;
    
    private void loadEmptyTable(int selected) {
        DefaultTableModel model = (DefaultTableModel) tbAdd.getModel();
        model.setRowCount(0); // Xóa bảng cũ

        switch (selected) {
            case 1 -> {
                model.setColumnIdentifiers(new String[]{"Mã DP", "Mã KH", "Mã NV", "Ngày Đặt", "Ngày Nhận", "Ngày Trả", "Trạng Thái"});
                check = 1;
            }
            case 2 -> {
                model.setColumnIdentifiers(new String[]{"Mã DP", "Mã Phòng", "Giá DP"});
                check = 2;
            }
            case 3 -> {
                model.setColumnIdentifiers(new String[]{"Mã Phòng", "Số Phòng", "Tầng", "Trạng Thái", "Mã LP", "Mã NVQL"});
                check = 3;
            }
            case 4 -> {
                model.setColumnIdentifiers(new String[]{"Mã LP", "Tên LP", "Giá LP", "Sức Chứa", "Mô Tả"});
                check = 4;
            }
        }

        // Thêm 1 dòng trống để nhập dữ liệu
        model.addRow(new Object[model.getColumnCount()]);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbShowDP = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbShowCTDP = new javax.swing.JTable();
        btnAdd1 = new javax.swing.JButton();
        btnUpdate1 = new javax.swing.JButton();
        btnDelete1 = new javax.swing.JButton();
        btnFind1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbShowPhong = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbShowLP = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbAdd = new javax.swing.JTable();
        btnChon = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbShowDP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã DP", "Mã KH", "Mã NV", "Ngày Đặt", "Ngày Nhận", "Ngày Trả", "Trạng Thái"
            }
        ));
        tbShowDP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbShowDPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbShowDP);

        tbShowCTDP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã DP", "Mã Phòng", "Giá DP"
            }
        ));
        tbShowCTDP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbShowCTDPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbShowCTDP);

        btnAdd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48-free-object-icons/png/48x48/Add.png"))); // NOI18N
        btnAdd1.setText("Thêm");
        btnAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd1ActionPerformed(evt);
            }
        });

        btnUpdate1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48-free-object-icons/png/48x48/edit-icon.png"))); // NOI18N
        btnUpdate1.setText("Sửa");
        btnUpdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate1ActionPerformed(evt);
            }
        });

        btnDelete1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48-free-object-icons/png/48x48/Minus.png"))); // NOI18N
        btnDelete1.setText("Xóa");
        btnDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete1ActionPerformed(evt);
            }
        });

        btnFind1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48-free-object-icons/png/48x48/Search.png"))); // NOI18N
        btnFind1.setText("TÌm");
        btnFind1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFind1ActionPerformed(evt);
            }
        });

        tbShowPhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Phòng", "Số Phòng", "Tầng", "Trạng Thái", "Mã LP", "Mã NVQL"
            }
        ));
        tbShowPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbShowPhongMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbShowPhong);

        tbShowLP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã LP", "Tên LP", "Giá LP", "Sức Chứa", "Mô Tả"
            }
        ));
        tbShowLP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbShowLPMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbShowLP);

        tbAdd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(tbAdd);

        btnChon.setText("Chọn");
        btnChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnChon)
                        .addGap(0, 42, Short.MAX_VALUE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1077, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAdd1)
                .addGap(67, 67, 67)
                .addComponent(btnUpdate1)
                .addGap(67, 67, 67)
                .addComponent(btnDelete1)
                .addGap(67, 67, 67)
                .addComponent(btnFind1)
                .addGap(272, 272, 272))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(btnChon)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd1)
                    .addComponent(btnUpdate1)
                    .addComponent(btnDelete1)
                    .addComponent(btnFind1))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String getValue(DefaultTableModel model, int row, int col) {
        Object val = model.getValueAt(row, col);
        if (val == null) return "";
        return val.toString().trim();
    }
    
    private void btnAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd1ActionPerformed

        DatPhongDAO DPD = new DatPhongDAO();
        ChiTietDatPhongDAO CTDPD = new ChiTietDatPhongDAO();
        PhongDAO PD = new PhongDAO();
        LoaiPhongDAO LPD = new LoaiPhongDAO();

        DefaultTableModel modelAdd = (DefaultTableModel) tbAdd.getModel();
        DefaultTableModel modelShowDP = (DefaultTableModel) tbShowDP.getModel();
        DefaultTableModel modelShowCTDP = (DefaultTableModel) tbShowCTDP.getModel();
        DefaultTableModel modelShowPhong = (DefaultTableModel) tbShowPhong.getModel();
        DefaultTableModel modelShowLP = (DefaultTableModel) tbShowLP.getModel();

        switch (check) {

            case 1 -> {

                String maDP = getValue(modelAdd, 0, 0);
                String maKH = getValue(modelAdd, 0, 1);
                String maNV = getValue(modelAdd, 0, 2);

                String ngayDatStr = getValue(modelAdd, 0, 3);
                String ngayNhanStr = getValue(modelAdd, 0, 4);
                String ngayTraStr = getValue(modelAdd, 0, 5);

                Date ngayDat = Date.valueOf(ngayDatStr);
                Date ngayNhan = Date.valueOf(ngayNhanStr);
                Date ngayTra = Date.valueOf(ngayTraStr);

                String trangThai = getValue(modelAdd, 0, 6);

                modelShowDP.addRow(new Object[]{
                    maDP, maKH, maNV, ngayDatStr, ngayNhanStr, ngayTraStr, trangThai
                });

                DatPhong dp = new DatPhong();
                dp.setMaDP(maDP);
                dp.setMaKH(maKH);
                dp.setMaNV(maNV);
                dp.setNgayDat(ngayDat);
                dp.setNgayNhan(ngayNhan);
                dp.setNgayTra(ngayTra);
                dp.setTrangThai(trangThai);

                if (DPD.insert(dp))
                    JOptionPane.showMessageDialog(this, "Thêm thành công!");
                else{
                    JOptionPane.showMessageDialog(this, "Lỗi khi thêm!");
                }

            }

            case 2 -> {

                String maDP = getValue(modelAdd, 0, 0);
                String maPhong = getValue(modelAdd, 0, 1);
                String giaDPstr = getValue(modelAdd, 0, 2);
                double giaDP = Double.parseDouble(getValue(modelAdd, 0, 2));

                modelShowCTDP.addRow(new Object[]{
                    maDP, maPhong, giaDPstr
                });

                ChiTietDatPhong ctdp = new ChiTietDatPhong();
                ctdp.setMaDP(maDP);
                ctdp.setMaPhong(maPhong);
                ctdp.setGiaDP(giaDP);

                if (CTDPD.insert(ctdp))
                    JOptionPane.showMessageDialog(this, "Thêm thành công!");
                else
                    JOptionPane.showMessageDialog(this, "Lỗi khi thêm!");

            }

            case 3 -> {

                String maPhong = getValue(modelAdd, 0, 0);
                String soPhong = getValue(modelAdd, 0, 1);
                int tang = Integer.parseInt(getValue(modelAdd, 0, 2));
                String trangThai = getValue(modelAdd, 0, 3);
                String maLP = getValue(modelAdd, 0, 4);
                String maNVQL = getValue(modelAdd, 0, 5);

                modelShowPhong.addRow(new Object[]{
                    maPhong, soPhong, tang, trangThai, maLP, maNVQL
                });

                Phong p = new Phong(maPhong, soPhong, tang, trangThai, maLP, maNVQL);

                if (PD.insert(p))
                    JOptionPane.showMessageDialog(this, "Thêm thành công!");
                else
                    JOptionPane.showMessageDialog(this, "Lỗi khi thêm!");
            }

            case 4 -> {

                String maLP = getValue(modelAdd, 0, 0);
                String tenLP = getValue(modelAdd, 0, 1);
                String giaLPstr = getValue(modelAdd, 0, 2);
                double giaLP = Double.parseDouble(giaLPstr);
                int sucChua = Integer.parseInt(getValue(modelAdd, 0, 3));
                String moTa = getValue(modelAdd, 0, 4);

                modelShowLP.addRow(new Object[]{
                    maLP, tenLP, giaLPstr, sucChua, moTa
                });

                LoaiPhong lp = new LoaiPhong(maLP, tenLP, giaLP, sucChua, moTa);

                if (LPD.insert(lp))
                    JOptionPane.showMessageDialog(this, "Thêm thành công!");
                else
                    JOptionPane.showMessageDialog(this, "Lỗi khi thêm!");
            }
        }
        
        
    }//GEN-LAST:event_btnAdd1ActionPerformed

    private void btnUpdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate1ActionPerformed
        
        
        DatPhongDAO DPD = new DatPhongDAO();
        ChiTietDatPhongDAO CTDPD = new ChiTietDatPhongDAO();
        PhongDAO PD = new PhongDAO();
        LoaiPhongDAO LPD = new LoaiPhongDAO();

        DefaultTableModel modelAdd = (DefaultTableModel) tbAdd.getModel();
        
        DefaultTableModel modelShowDP = (DefaultTableModel) tbShowDP.getModel();
        DefaultTableModel modelShowCTDP = (DefaultTableModel) tbShowCTDP.getModel();
        DefaultTableModel modelShowPhong = (DefaultTableModel) tbShowPhong.getModel();
        DefaultTableModel modelShowLP = (DefaultTableModel) tbShowLP.getModel();

        switch(check){
            case 1 -> {
                
                int row = tbShowDP.getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 hàng để sửa.");
                    return;
                }

                if (modelAdd.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "Chưa có dữ liệu để cập nhật.");
                    return;
                }

                String maDP = getValue(modelAdd, 0, 0);
                String maKH = getValue(modelAdd, 0, 1);
                String maNV = getValue(modelAdd, 0, 2);
                String ngayDatStr = getValue(modelAdd, 0, 3);
                String ngayNhanStr = getValue(modelAdd, 0, 4);
                String ngayTraStr = getValue(modelAdd, 0, 5);
                Date ngayDat = Date.valueOf(ngayDatStr);
                Date ngayNhan = Date.valueOf(ngayNhanStr);
                Date ngayTra = Date.valueOf(ngayTraStr);
                String trangThai = getValue(modelAdd, 0, 6);

                modelShowDP.setValueAt(maDP, row, 0);
                modelShowDP.setValueAt(maKH, row, 1);
                modelShowDP.setValueAt(maNV, row, 2);
                modelShowDP.setValueAt(ngayDatStr, row, 3);
                modelShowDP.setValueAt(ngayNhanStr, row, 4);
                modelShowDP.setValueAt(ngayTraStr, row, 5);
                modelShowDP.setValueAt(trangThai, row, 6);

                DatPhong dp = new DatPhong();
                dp.setMaDP(maDP);
                dp.setMaKH(maKH);
                dp.setMaNV(maNV);
                dp.setNgayDat(ngayDat);
                dp.setNgayNhan(ngayNhan);
                dp.setNgayTra(ngayTra);
                dp.setTrangThai(trangThai);

                if (DPD.update(dp)) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật!");
                }
            }
            case 2 -> {
                int row = tbShowCTDP.getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 hàng để sửa.");
                    return;
                }

                if (modelAdd.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "Chưa có dữ liệu để cập nhật.");
                    return;
                }

                String maDP = getValue(modelAdd, 0, 0);
                String maPhong = getValue(modelAdd, 0, 1);
                String giaDPstr = getValue(modelAdd, 0, 2);
                double giaDP = Double.parseDouble(giaDPstr);

                modelShowCTDP.setValueAt(maDP, row, 0);
                modelShowCTDP.setValueAt(maPhong, row, 1);
                modelShowCTDP.setValueAt(giaDPstr, row, 2);

                ChiTietDatPhong ctdp = new ChiTietDatPhong(maDP,maPhong,giaDP);

                if (CTDPD.update(ctdp)) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật!");
                }
            }
            case 3 -> {
                int row = tbShowPhong.getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 hàng để sửa.");
                    return;
                }

                if (modelAdd.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "Chưa có dữ liệu để cập nhật.");
                    return;
                }

                String maPhong = getValue(modelAdd, 0, 0);
                String soPhong = getValue(modelAdd, 0, 1);
                int tang = Integer.parseInt(getValue(modelAdd, 0, 2));
                String trangThai = getValue(modelAdd, 0, 3);
                String maLP = getValue(modelAdd, 0, 4);
                String maNVQL = getValue(modelAdd, 0, 5);

                modelShowPhong.setValueAt(maPhong, row, 0);
                modelShowPhong.setValueAt(soPhong, row, 1);
                modelShowPhong.setValueAt(tang, row, 2);
                modelShowPhong.setValueAt(trangThai, row, 3);
                modelShowPhong.setValueAt(maLP, row, 4);
                modelShowPhong.setValueAt(maNVQL, row, 5);

                Phong p = new Phong(maPhong,soPhong,tang,trangThai,maLP,maNVQL);

                if (PD.update(p)) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật!");
                }
            }
            case 4 -> {
                int row = tbShowLP.getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 hàng để sửa.");
                    return;
                }

                if (modelAdd.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "Chưa có dữ liệu để cập nhật.");
                    return;
                }

                String maLP = getValue(modelAdd, 0, 0);
                String tenLP = getValue(modelAdd, 0, 1);
                double giaLP = Double.parseDouble(getValue(modelAdd, 0, 2));
                int sucChua = Integer.parseInt(getValue(modelAdd, 0, 3));
                String moTa = getValue(modelAdd, 0, 4);

                modelShowLP.setValueAt(maLP, row, 0);
                modelShowLP.setValueAt(tenLP, row, 1);
                modelShowLP.setValueAt(giaLP, row, 2);
                modelShowLP.setValueAt(sucChua, row, 3);
                modelShowLP.setValueAt(moTa, row, 4);

                // --- UPDATE DATABASE ---
                LoaiPhong lp = new LoaiPhong(maLP,tenLP,giaLP,sucChua,moTa);

                if (LPD.update(lp)) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật!");
                }
            }
        }
    }//GEN-LAST:event_btnUpdate1ActionPerformed

    private void btnDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete1ActionPerformed
        
        switch(check){
            case 1 -> {
                int row = tbShowDP.getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 hàng để xóa.");
                    return;
                }
                
                DatPhongDAO DPD = new DatPhongDAO();
                String maDP = tbShowDP.getValueAt(row, 0).toString();
                
                if(DPD.delete(maDP)){
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    DefaultTableModel model = (DefaultTableModel) tbShowDP.getModel();
                    model.removeRow(row);
                }else{
                    JOptionPane.showMessageDialog(this, "Lỗi khi xóa!");
                }
                
            }
            case 2 -> {
                int row = tbShowCTDP.getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 hàng để xóa.");
                    return;
                }
                
                ChiTietDatPhongDAO CTPDD = new ChiTietDatPhongDAO();
                String maDP = tbShowCTDP.getValueAt(row, 0).toString();
                String maP = tbShowCTDP.getValueAt(row, 1).toString();
                if(CTPDD.delete(maDP, maP)){
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    DefaultTableModel model = (DefaultTableModel) tbShowCTDP.getModel();
                    model.removeRow(row);
                }else{
                    JOptionPane.showMessageDialog(this, "Lỗi khi xóa!");
                }
            }
            case 3 -> {
                int row = tbShowPhong.getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 hàng để xóa.");
                    return;
                }
                
                PhongDAO PD = new PhongDAO();
                String maP = tbShowPhong.getValueAt(row, 0).toString();
                if(PD.delete(maP)){
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    DefaultTableModel model = (DefaultTableModel) tbShowPhong.getModel();
                    model.removeRow(row);
                }else{
                    JOptionPane.showMessageDialog(this, "Lỗi khi xóa!");
                }
            }
            case 4 -> {
                int row = tbShowLP.getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 hàng để xóa.");
                    return;
                }
                
                LoaiPhongDAO LPD = new LoaiPhongDAO();
                String maLP = tbShowLP.getValueAt(row, 0).toString();
                if(LPD.delete(maLP)){
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    DefaultTableModel model = (DefaultTableModel) tbShowLP.getModel();
                    model.removeRow(row);
                }else{
                    JOptionPane.showMessageDialog(this, "Lỗi khi xóa!");
                }
            }
            default -> {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn bảng muốn xóa!");
            }
        }
    }//GEN-LAST:event_btnDelete1ActionPerformed

    private void btnFind1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFind1ActionPerformed
         switch(check){
            case 1 -> {
                String maDP = JOptionPane.showInputDialog("Nhập mã Đặt Phòng:");

                DatPhongDAO dao = new DatPhongDAO();
                DatPhong dp = dao.findById(maDP);

                DefaultTableModel model = (DefaultTableModel) tbAdd.getModel();
                model.setRowCount(0);

                if (dp != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    model.addRow(new Object[]{
                        dp.getMaDP(),
                        dp.getMaKH(),
                        dp.getMaNV(),
                        sdf.format(dp.getNgayDat()),
                        sdf.format(dp.getNgayNhan()),
                        sdf.format(dp.getNgayTra()),
                        dp.getTrangThai()
                    });
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy!");
                }
            }
            case 2 -> {
                String maDP = JOptionPane.showInputDialog("Nhập mã Đặt Phòng:");

                ChiTietDatPhongDAO dao = new ChiTietDatPhongDAO();
                List<ChiTietDatPhong> list = dao.findByMaDP(maDP);

                DefaultTableModel model = (DefaultTableModel) tbAdd.getModel();
                model.setRowCount(0);

                if (list != null && !list.isEmpty()) {
                    for (ChiTietDatPhong ctdp : list) {
                        model.addRow(new Object[]{
                            ctdp.getMaDP(),
                            ctdp.getMaPhong(),
                            ctdp.getGiaDP()
                        });
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy!");
                }
            }
            case 3 -> {
                String maP = JOptionPane.showInputDialog("Nhập mã Phòng:");

                PhongDAO dao = new PhongDAO();
                Phong p = dao.findById(maP);

                DefaultTableModel model = (DefaultTableModel) tbAdd.getModel();
                model.setRowCount(0);

                if (p != null) {
                    model.addRow(new Object[]{
                        p.getMaPhong(),
                        p.getSoPhong(),
                        p.getTang(),
                        p.getTrangThai(),
                        p.getMaLP(),
                        p.getMaNVQL()
                    });
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy!");
                }
            }
            case 4 -> {
                String maLP = JOptionPane.showInputDialog("Nhập mã Dat Phong:");

                LoaiPhongDAO dao = new LoaiPhongDAO();
                LoaiPhong lp = dao.findById(maLP);

                DefaultTableModel model = (DefaultTableModel) tbAdd.getModel();
                model.setRowCount(0);

                if (lp != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    model.addRow(new Object[]{
                        lp.getMaLP(),
                        lp.getTenLP(),
                        lp.getGiaLP(),
                        lp.getSucChua(),
                        lp.getMoTa()
                    });
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy!");
                }
            }
            default -> {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn bảng muốn tìm!");
            }
        }
    }//GEN-LAST:event_btnFind1ActionPerformed

    private void btnChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonActionPerformed
        JPopupMenu pop = new JPopupMenu();

        JMenuItem m1 = new JMenuItem("Bảng 1: Đặt Phòng");
        JMenuItem m2 = new JMenuItem("Bảng 2: Chi Tiết Đặt Phòng");
        JMenuItem m3 = new JMenuItem("Bảng 3: Phòng");
        JMenuItem m4 = new JMenuItem("Bảng 4: Loại Phòng");

        // Gắn các sự kiện chọn bảng
        m1.addActionListener(e -> loadEmptyTable(1));
        m2.addActionListener(e -> loadEmptyTable(2));
        m3.addActionListener(e -> loadEmptyTable(3));
        m4.addActionListener(e -> loadEmptyTable(4));

        pop.add(m1);
        pop.add(m2);
        pop.add(m3);
        pop.add(m4);

        // Hiện popup ngay dưới nút bấm
        pop.show(btnChon, 0, btnChon.getHeight());
    }//GEN-LAST:event_btnChonActionPerformed

    private void tbShowDPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbShowDPMouseClicked
        int row = tbShowDP.getSelectedRow();
        if (row >= 0) {

            String maDP = tbShowDP.getValueAt(row, 0).toString();

            DatPhongDAO dao = new DatPhongDAO();
            List<DatPhong> list = new ArrayList<>();
            list.add(dao.findById(maDP));

            DefaultTableModel modelUpdate = (DefaultTableModel) tbAdd.getModel();
            modelUpdate.setRowCount(0);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (DatPhong dp : list) {
                modelUpdate.addRow(new Object[]{
                    dp.getMaDP(),
                    dp.getMaKH(),
                    dp.getMaNV(),
                    sdf.format(dp.getNgayDat()),
                    sdf.format(dp.getNgayNhan()),
                    sdf.format(dp.getNgayTra()),
                    dp.getTrangThai()
                });
            }
        }   
    }//GEN-LAST:event_tbShowDPMouseClicked

    private void tbShowCTDPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbShowCTDPMouseClicked
        int row = tbShowCTDP.getSelectedRow();
        if (row >= 0) {

        String maDP = tbShowCTDP.getValueAt(row, 0).toString();

        ChiTietDatPhongDAO dao = new ChiTietDatPhongDAO();
        List<ChiTietDatPhong> list = dao.findByMaDP(maDP);  

        DefaultTableModel modelUpdate = (DefaultTableModel) tbAdd.getModel();
        modelUpdate.setRowCount(0);

        for (ChiTietDatPhong dp : list) {
            modelUpdate.addRow(new Object[]{
                dp.getMaDP(),
                dp.getMaPhong(),
                dp.getGiaDP()
            });
        }
    }
    
    }//GEN-LAST:event_tbShowCTDPMouseClicked

    private void tbShowPhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbShowPhongMouseClicked
        int row = tbShowPhong.getSelectedRow();
        if (row >= 0) {

            String maPhong = tbShowPhong.getValueAt(row, 0).toString();

            PhongDAO dao = new PhongDAO();
            List<Phong> list = new ArrayList<>();
            list.add(dao.findById(maPhong));

            DefaultTableModel modelUpdate = (DefaultTableModel) tbAdd.getModel();
            modelUpdate.setRowCount(0);

            for (Phong p : list) {
                modelUpdate.addRow(new Object[]{
                    p.getMaPhong(),
                    p.getSoPhong(),
                    p.getTang(),
                    p.getTrangThai(),
                    p.getMaLP(),
                    p.getMaNVQL()
                });
            }
        } 
    }//GEN-LAST:event_tbShowPhongMouseClicked

    private void tbShowLPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbShowLPMouseClicked
        int row = tbShowLP.getSelectedRow();
        if (row >= 0) {

            String maLP = tbShowLP.getValueAt(row, 0).toString();

            LoaiPhongDAO dao = new LoaiPhongDAO();
            List<LoaiPhong> list = new ArrayList<>();
            list.add(dao.findById(maLP));

            DefaultTableModel modelUpdate = (DefaultTableModel) tbAdd.getModel();
            modelUpdate.setRowCount(0);

            for (LoaiPhong lp : list) {
                modelUpdate.addRow(new Object[]{
                    lp.getMaLP(),
                    lp.getTenLP(),
                    lp.getGiaLP(),
                    lp.getSucChua(),
                    lp.getMoTa()
                });
            }
        }  
    }//GEN-LAST:event_tbShowLPMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new DatPhong_Form().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd1;
    private javax.swing.JButton btnChon;
    private javax.swing.JButton btnDelete1;
    private javax.swing.JButton btnFind1;
    private javax.swing.JButton btnUpdate1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable tbAdd;
    private javax.swing.JTable tbShowCTDP;
    private javax.swing.JTable tbShowDP;
    private javax.swing.JTable tbShowLP;
    private javax.swing.JTable tbShowPhong;
    // End of variables declaration//GEN-END:variables
}
