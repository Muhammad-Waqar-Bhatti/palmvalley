/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palm.valley.dao;

import com.palm.valley.beans.StockBean;
import java.sql.ResultSet;

/**
 *
 * @author Dell
 */
public interface StockDAO {
    public int addStock (StockBean stock);
    public int deleteStock(StockBean stock);
    public int updateStock(StockBean stock);
    public ResultSet getStocks();
    public StockBean getStockById(Integer id);
}
