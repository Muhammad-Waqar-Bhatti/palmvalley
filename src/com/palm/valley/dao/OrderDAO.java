/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palm.valley.dao;

import com.palm.valley.beans.OrderBean;
import java.sql.ResultSet;

/**
 *
 * @author Dell
 */
public interface OrderDAO {
    public Integer addOrder(OrderBean order);
    public ResultSet getOrders();
    public Integer deleteOrder(OrderBean order);
    public Integer updateOrder(OrderBean order);
    public Boolean CheckOrderNo(String orderNo);
    public OrderBean getlastOrder();
    public OrderBean getOrderById(Integer orderId);
}
