/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan;

import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.sql.*;
import java.text.*;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS M409D
 */

public class frmTransaksi extends javax.swing.JFrame {
    Connection con;
    ResultSet RsBrg;
    ResultSet RsKons;
    Statement stm;
    double total=0;
    String tanggal,tgl1;
    Boolean edit=false;
    DefaultTableModel tableModel = new DefaultTableModel(
    new Object [][] {},
    new String [] {"Kd Barang", "Nama Barang","Harga Barang","Jumlah","Total"}); 
    String idBrg;
    String namaBrg;
    String hargaBrg;
    /**
     * Creates new form frmtrans
     */
    public frmTransaksi() {
        initComponents();
        open_db();
        inisialisasi_tabel();
        aktif(false);
        setTombol(true);
        txtTgl.setModel(new SpinnerDateModel());
        txtTgl.setEditor(new JSpinner.DateEditor(txtTgl,"yyy/MM/dd"));
        format_tanggal();
    }
    private void hitung_jual(){
        double xtot,xhrg;
        int xjml;
        xhrg=Double.parseDouble(txtHarga.getText());
        xjml=Integer.parseInt(txtJml.getText());
        xtot=xhrg*xjml;
        String xtotal=Double.toString(xtot);
        txtTot.setText(xtotal);
        total=total+xtot;
        txtTotal.setText(Double.toString(total));
    }
    private void open_db(){
     try{
        KoneksiMysql kon = new KoneksiMysql("localhost","root","","penjualan");
        con = kon.getConnection();
        }catch (Exception e) {
        System.out.println("Error : "+e);
        }
    }
    private void baca_konsumen(){
        try{
            // cmbKd_Kons.removeAllItems();
            stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=stm.executeQuery("select kd_kons,nm_kons from konsumen");
            rs.beforeFirst();
            while(rs.next()){
                //omboBox.addItem(new TypeEntry(rs.getInt(1),rs.getString(2).trim()));
                //cmbKd_Kons.addItem(new TypeEntry(RsKons.getString(1).trim(),RsKons.getString(2).trim()));
                cmbKd_Kons.addItem(rs.getString(1).trim());
            }
            rs.close();
        }
        catch(SQLException e)
        {
        System.out.println("Error : "+e);
        }
    }
    
    public void inisialisasi_tabel()
{
    tblJual.setModel(tableModel);
}
    
    private void baca_barang(){
        try{
            // cmbKd_Brg.removeAllItems();
            stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=stm.executeQuery("select * from barang");
            rs.beforeFirst();
            while(rs.next()){
                cmbKd_Brg.addItem(rs.getString(1).trim());
            }
            rs.close();
        }catch(SQLException e){
            System.out.println("Error : "+e);
        }
    }
    
    private void detail_barang(String xkode){
        try{
            stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=stm.executeQuery("select * from barang where kd_brg='"+xkode+"'");
            rs.beforeFirst();
            while(rs.next()){
                txtNm_Brg.setText(rs.getString(2).trim());
                txtHarga.setText(Double.toString((Double)rs.getDouble(4)));
                //txtJml.setText(Double.toString((Double)rs.getDouble(6)));
            }
            rs.close();
        }catch(SQLException e){
            System.out.println("Error : "+e);
        }
    } 
    private void detail_konsumen(String xkode){
        try{
            stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=stm.executeQuery("select * from konsumen where kd_kons='"+xkode+"'");
            rs.beforeFirst();
            while(rs.next()){
                txtNama.setText(rs.getString(2).trim());
            }
            rs.close();
        }catch(SQLException e){
            System.out.println("Error : "+e);
        }
    }
    
    private void kosong(){
        txtNoJual.setText("");
        txtNama.setText("");
        txtHarga.setText("");
        txtTotal.setText("");
        DefaultTableModel dataModel = (DefaultTableModel) tblJual.getModel();
        if (tblJual.getRowCount() > 0) {
        for (int i = tblJual.getRowCount() - 1; i > -1; i--) {
        dataModel.removeRow(i);
}
}
    }
    //method kosongkan detail jual
    private void kosong_detail(){
        txtNm_Brg.setText("");
        txtHarga.setText("");
        txtJml.setText("");
        txtTot.setText("");
    }
    //method aktif on/off isian
    private void aktif(boolean x){
        cmbKd_Kons.setEnabled(x);
        cmbKd_Brg.setEnabled(x);
        txtTgl.setEnabled(x);
        txtJml.setEditable(x);
        txtTgl.setEnabled(x);
        txtJml.setEditable(x);
    }
    //method set tombol on/off
    private void setTombol(boolean t){
        cmdTambah.setEnabled(t);
        cmdSimpan.setEnabled(!t);
        cmdBatal.setEnabled(!t);
        cmdKeluar.setEnabled(t);
        cmdHapusItem.setEnabled(!t);
    }
    private void nomor_jual() throws SQLException{
          try{
           stm=con.createStatement();
           ResultSet rs=stm.executeQuery("select no_jual from jual");
           int brs=0;
           while(rs.next()){
               brs=rs.getRow();
           }
            if(brs==0)
                txtNoJual.setText("1");
            else{int nom=brs+1;
                txtNoJual.setText(Integer.toString(nom));
            }
            rs.close();
        }catch(SQLException e){
            System.out.println("Error : "+e);
        }
    }
    
