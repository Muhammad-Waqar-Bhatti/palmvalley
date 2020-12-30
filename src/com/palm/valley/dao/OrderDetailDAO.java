/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palm.valley.dao;

import com.palm.valley.beans.OrderDetailBean;

/**
 *
 * @author Dell
 */
public interface OrderDetailDAO {
    public Integer addOrderDetail(OrderDetailBean orderDetail);
    public OrderDetailBean getOrderDetailByOrderId(Integer orderId);
}
