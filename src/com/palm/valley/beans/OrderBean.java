/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palm.valley.beans;


import java.util.Date;

/**
 *
 * @author Dell
 */
public class OrderBean extends Bean{
    private Integer orderId;
    private Double totalPrice;
    private Date orderDate;
    private AccountBean account;
    private String status;
    private TaxBean tax;
    private UserBean user;
    private String orderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public AccountBean getAccount() {
        return account;
    }

    public void setAccount(AccountBean account) {
        this.account = account;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TaxBean getTax() {
        return tax;
    }

    public void setTax(TaxBean tax) {
        this.tax = tax;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
}