    private void simpan_ditabel(){
          try{
            String tKode=cmbKd_Brg.getSelectedItem().toString();
            String tNama=txtNm_Brg.getText();
            double hrg=Double.parseDouble(txtHarga.getText());
            int jml=Integer.parseInt(txtJml.getText());
            double tot=Double.parseDouble(txtTot.getText());
            tableModel.addRow(new Object[]{tKode,tNama,hrg,jml,tot});
            inisialisasi_tabel();
        }catch(Exception e){
            System.out.println("Error:"+e);
        }
    }
    
    private void simpan_transaksi(){
       try{
            String xnojual=txtNoJual.getText();
            format_tanggal();
            String xkode=cmbKd_Kons.getSelectedItem().toString();
            String msql="insert into jual values('"+xnojual+"','"+xkode+"','"+tanggal+"')";
            stm.executeUpdate(msql);
            for(int i=0;i<tblJual.getRowCount();i++)
            {
                String xkd=(String)tblJual.getValueAt(i,0);
                double xhrg=(Double)tblJual.getValueAt(i,2);
                int xjml=(Integer)tblJual.getValueAt(i,3);
                String zsql="insert into djual values('"+xnojual+"','"+xkd+"',"+xhrg+","+xjml+")";
                stm.executeUpdate(zsql);
                String ysql="update barang set stok=stok-"+xjml+" where kd_brg='"+xkd+"'";
                stm.executeUpdate(ysql);
            }
        }catch(SQLException e){
            System.out.println("Error:"+e);
        }
    }
    
    private void format_tanggal(){
        String DATE_FORMAT = "yyyy-MM-dd";
        java.text.SimpleDateFormat sdf = new
        java.text.SimpleDateFormat(DATE_FORMAT);
        Calendar c1 = Calendar.getInstance();
        int year=c1.get(Calendar.YEAR);
        int month=c1.get(Calendar.MONTH)+1;
        int day=c1.get(Calendar.DAY_OF_MONTH);
        tgl1=sdf.format(txtTgl.getValue());
        tanggal=Integer.toString(year)+"-"+Integer.toString(month)+"-"+Integer.toString(day);
        System.out.println("Tanggal : " + tgl1);
        System.out.println("Tanggal2 : " + tanggal);
    }

    private void setField() {
        int row=tblJual.getSelectedRow();
        cmbKd_Brg.setSelectedItem((String)tblJual.getValueAt(row,0));
        txtNm_Brg.setText((String)tblJual.getValueAt(row,1));
        String harga = Double.toString((Double)tblJual.getValueAt(row,2));
        txtHarga.setText(harga);
        String jumlah=Integer.toString((Integer)tblJual.getValueAt(row,3));
        txtJml.setText(jumlah);
        String total=Double.toString((Double)tblJual.getValueAt(row,4));
        txtTot.setText(total);
    }


