/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author ASUS M409D
 */
public class ExportToExcel {
    public ExportToExcel(JTable table, File file) throws IOException {
        try{
            TableModel tableModel = table.getModel();
            FileWriter fOut = new FileWriter(file);
            
            for(int i = 0; i < tableModel.getColumnCount(); i++){
                fOut.write(tableModel.getColumnName(i)+"\t");
            }
            fOut.write("\n");
            
            for(int i = 0; i < tableModel.getRowCount(); i++){
                for(int j = 0; j < tableModel.getColumnCount(); j++){
                    fOut.write(tableModel.getValueAt(i, j).toString()+"\t");
            }
                fOut.write("\n");
            }
            
            fOut.close();
        } catch (Exception e){
            e.printStackTrace();
    }
}
}