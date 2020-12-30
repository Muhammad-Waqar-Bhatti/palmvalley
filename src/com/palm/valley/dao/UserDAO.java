/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palm.valley.dao;

import com.palm.valley.beans.UserBean;
import java.sql.ResultSet;
import java.util.List;
/**
 *
 * @author Dell
 */
public interface UserDAO {
    public UserBean login(UserBean user);
    public int addUser (UserBean user);
    public int deleteUser(UserBean user);
    public int updateUser(UserBean user);
    public ResultSet getUsers();
    public UserBean getUserById(Integer id);
    public ResultSet getCustomerList();
    public UserBean getUserByName(String name);
}