    private class PrintingTask extends SwingWorker<Object, Object> {
        private final MessageFormat headerFormat;
        private final MessageFormat footerFormat;
        private final boolean interactive;
        private volatile boolean complete = false;
        private volatile String message;
        public PrintingTask(MessageFormat header, MessageFormat footer,
        boolean interactive) {
        this.headerFormat = header;
        this.footerFormat = footer;
        this.interactive = interactive;
        }
        @Override
        protected Object doInBackground() {
        try {
        complete = text.print(headerFormat, footerFormat,
        true, null, null, interactive);
        message = "Printing " + (complete ? "complete" : "canceled");
        } catch (PrinterException ex) {
        message = "Sorry, a printer error occurred";
        } catch (SecurityException ex) {
        message =
        "Sorry, cannot access the printer due to security reasons";
        }
        return null;
        }
        @Override
        protected void done() {
        message(!complete, message);
        }
    }
    private MessageFormat createFormat(String source) {
        String text = source;
        if (text != null && text.length() > 0) {
            try {
                return new MessageFormat(text);
            } catch (IllegalArgumentException e) {
                error("Sorry, this format is invalid.");
            }
        }
        return null;
    }
    private void message(boolean error, String msg) {
        int type = (error ? JOptionPane.ERROR_MESSAGE :
        JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(this, msg, "Printing", type);
    }
    private void error(String msg) {
        message(true, msg);
    }
    public static void createAndShowGUI() {
        JFrame f = new frmTransaksi();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    /**
     *
     */
    public void itemTerpilih(){
        frmSelectBarang fDB = new frmSelectBarang();
        fDB.fAB = this;
        txtid.setText(idBrg);
        txtNm_Brg.setText(namaBrg);
        txtHarga.setText(hargaBrg);
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
        txtNoJual = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTgl = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbKd_Kons = new javax.swing.JComboBox<>();
        txtNama = new javax.swing.JTextField();
        cmbKd_Brg = new javax.swing.JComboBox<>();
        txtNm_Brg = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        txtTot = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblJual = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        cmdSimpan = new javax.swing.JButton();
        cmdBatal = new javax.swing.JButton();
        cmdCetak = new javax.swing.JButton();
        cmdKeluar = new javax.swing.JButton();
        cmdTambah = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        text = new javax.swing.JTextArea();
        btnPilih = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtKembali = new javax.swing.JTextField();
        txtJml = new javax.swing.JTextField();
        txtid = new javax.swing.JTextField();
        cmdHapusItem = new javax.swing.JButton();
        txtBayar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("No Jual");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Tgl. Jual");

        txtTgl.setModel(new javax.swing.SpinnerDateModel());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Kode Konsumen");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setText("Nama Konsumen");

        cmbKd_Kons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKd_KonsActionPerformed(evt);
            }
        });

