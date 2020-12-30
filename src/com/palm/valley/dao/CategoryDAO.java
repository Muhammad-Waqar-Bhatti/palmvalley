/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palm.valley.dao;

import com.palm.valley.beans.CategoryBean;
import com.palm.valley.beans.UserBean;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface CategoryDAO {
    
    public int addCategory (CategoryBean category);
    public int deleteCategory(CategoryBean category);
    public int updateCategory(CategoryBean category);
    public ResultSet getCategories();
    public CategoryBean getCategoryById(Integer id);
     public CategoryBean getCategoryByCategoryName(String name);
     public List<CategoryBean> getCategoriesList();
}
