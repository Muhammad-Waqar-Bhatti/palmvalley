/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palm.valley.utill;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Dell
 */
public class UtilityClass {
    
    public static void searchFromTable(JTable table,String search){
        TableRowSorter tableRowSorter = new TableRowSorter(table.getModel());
        table.setRowSorter(tableRowSorter);
        tableRowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + search));
    }
}
