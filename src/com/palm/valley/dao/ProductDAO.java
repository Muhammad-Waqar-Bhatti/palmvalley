/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palm.valley.dao;

import com.palm.valley.beans.CategoryBean;
import com.palm.valley.beans.ProductBean;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface ProductDAO {
    public int addProduct (ProductBean product);
    public int deleteProduct(ProductBean product);
    public int updateProduct(ProductBean product);
    public ResultSet getProducts();
    public ProductBean getProductById(Integer id);
    public List<CategoryBean> getCategories();
    public Boolean barcodeExists(String barcode);
    public List<ProductBean> getProductList();
    public ProductBean getProductByName(String name);
    public Integer updateProductQuantity(ProductBean product);
    public ProductBean getProductByBarcode(String barcode);
}
