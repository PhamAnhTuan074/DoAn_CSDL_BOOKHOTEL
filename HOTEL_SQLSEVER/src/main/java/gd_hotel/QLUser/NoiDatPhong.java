/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gd_hotel.QLUser;

import com.hotel.dao.DichVuDAO;
import com.hotel.dao.PhongTrongDAO;
import com.hotel.model.DichVu;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HOANG PHI
 */
public class NoiDatPhong extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(NoiDatPhong.class.getName());

    /**
     * Creates new form PhongDuocDat
     */
    public NoiDatPhong() {
        initComponents();   
        LamMoBackGround();
        jPanel2.setOpaque(false);
        jPanel3.setOpaque(false);
        jPanel4.setOpaque(false);


        // 1. JTable trong suốt
        tbShowPT.setOpaque(false);
        tbShowPT.setBackground(new Color(0, 0, 0, 0));
        tbShowPT.setShowGrid(false);

        // 2. Cell Renderer trong suốt
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setOpaque(false);
        tbShowPT.setDefaultRenderer(Object.class, renderer);

        // 3. Header trong suốt
        tbShowPT.getTableHeader().setOpaque(false);
        tbShowPT.getTableHeader().setBackground(new Color(0,0,0,0));

        // 4. ScrollPane trong suốt
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);

        // 5. Cha của scrollpane (viewport parent)
        if (jScrollPane1.getParent() != null) {
            jScrollPane1.getParent().setBackground(new Color(0,0,0,0));
        }

        // 6. Thêm scrollPane vào JLabel nền
        jLabel1.setLayout(null);
        jLabel1.add(jScrollPane1);

        
        DecimalFormat df = new DecimalFormat("#,###");

        PhongTrongDAO ptDAO = new PhongTrongDAO();
        ArrayList<Object[]> listP = ptDAO.getPhongTrong(
            Date.valueOf("2025-12-01"),
            Date.valueOf("2025-12-03")
        );


        DefaultTableModel modelP = (DefaultTableModel) tbShowPT.getModel();
        modelP.setRowCount(0);

        for (Object[] row : listP) {
            modelP.addRow(new Object[]{
                row[0], row[1], row[2], row[3], row[4], df.format(row[5])
            });
        }
        
        
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                NoiDatPhong.this.setVisible(false);
                java.awt.EventQueue.invokeLater(() -> new USER_PAGE().setVisible(true));
                JOptionPane.showMessageDialog(rootPane, "Vui lòng đến thanh toán để mọi thứ được chấp thuận!");
                new ThanhToan().setVisible(true);
            }
        });
        
    }

    public void LamMoBackGround() {    

        ImageIcon icon = new ImageIcon("D:/PROGRAM LANGUAGE/Java/GD_HOTEL/src/main/resources/background/Home sweet Home 1200x675.png");
        int width = icon.getIconWidth();
        int height = icon.getIconHeight();
        Image img = icon.getImage();

        BufferedImage buffered = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = buffered.createGraphics();
        g2.drawImage(img, 0, 0, width, height, null);

        // phủ lớp mờ
        g2.setColor(new Color(0, 0, 0, 100));
        g2.fillRect(0, 0, width, height);
        g2.dispose();

        // set icon đã làm mờ
        jLabel1.setIcon(new ImageIcon(buffered));

    }

    
    public static class LuuDatPhong{
        public static LuuDatPhong datPhongCurrent;
        private Date ngayDat;
        private String ngayTra;
        private String ngayNhan;
        private String loaiPhong;
        private String loaiDV;
        private String maPhong;
        private String giaPhong;
        private double giaDV;

        public double getGiaDV() {
            return giaDV;
        }

        public void setGiaDV(double giaDV) {
            this.giaDV = giaDV;
        }

        public String getGiaPhong() {
            return giaPhong;
        }

        public void setGiaPhong(String giaPhong) {
            this.giaPhong = giaPhong;
        }

        public String getMaPhong() {
            return maPhong;
        }

        public void setMaPhong(String maPhong) {
            this.maPhong = maPhong;
        }

        public Date getNgayDat() {
            return ngayDat;
        }

        public void setNgayDat(Date ngayDat) {
            this.ngayDat = ngayDat;
        }

        public String getLoaiPhong() {
            return loaiPhong;
        }

        public void setLoaiPhong(String loaiPhong) {
            this.loaiPhong = loaiPhong;
        }

        public String getLoaiDV() {
            return loaiDV;
        }

        public void setLoaiDV(String loaiDV) {
            this.loaiDV = loaiDV;
        }
        
        public String getNgayNhan() {
            return ngayNhan;
        }

        public void setNgayNhan(String ngayNhan) {
            this.ngayNhan = ngayNhan;
        }
        
        public String getNgayTra() {
            return ngayTra;
        }

        public void setNgayTra(String ngayTra) {
            this.ngayTra = ngayTra;
        }
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbShowPT = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtDV = new javax.swing.JTextField();
        btnChonDV = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        cbbLoaiPhong = new javax.swing.JComboBox<>();
        btnDatPhong = new javax.swing.JButton();
        txtNgayNhan = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtNgayTra = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbShowPT.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        tbShowPT.setForeground(new java.awt.Color(255, 255, 255));
        tbShowPT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Phòng", "Số Phòng", "Tầng", "Trạng Thái", "Tên Phòng", "Giá Cơ Bản"
            }
        ));
        tbShowPT.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(tbShowPT);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 530, 200));

        jLabel2.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Phòng Trống");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 360, 30));

        jLabel3.setFont(new java.awt.Font("Segoe Script", 0, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ABC");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, 270, 90));

        jLabel4.setFont(new java.awt.Font("Segoe Script", 0, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("HOTEL");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 90, 270, 140));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("1. Giặt ủi thường - 30.000 - Tính theo kg");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("2. Giặt hấp cao cấp - 100.000 - Tính theo bộ");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("3. Ăn sáng Buffet - 150.000 - Vé người lớn");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("4. Ăn sáng trẻ em - 80.000 - Vé trẻ em dưới 1.2m");

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("5. Thuê xe máy tay ga - 150.000 - Tính theo ngày");

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("6. Đón tiễn sân bay - 300.000 - Xe 4 chỗ/lượt");

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("7. Massage Body - 450.000 - Vé 60 phút");

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("8. Sauna & Steam - 200.000 - Vé xông hơi");

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("9. MiniBar & Bia - 40.000 - Lon 330ml");

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("10. MiniBar & Nước suối - 20.000 - Chai 500ml");

        jLabel15.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Dịch Vụ");

        jLabel16.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Dịch Vụ Bạn Chọn");

        txtDV.setFont(new java.awt.Font("Serif", 0, 10)); // NOI18N
        txtDV.setText("có thể chọn nhiều DV cách nhau bởi dấu ','");
        txtDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDVActionPerformed(evt);
            }
        });

        btnChonDV.setText("Chọn");
        btnChonDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonDVActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 0, 51));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Vui lòng nhập số thứ tự của dịch vụ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(62, 62, 62))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnChonDV, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDV, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDV, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(btnChonDV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 260, 590, 370));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("1. Standard Single - Chứa tối đa 1 người");

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("2. Standard Double - Chứa tối đa 2 người");

        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("3. Deluxe City View  - Chứa tối đa 2 người");

        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("4. Deluxe Ocean View - Chứa tối đa 2 người");

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("5. Family Suite - Chứa tối đa 4 người");

        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("6. Executive Suite - Chứa tối đa 2 người");

        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("7. Presidential - Chứa tối đa 4 người");

        jLabel25.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Sức Chứa Các Loại Phòng");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 7, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, 280, 340));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        cbbLoaiPhong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Loại Phòng", "Standard Single", "Standard Double", "Deluxe City View", "Deluxe Ocean View", "Family Suite", "Executive Suite", "Presidential" }));

        btnDatPhong.setText("Đặt Phòng");
        btnDatPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatPhongActionPerformed(evt);
            }
        });

        txtNgayNhan.setText("yyyy-mm-dd");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Ngày Nhận:");

        txtNgayTra.setText("yyyy-mm-dd");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Ngày Trả:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cbbLoaiPhong, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(btnDatPhong)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNgayTra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                    .addComponent(txtNgayNhan, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgayNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addGap(18, 18, 18)
                .addComponent(cbbLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDatPhong))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 230, 230));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background/Home sweet Home 1200x675.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDVActionPerformed

    private void btnDatPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatPhongActionPerformed
        String ngayTra = txtNgayTra.getText().trim();
        String ngayNhan = txtNgayNhan.getText().trim();
        int index = cbbLoaiPhong.getSelectedIndex();

        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn loại phòng!");
            return;
        }
        String loaiPhong = cbbLoaiPhong.getItemAt(index);

        DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE;
        try {
            LocalDate parsedDateNhan = LocalDate.parse(ngayNhan, fmt);
            LocalDate parsedDateTra = LocalDate.parse(ngayTra, fmt);
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Ngày đặt không hợp lệ. Vui lòng nhập theo định dạng yyyy-MM-dd (ví dụ 2025-12-31).");
            return;
        }

        LuuDatPhong ldp = new LuuDatPhong();
        
        LocalDate todayVN = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        Date ngayDat = Date.valueOf(todayVN);
        ldp.setNgayDat(ngayDat);
        ldp.setLoaiPhong(loaiPhong);
        ldp.setNgayNhan(ngayNhan);
        ldp.setNgayTra(ngayTra);
        PhongTrongDAO ptDAO = new PhongTrongDAO();
        String maPhong = ptDAO.getMaPhongTrong(loaiPhong);
        ldp.setMaPhong(maPhong);
        String giaPhong = ptDAO.getGiaPhong(loaiPhong);
        ldp.setGiaPhong(giaPhong);
        LuuDatPhong.datPhongCurrent = ldp; 

        JOptionPane.showMessageDialog(this, "Đặt phòng thành công!");
    }//GEN-LAST:event_btnDatPhongActionPerformed

    private void btnChonDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonDVActionPerformed
        String chonDV = txtDV.getText().trim();
        String[] Ktra = chonDV.split(",");
        for (String x : Ktra) {
            if (!x.matches("[0-9]+")) {
                JOptionPane.showMessageDialog(this, "Bạn nhập sai quy định (chỉ nhập số, ngăn cách bằng dấu phẩy)!");
                return;
            }
        }

        DichVuDAO dvd = new DichVuDAO();
        double giaDV = 0;
        for (String x : Ktra){
            String maDV = "DV";
            if(x.length() < 2){
                maDV = maDV + "0" + x;
            }
            else{
                maDV += x;
            }
            DichVu dv = dvd.findById(maDV);
            giaDV += dv.getGiaDV();    
        }
        
        if (LuuDatPhong.datPhongCurrent == null) {
            LuuDatPhong.datPhongCurrent = new LuuDatPhong();   
            LuuDatPhong.datPhongCurrent.setLoaiPhong("");
            LuuDatPhong.datPhongCurrent.setNgayDat(null);
            LuuDatPhong.datPhongCurrent.setNgayNhan("");
            LuuDatPhong.datPhongCurrent.setNgayTra("");
            LuuDatPhong.datPhongCurrent.setMaPhong("");
            LuuDatPhong.datPhongCurrent.setGiaPhong("");
        }
        LuuDatPhong.datPhongCurrent.setLoaiDV(chonDV);
        LuuDatPhong.datPhongCurrent.setGiaDV(giaDV);
        JOptionPane.showMessageDialog(this, "Chọn Dịch Vụ Thành Công!");
        
    }//GEN-LAST:event_btnChonDVActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new NoiDatPhong().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonDV;
    private javax.swing.JButton btnDatPhong;
    private javax.swing.JComboBox<String> cbbLoaiPhong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbShowPT;
    private javax.swing.JTextField txtDV;
    private javax.swing.JTextField txtNgayNhan;
    private javax.swing.JTextField txtNgayTra;
    // End of variables declaration//GEN-END:variables
}
