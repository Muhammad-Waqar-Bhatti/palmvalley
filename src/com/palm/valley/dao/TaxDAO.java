/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palm.valley.dao;

import com.palm.valley.beans.TaxBean;
import java.sql.ResultSet;

/**
 *
 * @author Dell
 */
public interface TaxDAO {
    public Integer addTax(TaxBean tax);
    public ResultSet getTax();
    public TaxBean getCurrentTax();
    public Integer updateTax(TaxBean tax);
    public Integer deleteTax(TaxBean tax);
    public TaxBean getTaxById(Integer tax);
    
}
