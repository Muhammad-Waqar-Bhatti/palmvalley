/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palm.valley.daoimpl;

import com.palm.valley.beans.UserBean;
import com.palm.valley.connection.DBConnection;
import com.palm.valley.dao.UserDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class UserDAOImpl implements UserDAO {
    Connection con = DBConnection.getConnection();
    @Override
    public UserBean login(UserBean user) {
        UserBean users = null;
        try{
            PreparedStatement pst = con.prepareStatement("SELECT user_id,user_name, PASSWORD , user_type, full_name FROM USER WHERE user_name =? or PASSWORD =?");
            pst.setString(1, user.getUserName());
            pst.setString(2, user.getPassword());
            
            ResultSet rst =pst.executeQuery(); 
            while(rst.next()){
                users = new UserBean();
                users.setUserId(rst.getInt("user_id"));
                users.setPassword(rst.getString("password"));
                users.setUserName(rst.getString("user_name"));
                users.setUserType(rst.getString("user_type"));
                users.setFullName(rst.getString("full_name"));
                
            }
        }catch(Exception e){}
        return users;
    }

    @Override
    public int addUser(UserBean user) {
       try{
           PreparedStatement pst = con.prepareStatement("insert into user(user_name,full_name,user_type,password,nic,contact,date_of_birth,created_by,created_date,active) values(?,?,?,?,?,?,?,?,?,1) ");
           pst.setString(1, user.getUserName());
           pst.setString(2, user.getFullName());
           pst.setString(3, user.getUserType());
           pst.setString(4, user.getPassword());
           pst.setString(5, user.getNic());
           pst.setString(6, user.getContact());
           pst.setDate(7, new java.sql.Date(user.getDateOfBirth().getTime()));
           pst.setInt(8, user.getCreatedBy());
           pst.setTimestamp(9, user.getCreatedDate());
           return pst.executeUpdate();
           
       }catch(Exception e){
       e.printStackTrace();
       }
       return 0;
    }

    @Override
    public int deleteUser(UserBean user) {
       try{
           PreparedStatement pst = con.prepareStatement("update user set active = 0, modified_date =?, modified_by=? where user_id =? ");
           pst.setTimestamp(1, user.getModifiedDate());
           pst.setInt(2, user.getModifiedBy());
           pst.setInt(3, user.getUserId());
           return pst.executeUpdate();
           
       }catch(Exception e){
            e.printStackTrace();
       }
       return 0;
    }

    @Override
    public int updateUser(UserBean user) {
        try{
           PreparedStatement pst = con.prepareStatement("Update user set user_name=?,full_name = ?,user_type = ? ,password=? , nic=?,contact =?, date_of_birth=?, modified_date=?, modified_by=?  where user_id =?");
           pst.setString(1, user.getUserName());
           pst.setString(2, user.getFullName());
           pst.setString(3, user.getUserType());
           pst.setString(4, user.getPassword());
           pst.setString(5, user.getNic());
           pst.setString(6, user.getContact());
           pst.setDate(7,new java.sql.Date(user.getDateOfBirth().getTime()) );
           pst.setTimestamp(8, user.getModifiedDate());
           pst.setInt(9, user.getModifiedBy());
           pst.setInt(10,user.getUserId());
           
           return pst.executeUpdate();
           
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ResultSet getUsers() {
        ResultSet rst = null;
        try{
        PreparedStatement pst = con.prepareStatement("Select user_id,user_name, full_name, password,date_of_birth, nic, contact, user_type from user where active = 1");
        rst = pst.executeQuery();
        }catch(Exception e){
        
        }
        return rst;
    }

    @Override
    public UserBean getUserById(Integer id) {
        UserBean user = null;
        try{
            PreparedStatement pst = con.prepareStatement("SELECT * FROM USER WHERE user_id =?");
            pst.setInt(1,id);
            
            
            ResultSet rst =pst.executeQuery(); 
            while(rst.next()){
                user = new UserBean();
                user.setUserId(rst.getInt("user_id"));
                user.setPassword(rst.getString("password"));
                user.setUserName(rst.getString("user_name"));
                user.setUserType(rst.getString("user_type"));
                user.setFullName(rst.getString("full_name"));
                user.setContact(rst.getString("contact"));
                user.setDateOfBirth(rst.getDate("date_of_birth"));
                user.setNic(rst.getString("nic"));
            }
        }catch(Exception e){
        
        }        
        return user;
    }

    @Override
    public ResultSet getCustomerList() {
//       List<UserBean> customerList = new ArrayList<>();
//       UserBean user = null;
       ResultSet rst = null;
       try{
           PreparedStatement pst = con.prepareStatement("SELECT user_id,full_name,contact,nic,date_of_birth FROM USER WHERE user_type = \"CUSTOMER\" AND active = 1");
           rst = pst.executeQuery();
//           while(rst.next()){
//               user = new UserBean();
//               user.setFullName(rst.getString("full_name"));
//               user.setUserId(rst.getInt("user_id"));
//               user.setContact(rst.getString("contact"));
//               user.setNic(rst.getString("nic"));
//               user.setDateOfBirth(rst.getDate("date_of_birth"));
//               customerList.add(user);
//           }
       }catch(Exception e){}
       return rst;
    }

    @Override
    public UserBean getUserByName(String name) {
        UserBean user = null;
        try{
            PreparedStatement pst = con.prepareStatement("SELECT * FROM USER WHERE full_name =? and active = 1");
            pst.setString(1,name);
            
            
            ResultSet rst =pst.executeQuery(); 
            while(rst.next()){
                user = new UserBean();
                user.setUserId(rst.getInt("user_id"));
                user.setPassword(rst.getString("password"));
                user.setUserName(rst.getString("user_name"));
                user.setUserType(rst.getString("user_type"));
                user.setFullName(rst.getString("full_name"));
                user.setContact(rst.getString("contact"));
                user.setDateOfBirth(rst.getDate("date_of_birth"));
                user.setNic(rst.getString("nic"));
                return user;
            }
        }catch(Exception e){
            e.printStackTrace();
        }        
        return user;
    }
    
   
}
