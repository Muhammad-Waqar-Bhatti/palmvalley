/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palm.valley.beans;

import java.sql.Timestamp;

/**
 *
 * @author Dell
 */
public class StockBean extends Bean {
    private Integer stockId;
    private String name;
    private Integer quantity;
    private Double whoelSalePrice;
    private Double retailPrice;
    private Timestamp date;
    private AccountBean account;
    private ProductBean product;

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
        this.product = product;
    }
    

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getWhoelSalePrice() {
        return whoelSalePrice;
    }

    public void setWhoelSalePrice(Double whoelSalePrice) {
        this.whoelSalePrice = whoelSalePrice;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public AccountBean getAccount() {
        return account;
    }

    public void setAccount(AccountBean account) {
        this.account = account;
    }
    
    
}
