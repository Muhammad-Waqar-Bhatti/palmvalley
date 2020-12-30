/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palm.valley.daoimpl;

import com.palm.valley.beans.TaxBean;
import com.palm.valley.connection.DBConnection;
import com.palm.valley.dao.TaxDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Dell
 */
public class TaxDAOImpl implements TaxDAO{
    Connection con = DBConnection.getConnection();
    @Override
    public Integer addTax(TaxBean tax) {
     
        try{
            PreparedStatement pst = con.prepareStatement("insert into tax(tax,from_date,to_date) values(?,?,?)");
            pst.setDouble(1, tax.getTax());
            pst.setDate(2,  new java.sql.Date(tax.getFromDate().getTime()));
            pst.setDate(3, new java.sql.Date(tax.getToDate().getTime()));
            return pst.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ResultSet getTax() {
        ResultSet rst  = null;
        try{
            PreparedStatement pst = con.prepareStatement("SELECT t.`tax_id`,t.`tax`, t.from_date,t.to_date,c.full_name,t.`created_date`,m.`full_name`,t.`modified_date` FROM tax t INNER JOIN USER c ON t.created_by = c.`user_id` \n" +
"INNER JOIN USER m ON t.modified_by = m.`user_id` WHERE t.active = 1");
            return rst = pst.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return rst;
    }

    @Override
    public TaxBean getCurrentTax() {
        try{
            PreparedStatement pst = con.prepareStatement("SELECT * FROM tax WHERE active = 1 ORDER BY tax_id DESC LIMIT 1");
            ResultSet rst = pst.executeQuery();
            while(rst.next()){
                TaxBean tax = new TaxBean();
                tax.setTaxId(rst.getInt("tax_id"));
                tax.setTax(rst.getDouble("tax"));
                tax.setFromDate(rst.getDate("from_date"));
                tax.setToDate(rst.getDate("to_date"));
                return tax;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer updateTax(TaxBean tax) {
        try{
            PreparedStatement pst = con.prepareStatement("update tax set tax=?,from_date=?,to_date=? where tax_id=?");
            pst.setDouble(1, tax.getTax());
            pst.setDate(2,  new java.sql.Date(tax.getFromDate().getTime()));
            pst.setDate(3, new java.sql.Date(tax.getToDate().getTime()));
            pst.setInt(4, tax.getTaxId());
            return pst.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Integer deleteTax(TaxBean tax) {
        try{
            PreparedStatement pst = con.prepareStatement("update tax set active = 0 where tax_id=?");
            pst.setInt(1, tax.getTaxId());
            return pst.executeUpdate();
        }catch(Exception e){}
        return 0;
    }

    @Override
    public TaxBean getTaxById(Integer tax) {
        TaxBean taxBean = null;
       try{
           PreparedStatement pst = con.prepareStatement("Select * from tax where tax_id =?");
           pst.setInt(1,tax);
           
           ResultSet rst = pst.executeQuery();
           while(rst.next()){
               taxBean = new TaxBean();
               taxBean.setTax(rst.getDouble("tax"));
               taxBean.setFromDate(rst.getDate("from_date"));
               taxBean.setToDate(rst.getDate("to_date"));
               taxBean.setTaxId(rst.getInt("tax_id"));
               return taxBean;
           }
       }catch(Exception e){
       
       }
       return taxBean;
    }
    
}
