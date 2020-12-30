/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palm.valley.daoimpl;

import com.palm.valley.beans.CategoryBean;
import com.palm.valley.beans.UserBean;
import com.palm.valley.connection.DBConnection;
import com.palm.valley.dao.CategoryDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class CategoryDAOImpl implements CategoryDAO{
    Connection con = DBConnection.getConnection();
    @Override
    public int addCategory(CategoryBean category) {
          int suc = 0;
      
        try{
            PreparedStatement pst = con.prepareStatement("insert into category(name,description,created_date,created_by,active) values(?,?,?,?,1)");
            pst.setString(1, category.getName());
            pst.setString(2, category.getDescription());
            pst.setTimestamp(3, category.getCreatedDate());
            pst.setInt(4,category.getCreatedBy());
            
            suc = pst.executeUpdate();
            return suc;
        }catch(Exception e){
           e.printStackTrace();
        }
        return suc;
    }

    @Override
    public int deleteCategory(CategoryBean category) {
        int suc = 0;
        try{
            PreparedStatement pst = con.prepareStatement("update category set active =0, modified_date=?,modified_by=? where category_id=?");
            pst.setTimestamp(1, category.getModifiedDate());
            pst.setInt(2, category.getModifiedBy());
            pst.setInt(3, category.getCategoryId());
            suc = pst.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return suc;
    }

    @Override
    public int updateCategory(CategoryBean category) {
         int suc = 0;
        
        try{
        PreparedStatement pst = con.prepareStatement("update category set name =?, description=?,modified_date=?,modified_by=? where category_id = ?");
        pst.setString(1,category.getName());
        pst.setString(2,category.getDescription());
        pst.setTimestamp(3, category.getModifiedDate());
        pst.setInt(4, category.getModifiedBy());
        pst.setInt(5, category.getCategoryId());
         return suc = pst.executeUpdate();
        }catch(Exception e){
        e.printStackTrace();
        }
        return suc;
    }

    @Override
    public ResultSet getCategories() {
        ResultSet rst = null;
         try{
            PreparedStatement pst = con.prepareStatement("Select category_id,name,description from category where active=1");
            rst = pst.executeQuery();
            return rst;
        }catch(Exception e){
        
        }
         return rst;
       
    }

    @Override
    public CategoryBean getCategoryById(Integer id) {
        CategoryBean category = null;
        try{
             PreparedStatement pst = con.prepareStatement("Select category_id,name,description from category where category_id=?");
             pst.setInt(1, id);
            ResultSet rst = pst.executeQuery();
            while(rst.next()){
                category = new CategoryBean();
                category.setCategoryId(rst.getInt("category_id"));
                category.setName(rst.getString("name"));
                category.setDescription(rst.getString("description"));
                return category;
            }
        }catch(Exception e){
        
        }
        return category;
    }

    @Override
    public CategoryBean getCategoryByCategoryName(String name) {
        CategoryBean category = null;
        try{
            PreparedStatement pst = con.prepareStatement("Select * from category where name = ?");
            pst.setString(1, name);
            ResultSet rst = pst.executeQuery();
            while(rst.next()){
                category = new CategoryBean();
                category.setCategoryId(rst.getInt("category_id"));
                category.setName(rst.getString("name"));
                category.setDescription("description");
                return category;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return category;
    }
    
    public List<CategoryBean> getCategoriesList(){
        List<CategoryBean> categoryList = new ArrayList<>();
        try{
            PreparedStatement pst = con.prepareStatement("select * from category where active =1");
            ResultSet rst = pst.executeQuery();
            while(rst.next()){
                CategoryBean categoryBean = new CategoryBean();
                categoryBean.setCategoryId(rst.getInt("category_id"));
                categoryBean.setName(rst.getString("name"));
                categoryBean.setDescription(rst.getString("description"));
                categoryList.add(categoryBean);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return categoryList;
    }
    
}
