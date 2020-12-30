/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palm.valley.beans;

/**
 *
 * @author Dell
 */
public class OrderDetailBean extends Bean {
    private Integer orderDetailId;
    private OrderBean order;
    private ProductBean product;
    private Integer Quantity;

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer Quantity) {
        this.Quantity = Quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    private Double price;
    
    
}
