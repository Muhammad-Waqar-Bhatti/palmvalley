/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palm.valley.daoimpl;

import com.palm.valley.beans.AccountBean;
import com.palm.valley.beans.StockBean;
import com.palm.valley.connection.DBConnection;
import com.palm.valley.dao.AccountDAO;
import com.palm.valley.dao.StockDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Dell
 */
public class StockDAOImpl implements StockDAO {
    Connection con = DBConnection.getConnection();
    @Override
    public int addStock(StockBean stock) {
       try{
           PreparedStatement pst = con.prepareStatement("insert into stock(name,quantity,whole_sale_price,retail_price,date,created_by,created_date,modified_by, modified_date,account_id ,product_id,active) values(?,?,?,?,?,?,?,?,?,?,?,1)");
           pst.setString(1, stock.getName());
           pst.setInt(2, stock.getQuantity());
           pst.setDouble(3, stock.getWhoelSalePrice());
           pst.setDouble(4, stock.getRetailPrice());
           pst.setTimestamp(5, stock.getDate());
           pst.setInt(6, stock.getCreatedBy());
           pst.setTimestamp(7,stock.getCreatedDate());
           pst.setInt(8, stock.getModifiedBy());
           pst.setTimestamp(9, stock.getModifiedDate());
           pst.setInt(10,stock.getAccount().getAccountId());
           pst.setInt(11, stock.getProduct().getProductId());
           return pst.executeUpdate();
          
       }catch(Exception e){
           e.printStackTrace();
       }
       return 0;
    }

    @Override
    public int deleteStock(StockBean stock) {
        try{
            PreparedStatement pst = con.prepareStatement("update stock set active =0, modified_date=?,modified_by=? where stock_id=?");
            pst.setTimestamp(1, stock.getModifiedDate());
            pst.setInt(2, stock.getModifiedBy());
            pst.setInt(3, stock.getStockId());
            return pst.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateStock(StockBean stock) {
        try{
            PreparedStatement pst = con.prepareStatement("update stock set name=?,quantity=?,whole_sale_price=?,retail_price=?,date=?,modified_by=?, modified_date=?,account_id=?,product_id=? where stock_id =?");
            pst.setString(1, stock.getName());
            pst.setInt(2, stock.getQuantity());
            pst.setDouble(3, stock.getWhoelSalePrice());
            pst.setDouble(4,stock.getRetailPrice());
            pst.setTimestamp(5, stock.getDate());
            pst.setInt(6, stock.getModifiedBy());
            pst.setTimestamp(7, stock.getModifiedDate());
            pst.setInt(8,stock.getAccount().getAccountId());
            pst.setInt(9, stock.getProduct().getProductId());
            pst.setInt(10, stock.getStockId());
            return pst.executeUpdate();
        }catch(Exception e){e.printStackTrace();}
        return 0;
    }

    @Override
    public ResultSet getStocks() {
         ResultSet rst = null;
         try{
            PreparedStatement pst = con.prepareStatement("SELECT  s.`stock_id`,s.name,s.`quantity`,p.name,s.`whole_sale_price`,s.`retail_price`, cu.`full_name`,s.`created_date`,u.full_name ,s.`modified_date`, a.`name`  FROM stock s \n" +
"INNER JOIN account a ON s.`account_id` = a.`account_id` INNER JOIN USER u ON  u.`user_id`= s.`modified_by` INNER JOIN USER cu ON cu.`user_id` = s.`created_by` inner join product p on p.product_id = s.product_id WHERE s.active = 1;");
            rst = pst.executeQuery();
            return rst;
        }catch(Exception e){
        
        }
         return rst;
    }

    @Override
    public StockBean getStockById(Integer id) {
        StockBean stock = null;
        try{
             PreparedStatement pst = con.prepareStatement("Select * from stock where stock_id=?");
             pst.setInt(1, id);
            ResultSet rst = pst.executeQuery();
            while(rst.next()){
                stock = new StockBean();
                stock.setStockId(rst.getInt("stock_id"));
                stock.setName(rst.getString("name"));
                stock.setDate(rst.getTimestamp("date"));
                stock.setWhoelSalePrice(rst.getDouble("whole_sale_price"));
                stock.setRetailPrice(rst.getDouble("retail_price"));
                stock.setQuantity(rst.getInt("quantity"));
                AccountBean account = new AccountBean();
                AccountDAO accountDAO = new AccountDAOImpl();
                account = accountDAO.getAccountById(rst.getInt("account_id"));
                stock.setAccount(account);
                
                
                return stock;
            }
        }catch(Exception e){
        
        }
        return stock;
    }
    
}
