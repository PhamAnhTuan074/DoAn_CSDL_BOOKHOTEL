/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gd_hotel.QLKS;

import com.hotel.dao.DichVuDAO;
import com.hotel.dao.SuDungDichVuDAO;
import com.hotel.database.DatabaseConnection;
import com.hotel.model.DichVu;
import com.hotel.model.SuDungDichVu;
import gd_hotel.MANAGER_PAGE;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HOANG PHI
 */
public class DichVu_Form extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DichVu_Form.class.getName());

    public DichVu_Form() {
        initComponents();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            DefaultTableModel model = (DefaultTableModel) tbShowSDDV.getModel();
            SuDungDichVuDAO hdd = new SuDungDichVuDAO();
            List <SuDungDichVu> HD = hdd.getAll();
            for(SuDungDichVu hd : HD){
                model.addRow(new Object[] {
                    hd.getMaDP(),
                    hd.getMaDV(),
                    sdf.format(hd.getNgaySD()),
                    hd.getSoLuong()
                });
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + ex.getMessage());
        }
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            DefaultTableModel model = (DefaultTableModel) tbShowDV.getModel();
            DichVuDAO hdd = new DichVuDAO();
            List <DichVu> HD = hdd.getAll();
            for(DichVu hd : HD){
                model.addRow(new Object[] {
                    hd.getMaDV(),
                    hd.getTenDV(),
                    hd.getGiaDV(),
                    hd.getMoTa()
                });
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + ex.getMessage());
        }
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                DichVu_Form.this.setVisible(false);

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
                model.setColumnIdentifiers(new String[]{"Mã DP", "Mã DV", "Ngày SD", "Số Lượng"});
                check = 1;
            }
            case 2 -> {
                model.setColumnIdentifiers(new String[]{"Mã DV", "Tên DV", "Giá DV", "Mô Tả"});
                check = 2;
            }
        }

        // Thêm 1 dòng trống để nhập dữ liệu
        model.addRow(new Object[model.getColumnCount()]);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbShowSDDV = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbShowDV = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbAdd = new javax.swing.JTable();
        btnChon = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnFind = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbShowSDDV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã DP", "Mã DV", "Ngày SD", "Số Lượng"
            }
        ));
        tbShowSDDV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbShowSDDVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbShowSDDV);

        tbShowDV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã DV", "Tên DV", "Giá DV", "Mô Tả"
            }
        ));
        tbShowDV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbShowDVMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbShowDV);

        tbAdd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tbAdd);

        btnChon.setText("Chọn");
        btnChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonActionPerformed(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48-free-object-icons/png/48x48/Add.png"))); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48-free-object-icons/png/48x48/edit-icon.png"))); // NOI18N
        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48-free-object-icons/png/48x48/Minus.png"))); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48-free-object-icons/png/48x48/Search.png"))); // NOI18N
        btnFind.setText("Tìm");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(btnChon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1024, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd)
                .addGap(72, 72, 72)
                .addComponent(btnUpdate)
                .addGap(93, 93, 93)
                .addComponent(btnDelete)
                .addGap(86, 86, 86)
                .addComponent(btnFind)
                .addGap(255, 255, 255))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(btnChon)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnFind))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbShowSDDVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbShowSDDVMouseClicked
        DefaultTableModel modelUpdate = (DefaultTableModel) tbAdd.getModel();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int row = tbShowSDDV.getSelectedRow();

        if (row >= 0) {
            String maDP = tbShowSDDV.getValueAt(row, 0).toString();
            modelUpdate.setRowCount(0);

            SuDungDichVuDAO dao = new SuDungDichVuDAO();
            List<SuDungDichVu> list = dao.findByMaDP(maDP); 

            for (SuDungDichVu hd : list) {
                modelUpdate.addRow(new Object[]{
                    hd.getMaDP(),
                    hd.getMaDV(),
                    sdf.format(hd.getNgaySD()),
                    hd.getSoLuong()
                });
            }
        }     
    }//GEN-LAST:event_tbShowSDDVMouseClicked

    private void tbShowDVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbShowDVMouseClicked
        DefaultTableModel modelUpdate = (DefaultTableModel) tbAdd.getModel();
        int row = tbShowDV.getSelectedRow();
        if (row >= 0) {
            String maDV = tbShowDV.getValueAt(row, 0).toString();
            modelUpdate.setRowCount(0);
            DichVuDAO dao = new DichVuDAO();
            List<DichVu> list = new ArrayList<>();
            list.add((DichVu) dao.findById(maDV));

            for (DichVu hd : list) {
                modelUpdate.addRow(new Object[]{
                    hd.getMaDV(),
                    hd.getTenDV(),
                    hd.getGiaDV(),
                    hd.getMoTa()
                });
            }
        }   
    }//GEN-LAST:event_tbShowDVMouseClicked

    private void btnChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonActionPerformed
        JPopupMenu pop = new JPopupMenu();

        JMenuItem m1 = new JMenuItem("Bảng 1: Sử Dụng Dịch Vụ");
        JMenuItem m2 = new JMenuItem("Bảng 2: Dịch Vụ");

        // Gắn các sự kiện chọn bảng
        m1.addActionListener(e -> loadEmptyTable(1));
        m2.addActionListener(e -> loadEmptyTable(2));

        pop.add(m1);
        pop.add(m2);

        // Hiện popup ngay dưới nút bấm
        pop.show(btnChon, 0, btnChon.getHeight());
    }//GEN-LAST:event_btnChonActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        SuDungDichVuDAO SDDVD = new SuDungDichVuDAO();
        DichVuDAO DVD = new DichVuDAO();

        DefaultTableModel modelAdd = (DefaultTableModel) tbAdd.getModel();
        DefaultTableModel modelShowSDDV = (DefaultTableModel) tbShowSDDV.getModel();
        DefaultTableModel modelShowDV = (DefaultTableModel) tbShowDV.getModel();

        switch(check){
            case 1 -> {
                String maDP = modelAdd.getValueAt(0, 0).toString();
                String maDV = modelAdd.getValueAt(0, 1).toString();
                String ngaySDStr = modelAdd.getValueAt(0, 2).toString().trim();
                String soLuong = modelAdd.getValueAt(0, 3).toString();

                Date ngaySD = Date.valueOf(ngaySDStr);

                modelShowSDDV.addRow(new Object[]{
                    maDP, maDV, ngaySDStr, soLuong
                });

                SuDungDichVu sddv = new SuDungDichVu();
                sddv.setMaDP(maDP);
                sddv.setMaDV(maDV);
                sddv.setNgaySD(ngaySD);
                sddv.setSoLuong(Integer.parseInt(soLuong));
                
                if (SDDVD.insert(sddv)) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi khi thêm!");
                }
            }
            case 2 -> {
                String maDV = modelAdd.getValueAt(0, 0).toString();
                String tenDV = modelAdd.getValueAt(0, 1).toString();
                String giaDV = modelAdd.getValueAt(0, 2).toString();
                String moTa = modelAdd.getValueAt(0, 3).toString();

                modelShowDV.addRow(new Object[]{
                    maDV,tenDV,giaDV,moTa
                });

                DichVu dv = new DichVu(maDV,tenDV,Integer.parseInt(giaDV),moTa);
                
                if (DVD.insert(dv)) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi khi thêm!");
                }
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        
        SuDungDichVuDAO SDDVD = new SuDungDichVuDAO();
        DichVuDAO DVD = new DichVuDAO();

        DefaultTableModel modelAdd = (DefaultTableModel) tbAdd.getModel();
        DefaultTableModel modelShowSDDV = (DefaultTableModel) tbShowSDDV.getModel();
        DefaultTableModel modelShowDV = (DefaultTableModel) tbShowDV.getModel();
        
        switch(check){
            case 1 -> {
                int row = tbShowSDDV.getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 hàng để sửa.");
                    return;
                }

                String maDP = modelAdd.getValueAt(0, 0).toString();
                String maDV = modelAdd.getValueAt(0, 1).toString();
                String ngaySDStr = modelAdd.getValueAt(0, 2).toString().trim();
                String soLuong = modelAdd.getValueAt(0, 3).toString();

                Date ngaySD = Date.valueOf(ngaySDStr);

                modelShowSDDV.setValueAt(maDP, row, 0);
                modelShowSDDV.setValueAt(maDV, row, 1);
                modelShowSDDV.setValueAt(ngaySDStr, row, 2);
                modelShowSDDV.setValueAt(soLuong, row, 3);

                SuDungDichVu sddv = new SuDungDichVu(maDP,maDV,ngaySD,Integer.parseInt(soLuong));

                if (SDDVD.update(sddv)) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật!");
                }
            }
            case 2 -> {
                int row = tbShowDV.getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 hàng để sửa.");
                    return;
                }

                String maDV = modelAdd.getValueAt(0, 0).toString();
                String tenDV = modelAdd.getValueAt(0, 1).toString();
                String giaDV = modelAdd.getValueAt(0, 2).toString();
                String moTa = modelAdd.getValueAt(0, 3).toString();

                modelShowDV.setValueAt(maDV, row, 0);
                modelShowDV.setValueAt(tenDV, row, 1);
                modelShowDV.setValueAt(giaDV, row, 2);
                modelShowDV.setValueAt(moTa, row, 3);

                DichVu dv = new DichVu(maDV, tenDV, Integer.parseInt(giaDV), moTa);

                if (DVD.update(dv)) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật!");
                }
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        switch(check){
            case 1 -> {
                int row = tbShowSDDV.getSelectedRow();

                if (row < 0) {
                    JOptionPane.showMessageDialog(this, "Bạn phải chọn dòng để xóa!");
                    return;
                }

                String maDP = tbShowSDDV.getValueAt(row, 0).toString();
                String maDV = tbShowSDDV.getValueAt(row, 1).toString();
                String ngaySDstr = tbShowSDDV.getValueAt(row, 2).toString();
                Date ngaySD = Date.valueOf(ngaySDstr);

                SuDungDichVuDAO dao = new SuDungDichVuDAO();

                if (dao.delete(maDP, maDV, ngaySD)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    DefaultTableModel model = (DefaultTableModel) tbShowSDDV.getModel();
                    model.removeRow(row);
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại!");
                }
            }
            
            case 2 -> {
                int row = tbShowDV.getSelectedRow();

                if (row < 0) {
                    JOptionPane.showMessageDialog(this, "Bạn phải chọn dòng để xóa!");
                    return;
                }

                String maDV = tbShowDV.getValueAt(row, 0).toString();
                
                DichVuDAO dao = new DichVuDAO();

                if (dao.delete(maDV)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    DefaultTableModel model = (DefaultTableModel) tbShowDV.getModel();
                    model.removeRow(row);
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại!");
                }
            }
            
            default -> {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn bảng muốn xóa!");
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        switch(check){
            case 1 -> {
                String maDP = JOptionPane.showInputDialog("Nhập mã Đặt Phòng:");

                SuDungDichVuDAO dao = new SuDungDichVuDAO();
                List<SuDungDichVu> list = dao.findByMaDP(maDP);

                DefaultTableModel model = (DefaultTableModel) tbAdd.getModel();
                model.setRowCount(0);

                if (list != null && !list.isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    for (SuDungDichVu sddv : list) {
                        model.addRow(new Object[]{
                            sddv.getMaDP(),
                            sddv.getMaDV(),
                            sdf.format(sddv.getNgaySD()),
                            sddv.getSoLuong()
                        });
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy!");
                }
            }
            case 2 -> {
                String maDV = JOptionPane.showInputDialog("Nhập mã Dịch Vụ:");

                DichVuDAO dao = new DichVuDAO();
                DichVu dv = (DichVu) dao.findById(maDV);

                DefaultTableModel model = (DefaultTableModel) tbAdd.getModel();
                model.setRowCount(0);

                if (dv != null) {
                    model.addRow(new Object[]{
                        dv.getMaDV(),
                        dv.getGiaDV(),
                        dv.getGiaDV(),
                        dv.getMoTa()
                    });
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy!");
                }
            }
        }
    }//GEN-LAST:event_btnFindActionPerformed

    
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
        java.awt.EventQueue.invokeLater(() -> new DichVu_Form().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAdd1;
    private javax.swing.JButton btnAdd2;
    private javax.swing.JButton btnChon;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tbAdd;
    private javax.swing.JTable tbShowDV;
    private javax.swing.JTable tbShowSDDV;
    // End of variables declaration//GEN-END:variables

}
