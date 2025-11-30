/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gd_hotel.QLKS;

import com.hotel.dao.DoanhThuDAO;
import gd_hotel.MANAGER_PAGE;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;

/**
 *
 * @author HOANG PHI
 */
public class ThongKeDT_Form extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ThongKeDT_Form.class.getName());

    /**
     * Creates new form ThongKeDT_Form
     */
    public ThongKeDT_Form() {
        initComponents();
        
        DecimalFormat df = new DecimalFormat("#,###");

        DoanhThuDAO dtDAO = new DoanhThuDAO();
        ArrayList<Object[]> listT = dtDAO.getDoanhThuTheoThang();

        DefaultTableModel modelT = (DefaultTableModel) tbShowDoanhThuThang.getModel();
        modelT.setRowCount(0);

        for (Object[] row : listT) {
            modelT.addRow(new Object[]{
                row[0], row[1], df.format(row[2])
            });
        }
        
        
        ArrayList<Object[]> listN = dtDAO.getDoanhThuTheoNam();

        DefaultTableModel modelN = (DefaultTableModel) tbShowDoanhThuNam.getModel();
        modelN.setRowCount(0);

        for (Object[] row : listN) {
            modelN.addRow(new Object[]{
                row[0], df.format(row[1])
            });
        }
        
        veBieuDoThang();
        veBieuDoNam();
        
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ThongKeDT_Form.this.setVisible(false);

                java.awt.EventQueue.invokeLater(() -> new MANAGER_PAGE().setVisible(true));
            }
        });
    }
    
    
    public void veBieuDoNam() {
        try {
            // Lấy dữ liệu
            DoanhThuDAO dao = new DoanhThuDAO();
            ArrayList<Object[]> list = dao.getDoanhThuTheoNam();

            // Dataset
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (Object[] row : list) {
                int nam = Integer.parseInt(row[0].toString());
                double doanhThu = Double.parseDouble(row[1].toString());
                dataset.addValue(doanhThu, "Doanh Thu", String.valueOf(nam));
            }

            // Tạo biểu đồ cột
            JFreeChart chart = ChartFactory.createBarChart(
                    "Doanh Thu Theo Tháng",
                    "Năm",
                    "Doanh Thu (VND)",
                    dataset,
                    PlotOrientation.VERTICAL,
                    false, true, false
            );

            // Lấy CategoryPlot để add Line Chart
            CategoryPlot plot = chart.getCategoryPlot();
            BarRenderer barRenderer = new BarRenderer();
            barRenderer.setSeriesPaint(0, new Color(0, 123, 255)); // Xanh dương cho "Doanh Thu"
            plot.setRenderer(0, barRenderer);
            
            // Tạo panel chứa biểu đồ
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(pnBieuDoNam.getWidth(), pnBieuDoNam.getHeight()));
            chartPanel.setMouseWheelEnabled(true);

            // Xóa nội dung cũ để tránh bị chồng chéo
            pnBieuDoNam.removeAll();
            pnBieuDoNam.setLayout(new BorderLayout());
            pnBieuDoNam.add(chartPanel, BorderLayout.CENTER);

            pnBieuDoNam.validate();
            pnBieuDoNam.repaint();

        } catch (Exception e) {
            System.out.println("Lỗi vẽ biểu đồ: " + e.getMessage());
        }
    }

    
    public void veBieuDoThang() {
        try {
            // Lấy dữ liệu
            DoanhThuDAO dao = new DoanhThuDAO();
            ArrayList<Object[]> list = dao.getDoanhThuTheoThang();

            // Dataset
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (Object[] row : list) {
                String thangNam = row[1].toString() + " - " + row[0].toString();
                double doanhThu = Double.parseDouble(row[2].toString());
                dataset.addValue(doanhThu, "Doanh Thu", String.valueOf(thangNam));
            }

            // Tạo biểu đồ cột
            JFreeChart chart = ChartFactory.createBarChart(
                    "Doanh Thu Theo Tháng",
                    "Tháng - Năm",
                    "Doanh Thu (VND)",
                    dataset,
                    PlotOrientation.VERTICAL,
                    false, true, false
            );

            // Lấy CategoryPlot để add Line Chart
            CategoryPlot plot = chart.getCategoryPlot();
            BarRenderer barRenderer = new BarRenderer();
            barRenderer.setSeriesPaint(0, new Color(0, 123, 255)); // Xanh dương cho "Doanh Thu"
            plot.setRenderer(0, barRenderer);
            
            // Tạo panel chứa biểu đồ
            ChartPanel chartPanel = new ChartPanel(chart);
            
            chartPanel.setPreferredSize(new Dimension(pnBieuDoThang.getWidth(), pnBieuDoThang.getHeight()));
            chartPanel.setMouseWheelEnabled(true);

            // Xóa nội dung cũ để tránh bị chồng chéo
            pnBieuDoThang.removeAll();
            pnBieuDoThang.setLayout(new BorderLayout());
            pnBieuDoThang.add(chartPanel, BorderLayout.CENTER);

            pnBieuDoThang.validate();
            pnBieuDoThang.repaint();

        } catch (Exception e) {
            System.out.println("Lỗi vẽ biểu đồ: " + e.getMessage());
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbShowDoanhThuThang = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbShowDoanhThuNam = new javax.swing.JTable();
        pnBieuDoNam = new javax.swing.JPanel();
        pnBieuDoThang = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THỐNG KÊ DOANH THU KHÁCH SẠN");

        tbShowDoanhThuThang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Năm", "Tháng", "Tổng Doanh Thu"
            }
        ));
        jScrollPane1.setViewportView(tbShowDoanhThuThang);

        tbShowDoanhThuNam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Năm", "Tổng Doanh Thu"
            }
        ));
        jScrollPane2.setViewportView(tbShowDoanhThuNam);

        javax.swing.GroupLayout pnBieuDoNamLayout = new javax.swing.GroupLayout(pnBieuDoNam);
        pnBieuDoNam.setLayout(pnBieuDoNamLayout);
        pnBieuDoNamLayout.setHorizontalGroup(
            pnBieuDoNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnBieuDoNamLayout.setVerticalGroup(
            pnBieuDoNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 329, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnBieuDoThangLayout = new javax.swing.GroupLayout(pnBieuDoThang);
        pnBieuDoThang.setLayout(pnBieuDoThangLayout);
        pnBieuDoThangLayout.setHorizontalGroup(
            pnBieuDoThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnBieuDoThangLayout.setVerticalGroup(
            pnBieuDoThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 329, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnBieuDoThang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnBieuDoNam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnBieuDoNam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnBieuDoThang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        java.awt.EventQueue.invokeLater(() -> new ThongKeDT_Form().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnBieuDoNam;
    private javax.swing.JPanel pnBieuDoThang;
    private javax.swing.JTable tbShowDoanhThuNam;
    private javax.swing.JTable tbShowDoanhThuThang;
    // End of variables declaration//GEN-END:variables
}
