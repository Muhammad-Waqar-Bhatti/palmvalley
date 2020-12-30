/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palm.valley.daoimpl;

import com.palm.valley.beans.OrderDetailBean;
import com.palm.valley.connection.DBConnection;
import com.palm.valley.dao.OrderDetailDAO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Dell
 */
public class OrderDetailDAOImpl implements OrderDetailDAO{
    Connection con = DBConnection.getConnection();
    @Override
    public Integer addOrderDetail(OrderDetailBean orderDetail) {
        try{
            PreparedStatement pst = con.prepareStatement("insert into order_detail(order_id,product_id,quantity,price,active,created_by,created_date,modified_by,modified_date) values(?,?,?,?,?,?,?,?,?)");
            pst.setInt(1, orderDetail.getOrder().getOrderId());
            pst.setInt(2, orderDetail.getProduct().getProductId());
            pst.setInt(3, orderDetail.getQuantity());
            pst.setDouble(4, orderDetail.getPrice());
            pst.setInt(5, 1);
            pst.setInt(6, orderDetail.getCreatedBy());
            pst.setTimestamp(7, orderDetail.getCreatedDate());
            pst.setInt(8, orderDetail.getModifiedBy());
            pst.setTimestamp(9, orderDetail.getModifiedDate());
            
            return pst.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    
}
