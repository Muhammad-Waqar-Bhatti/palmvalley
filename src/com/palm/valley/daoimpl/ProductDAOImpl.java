/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palm.valley.daoimpl;

import com.palm.valley.beans.CategoryBean;
import com.palm.valley.beans.ProductBean;
import com.palm.valley.connection.DBConnection;
import com.palm.valley.dao.CategoryDAO;
import com.palm.valley.dao.ProductDAO;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class ProductDAOImpl implements ProductDAO {
    Connection con = DBConnection.getConnection();
    @Override
    public int addProduct(ProductBean product) {
         int suc = 0;
      
        try{
            PreparedStatement pst = con.prepareStatement("insert into product(barcode,price,quantity,category_id,name,created_date,created_by,size,active) values(?,?,?,?,?,?,?,?,1)");
            pst.setString(1, product.getBarcode());
            pst.setDouble(2, product.getPrice());
            pst.setInt(3, product.getQuantity());
            pst.setInt(4, product.getCategory().getCategoryId());
            pst.setString(5, product.getName());
            pst.setTimestamp(6, product.getCreatedDate());
            pst.setInt(7, product.getCreatedBy());
            pst.setString(8, product.getSize());
            
            suc = pst.executeUpdate();
            return suc;
        }catch(Exception e){
           e.printStackTrace();
        }
        return suc;
    }

    @Override
    public int deleteProduct(ProductBean product) {
       int suc = 0;
        try{
            PreparedStatement pst = con.prepareStatement("update product set active =0, modified_date=?,modified_by=? where product_id=?");
            pst.setTimestamp(1, product.getModifiedDate());
            pst.setInt(2, product.getModifiedBy());
            pst.setInt(3, product.getProductId());
            suc = pst.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return suc;
    }

    @Override
    public int updateProduct(ProductBean product) {
        int suc = 0;
      
        try{
            PreparedStatement pst = con.prepareStatement("update product set  price=?, quantity=?,category_id=?,modified_date=?,modified_by=?,size=? where product_id = ?");
            
            pst.setDouble(1, product.getPrice());
            pst.setInt(2, product.getQuantity());
            pst.setInt(3, product.getCategory().getCategoryId());
            pst.setTimestamp(4, product.getModifiedDate());
            pst.setInt(5, product.getModifiedBy());
            pst.setString(6,product.getSize());
            pst.setInt(7,product.getProductId());
            
            suc = pst.executeUpdate();
            return suc;
        }catch(Exception e){
           e.printStackTrace();
        }
        return suc;
    }

    @Override
    public ResultSet getProducts() {
        ResultSet rst = null;
         try{
            PreparedStatement pst = con.prepareStatement("SELECT p.product_id,p.name,p.barcode,p.price,p.quantity,p.size, c.name  FROM product p , category c where  p.category_id = c.category_id and p.active = 1");
            rst = pst.executeQuery();
            return rst;
        }catch(Exception e){
            e.printStackTrace();
        }
         return rst;
       
    }

    @Override
    public ProductBean getProductById(Integer id) {
        ProductBean product = null;
        try{
             PreparedStatement pst = con.prepareStatement("Select name,product_id,barcode,size,price,quantity,category_id from product where product_id=?");
             pst.setInt(1, id);
            ResultSet rst = pst.executeQuery();
            while(rst.next()){
                product = new ProductBean();
                product.setName(rst.getString("name"));
                product.setProductId(rst.getInt("product_id"));
                product.setBarcode(rst.getString("barcode"));
                product.setSize(rst.getString("size"));
                product.setPrice(rst.getDouble("price"));
                product.setQuantity(rst.getInt("quantity"));
                
                CategoryDAO categoryDAO = new CategoryDAOImpl();
                CategoryBean categoryBean = categoryDAO.getCategoryById(rst.getInt("category_id"));
                product.setCategory(categoryBean);
                return product;
            }
        }catch(Exception e){
        
        }
        return product;
    }

    @Override
    public List<CategoryBean> getCategories() {
        List<CategoryBean> categoryList = null;
        try{
        CategoryDAO category = new CategoryDAOImpl();
        ResultSet rst = category.getCategories();
        categoryList = new ArrayList<>();
        while(rst.next()){
            CategoryBean categories = new CategoryBean();
            categories.setName(rst.getString("name"));
            categories.setDescription(rst.getString("description"));
            categories.setCategoryId(rst.getInt("category_id"));
            categoryList.add(categories);
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        return categoryList;
    }

    @Override
    public Boolean barcodeExists(String barcode) {
       try{
           PreparedStatement pst =con.prepareStatement("select * from product where barcode =?");
           pst.setString(1, barcode);
           ResultSet rst = pst.executeQuery();
           
           if(rst.next()){
               return true;
           }
       }catch(Exception e){
           e.printStackTrace();
       }
       return false;
    }

    @Override
    public List<ProductBean> getProductList() {
        List<ProductBean> productList = new ArrayList<>();
        try{
            PreparedStatement pst = con.prepareStatement("select * from product where active =1");
            ResultSet rst = pst.executeQuery();
            while(rst.next()){
                ProductBean productBean = new ProductBean();
                productBean.setProductId(rst.getInt("product_id"));
                productBean.setBarcode(rst.getString("barcode"));
                productBean.setName(rst.getString("name"));
                CategoryBean categoryBean = new CategoryBean();
                categoryBean.setCategoryId(rst.getInt("category_id"));
                productBean.setCategory(categoryBean);
                productList.add(productBean);
            
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public ProductBean getProductByName(String name) {
        ProductBean productBean = null;
        try{
            PreparedStatement pst = con.prepareStatement("select * from product where name = ?");
            pst.setString(1,name);
            ResultSet rst = pst.executeQuery();
            while(rst.next()){
               productBean = new ProductBean();
               productBean.setProductId(rst.getInt("product_id"));
               productBean.setPrice(rst.getDouble("price"));
               productBean.setBarcode(rst.getString("barcode"));
               CategoryBean categoryBean = new CategoryBean();
               categoryBean.setCategoryId(rst.getInt("category_id"));
               productBean.setCategory(categoryBean);
               productBean.setQuantity(rst.getInt("quantity"));
               productBean.setName(rst.getString("name"));
               productBean.setSize(rst.getString("size"));
               
            }
        }catch(Exception e){
        
        }
        return productBean;
    }

    @Override
    public Integer updateProductQuantity(ProductBean product) {
       try{
           PreparedStatement pst = con.prepareStatement("update product set quantity=? where product_id =? ");
           pst.setInt(1, product.getQuantity());
           pst.setInt(2, product.getProductId());
           return pst.executeUpdate();
       }catch(Exception e){
       
       }
       return 0;
    }

    @Override
    public ProductBean getProductByBarcode(String barcode) {
        ProductBean productBean = null;
        try{
            PreparedStatement pst = con.prepareStatement("select * from product where barcode =?");
            pst.setString(1, barcode);
            ResultSet rst = pst.executeQuery();
            while(rst.next()){
                productBean = new ProductBean();
                productBean.setProductId(rst.getInt("product_id"));
                productBean.setName(rst.getString("name"));
                productBean.setPrice(rst.getDouble("price"));
                productBean.setQuantity(rst.getInt("quantity"));
                return productBean;
            }
        }catch(Exception e){
        
        }
        return null;
    }
    
   
    
}
