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
public class TaxBean extends Bean{
    private Integer taxId ;
    private Double tax;
    private Date fromDate;
    private Date toDate;

    public Integer getTaxId() {
        return taxId;
    }

    public void setTaxId(Integer taxId) {
        this.taxId = taxId;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
