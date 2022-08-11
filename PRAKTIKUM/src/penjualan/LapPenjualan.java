/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan;

import java.io.File;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS M409D
 */
public class LapPenjualan extends javax.swing.JFrame {
    Connection Con;
    ResultSet RsJual;
    Statement stm;
    PreparedStatement pstat;
    String tgl1,tgl2;
    private Object[][] dataTable = null;
    private String[] header = {"No Jual","Tanggal","Nama Konsumen","Kode Barang","Nama Barang","Harga Satuan","Jumlah","Total"};
    DefaultTableModel tableModel = new DefaultTableModel(new Object [][] {},header);

    /**
     * Creates new form LapPenjualan
     */
    public LapPenjualan() {
        initComponents();
        dtAwal.setDate(new Date());
        dtAkhir.setDate(new Date());
        open_db();
        baca_data();
    }
        
    
    private void open_db()
        {
            try{
                KoneksiMysql kon = new KoneksiMysql ("localhost","root","","penjualan");
                Con = kon.getConnection();
            }catch (Exception e) {
                System.out.println("Error : "+e);
            }
        }
        
    private void baca_data()
        {
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                tgl1=sdf.format(dtAwal.getDate());
                tgl2=sdf.format(dtAkhir.getDate());
                stm = Con.createStatement();
                pstat = Con.prepareStatement("SELECT a.no_jual,a.tgl_jual,c.nm_kons,d.kd_brg,d.nm_brg,b.harga_jual,b.jml_jual,(b.harga_jual*b.jml_jual) AS 'totjual' " +"FROM jual a LEFT JOIN djual b ON (a.no_jual=b.no_jual) " +"LEFT JOIN konsumen c ON (a.kd_kons=c.kd_kons) " +"LEFT JOIN barang d ON (b.kd_brg=d.kd_brg)" +"where date(a.tgl_jual)>=" + "date('"+ tgl1+"') and date(a.tgl_jual)<=" + "date('"+tgl2+"')order by a.tgl_jual desc",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                RsJual = pstat.executeQuery();
                ResultSetMetaData meta = RsJual.getMetaData();
                int col = meta.getColumnCount();
                int baris = 0;
                while(RsJual.next()) {
                    baris = RsJual.getRow();
                }
                dataTable = new Object[baris][col];
                int x = 0;
                RsJual.beforeFirst();
                while(RsJual.next()) {
                    dataTable[x][0] = RsJual.getString("no_jual");
                    dataTable[x][1] = RsJual.getDate("tgl_jual");
                    dataTable[x][2] = RsJual.getString("nm_kons");
                    dataTable[x][3] = RsJual.getString("kd_brg");
                    dataTable[x][4] = RsJual.getString("nm_brg");
                    dataTable[x][5] = RsJual.getDouble("harga_jual");
                    dataTable[x][6] = RsJual.getInt("jml_jual");
                    dataTable[x][7] = RsJual.getDouble("totjual");
                    x++;
                }
                tblLapJual.setModel(new DefaultTableModel(dataTable,header));
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
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
        dtAwal = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        dtAkhir = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLapJual = new javax.swing.JTable();
        cmdCari = new javax.swing.JButton();
        cmdExport = new javax.swing.JButton();
        cmdKeluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Tgl");

        dtAwal.setDateFormatString("yyyy-MM-dd");

        jLabel2.setText("s/d");

        dtAkhir.setDateFormatString("yyyy-MM-dd");

        tblLapJual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No Jual", "Tanggal", "Nama Konsumen", "Kode Barang", "Nama Barang", "Harga Satuan", "Jumlah", "Total"
            }
        ));
        jScrollPane1.setViewportView(tblLapJual);

        cmdCari.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        cmdCari.setText("Cari");
        cmdCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCariActionPerformed(evt);
            }
        });

        cmdExport.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        cmdExport.setText("Export");
        cmdExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExportActionPerformed(evt);
            }
        });

        cmdKeluar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        cmdKeluar.setText("Keluar");
        cmdKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdKeluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(dtAwal, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel2)
                        .addGap(81, 81, 81)
                        .addComponent(dtAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(115, 115, 115)
                        .addComponent(cmdCari))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 994, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(cmdExport)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdKeluar)
                .addGap(63, 63, 63))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dtAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtAwal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdCari))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdExport)
                    .addComponent(cmdKeluar))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCariActionPerformed
        // TODO add your handling code here:
        baca_data();
    }//GEN-LAST:event_cmdCariActionPerformed

    private void cmdExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExportActionPerformed
        // TODO add your handling code here:
        try{
            ExportToExcel ex=new ExportToExcel(tblLapJual, new
    File("DataPenjualan.xls"));
            //exportToExcel(tblBrg, new File("DataBarang.xls"));
            JOptionPane.showMessageDialog(null, "Sukses Export data .....");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
}
    }//GEN-LAST:event_cmdExportActionPerformed

    private void cmdKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdKeluarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_cmdKeluarActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LapPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LapPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LapPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LapPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LapPenjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdCari;
    private javax.swing.JButton cmdExport;
    private javax.swing.JButton cmdKeluar;
    private com.toedter.calendar.JDateChooser dtAkhir;
    private com.toedter.calendar.JDateChooser dtAwal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblLapJual;
    // End of variables declaration//GEN-END:variables
}