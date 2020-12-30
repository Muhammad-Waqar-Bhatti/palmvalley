/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palm.valley.daoimpl;

import com.palm.valley.beans.OrderBean;
import com.palm.valley.beans.TaxBean;
import com.palm.valley.beans.UserBean;
import com.palm.valley.connection.DBConnection;
import com.palm.valley.dao.OrderDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Dell
 */
public class OrderDAOImpl implements OrderDAO {
    Connection con = DBConnection.getConnection();
    @Override
    public Integer addOrder(OrderBean order) {
        try{
            PreparedStatement pst = con.prepareStatement("insert into `order`(user_id,total_price,order_date,account_id,tax_id,status,created_by,created_date,modified_by,modified_date,order_no,active) values(?,?,?,?,?,?,?,?,?,?,?,1)" );
            
            pst.setInt(1, order.getUser().getUserId());
            pst.setDouble(2, order.getTotalPrice());
            pst.setDate(3, (Date) order.getOrderDate());
            pst.setInt(4, order.getAccount().getAccountId());
            pst.setInt(5, order.getTax().getTaxId());
            pst.setString(6, order.getStatus());
            pst.setInt(7, order.getCreatedBy());
            pst.setTimestamp(8, order.getCreatedDate());
            pst.setInt(9, order.getModifiedBy());
            pst.setTimestamp(10, order.getModifiedDate());
            pst.setString(11, order.getOrderNo());
            
            return pst.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Integer deleteOrder(OrderBean order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer updateOrder(OrderBean order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean CheckOrderNo(String orderNo) {
       try{
           PreparedStatement pst = con.prepareStatement("select * from order where order_no=?");
           pst.setString(1, orderNo);
           ResultSet rst = pst.executeQuery();
           if(rst.next()){
               return true;
           }
       }catch(Exception e){}
       return false;
    }

    @Override
    public OrderBean getlastOrder() {
        OrderBean orderBean = null;
        try{
            PreparedStatement pst = con.prepareStatement("SELECT * FROM `order` WHERE active = 1 ORDER BY order_id DESC LIMIT 1");
            ResultSet rst = pst.executeQuery();
            while(rst.next()){
                orderBean = new OrderBean();
                orderBean.setOrderId(rst.getInt("order_id"));
                orderBean.setOrderDate(rst.getDate("order_date"));
                orderBean.setOrderNo(rst.getString("order_no"));
                orderBean.setStatus(rst.getString("status"));
                orderBean.setTotalPrice(rst.getDouble("total_price"));
                return orderBean;
            }
        }catch(Exception e){}
        return orderBean;
    }

    @Override
    public ResultSet getOrders() {
        ResultSet rst = null;
        try{
            PreparedStatement pst = con.prepareStatement("SELECT o.order_id,o.order_no , u.`full_name`, o.total_price, o.order_date,c.`full_name`,o.created_date,m.full_name,o.modified_date,t.`tax` FROM `order` o\n" +
"INNER JOIN `user` u ON  u.`user_id` = o.`user_id` \n" +
"INNER JOIN `user` c ON c.`user_id`= o.`created_by`\n" +
"INNER JOIN `user` m ON m.`user_id` = o.`modified_by`\n" +
"INNER JOIN tax t ON t.`tax_id` = o.`tax_id` WHERE o.`active` =1;");
            
         rst = pst.executeQuery();
        
        }catch(Exception e){
            
        }
        return rst;
    }

    @Override
    public OrderBean getOrderById(Integer orderId) {
        OrderBean orderBean = null;
        try{
            PreparedStatement pst = con.prepareStatement("select * from order where order_id = ?");
            pst.setInt(1, orderId);
            ResultSet rst = pst.executeQuery();
            while(rst.next()){
                 orderBean = new OrderBean();
                 orderBean.setOrderId(rst.getInt("order_id"));
                 orderBean.setOrderNo(rst.getString("order_no"));
                 orderBean.setOrderDate(rst.getDate("order_date"));
                 orderBean.setTotalPrice(rst.getDouble("total_price"));
                 TaxBean taxBean = new TaxDAOImpl().getTaxById(rst.getInt("tax_id"));
                 orderBean.setTax(taxBean);
                 UserBean userBean = new UserDAOImpl().getUserById(rst.getInt("user_id"));
                 orderBean.setUser(userBean);
                 
                 
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return orderBean;
    }

    
}