        cmbKd_Brg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKd_BrgActionPerformed(evt);
            }
        });

        tblJual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Kd Barang", "Nama Barang", "Harga Barang", "Jumlah", "Total"
            }
        ));
        tblJual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblJualMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblJual);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setText("Total");

        cmdSimpan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cmdSimpan.setText("Simpan");
        cmdSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSimpanActionPerformed(evt);
            }
        });

        cmdBatal.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cmdBatal.setText("Batal");
        cmdBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBatalActionPerformed(evt);
            }
        });

        cmdCetak.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cmdCetak.setText("Cetak");
        cmdCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCetakActionPerformed(evt);
            }
        });

        cmdKeluar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cmdKeluar.setText("Keluar");
        cmdKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdKeluarActionPerformed(evt);
            }
        });

        cmdTambah.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cmdTambah.setText("Tambah");
        cmdTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdTambahActionPerformed(evt);
            }
        });

        text.setColumns(20);
        text.setRows(5);
        jScrollPane2.setViewportView(text);

        btnPilih.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnPilih.setText("Pilih Barang");
        btnPilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPilihActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("Bayar");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setText("Kembalian");

        txtJml.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtJmlKeyPressed(evt);
            }
        });

        cmdHapusItem.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cmdHapusItem.setText("Hapus Item");
        cmdHapusItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdHapusItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtKembali))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtBayar)))
                        .addGap(6, 6, 6))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNoJual, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                            .addComponent(txtTgl))
                        .addGap(123, 123, 123)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbKd_Kons, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmdHapusItem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPilih)
                                .addGap(41, 41, 41))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmbKd_Brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(txtNm_Brg, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtJml, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtTot, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdTambah)
                        .addGap(34, 34, 34)
                        .addComponent(cmdSimpan)
                        .addGap(40, 40, 40)
                        .addComponent(cmdBatal)
                        .addGap(40, 40, 40)
                        .addComponent(cmdCetak)
                        .addGap(41, 41, 41)
                        .addComponent(cmdKeluar)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNoJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(cmbKd_Kons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbKd_Brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNm_Brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJml, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPilih)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdHapusItem))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdTambah)
                    .addComponent(cmdSimpan)
                    .addComponent(cmdBatal)
                    .addComponent(cmdCetak)
                    .addComponent(cmdKeluar))
                .addContainerGap(99, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCetakActionPerformed
        // TODO add your handling code here:
      String ctk="Nota Penjualan\nNo :"+txtNoJual.getText()+"\nTanggal : "+tanggal;
        ctk=ctk+"\n"+"-------------------------------------------------------------------------------------------------";
        ctk=ctk+"\n"+"Kode\tNama Barang\tHarga\tJml\tTotal";
        ctk=ctk+"\n"+"-------------------------------------------------------------------------------------------------";
        for(int i=0;i<tblJual.getRowCount();i++){
            String xkd=(String)tblJual.getValueAt(i,0);
            String xnama=(String)tblJual.getValueAt(i,1);
            double xhrg=(Double)tblJual.getValueAt(i,2);
            int xjml=(Integer)tblJual.getValueAt(i,3);
            double xtot=(Double)tblJual.getValueAt(i,4);
            ctk=ctk+"\n"+xkd+"\t"+xnama+"\t"+xhrg+"\t"+xjml+"\t"+xtot;
        }
        ctk=ctk+"\n"+"-------------------------------------------------------------------------------------------------";
        text.setText(ctk);
        String headerField="";
        String footerField="";
        MessageFormat header = createFormat(headerField);
        MessageFormat footer = createFormat(footerField);
        boolean interactive = true;//interactiveCheck.isSelected();
        boolean background = true;//backgroundCheck.isSelected();
        PrintingTask task = new PrintingTask(header, footer, interactive);
        if (background) {
            task.execute();
        } else {
            task.run();
        }
    
    }//GEN-LAST:event_cmdCetakActionPerformed

    private void cmbKd_KonsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKd_KonsActionPerformed
        // TODO add your handling code here:
        JComboBox cKd_Kons = (javax.swing.JComboBox)evt.getSource();
        //Membaca Item Yang Terpilih — > String
        String sKd_Kons = (String)cKd_Kons.getSelectedItem();
        detail_konsumen(sKd_Kons);
    }//GEN-LAST:event_cmbKd_KonsActionPerformed

    private void cmbKd_BrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKd_BrgActionPerformed
        // TODO add your handling code here:
        JComboBox cKd_Brg = (javax.swing.JComboBox)evt.getSource();
        //Membaca Item Yang Terpilih — > String
        String sKd_Brg = (String)cKd_Brg.getSelectedItem();
        detail_barang(sKd_Brg);
        txtJml.setText("");
        txtTot.setText("");
    }//GEN-LAST:event_cmbKd_BrgActionPerformed

    private void txtJmlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJmlKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        hitung_jual();
        simpan_ditabel();
    }//GEN-LAST:event_txtJmlKeyPressed
    }
    private void cmdSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSimpanActionPerformed
        // TODO add your handling code here:
        simpan_transaksi();
        aktif(false);
        setTombol(true);
        kosong();
        kosong_detail();
    }//GEN-LAST:event_cmdSimpanActionPerformed

    private void cmdBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBatalActionPerformed
        // TODO add your handling code here:
        aktif(false);
        setTombol(true);
        kosong();
    }//GEN-LAST:event_cmdBatalActionPerformed

    private void cmdKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdKeluarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_cmdKeluarActionPerformed

    private void btnPilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPilihActionPerformed
        // TODO add your handling code here:
        frmSelectBarang fDB = new frmSelectBarang();
        fDB.fAB = this;
        fDB.setVisible(true);
        fDB.setResizable(false);
    }//GEN-LAST:event_btnPilihActionPerformed

    private void cmdHapusItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdHapusItemActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dataModel = (DefaultTableModel) tblJual.getModel();
        int row=tblJual.getSelectedRow();
        
        if (tblJual.getRowCount() > 0) {
            dataModel.removeRow(row);
        }
        
        double xtot,xhrg;
        int xjml;
        xhrg=Double.parseDouble(txtHarga.getText());
        xjml=Integer.parseInt(txtJml.getText());
        xtot=xhrg*xjml;
        total=total-xtot;
        txtTotal.setText(Double.toString(total));
    }//GEN-LAST:event_cmdHapusItemActionPerformed

    private void tblJualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblJualMouseClicked
        // TODO add your handling code here:
        setField();
    }//GEN-LAST:event_tblJualMouseClicked

    private void cmdTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdTambahActionPerformed
        // TODO add your handling code here:
        aktif(true);
        setTombol(false);
        kosong();
        baca_konsumen();
        baca_barang();
        try {
            nomor_jual();
        } catch (SQLException ex) {
            Logger.getLogger(frmTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmdTambahActionPerformed

    
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
            java.util.logging.Logger.getLogger(frmTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPilih;
    private javax.swing.JComboBox<String> cmbKd_Brg;
    private javax.swing.JComboBox<String> cmbKd_Kons;
    private javax.swing.JButton cmdBatal;
    private javax.swing.JButton cmdCetak;
    private javax.swing.JButton cmdHapusItem;
    private javax.swing.JButton cmdKeluar;
    private javax.swing.JButton cmdSimpan;
    private javax.swing.JButton cmdTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblJual;
    private javax.swing.JTextArea text;
    private javax.swing.JTextField txtBayar;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtJml;
    private javax.swing.JTextField txtKembali;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNm_Brg;
    private javax.swing.JTextField txtNoJual;
    private javax.swing.JSpinner txtTgl;
    private javax.swing.JTextField txtTot;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtid;
    // End of variables declaration//GEN-END:variables
}
