/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palm.valley.daoimpl;

import com.palm.valley.beans.AccountBean;
import com.palm.valley.connection.DBConnection;
import com.palm.valley.dao.AccountDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class AccountDAOImpl implements AccountDAO {
    Connection con = DBConnection.getConnection();
    @Override
    public int addAccount(AccountBean account) {
       int suc = 0;
      
        try{
            PreparedStatement pst = con.prepareStatement("insert into account(name,contact,description,created_by,created_date,active) values(?,?,?,?,?,1)");
            pst.setString(1, account.getName());
            pst.setString(2, account.getContact());
            pst.setString(3, account.getDescription());
            pst.setInt(4, account.getCreatedBy());
            pst.setTimestamp(5, account.getCreatedDate());
            
            suc = pst.executeUpdate();
            return suc;
        }catch(Exception e){
           e.printStackTrace();
        }
        return suc;
    }

    @Override
    public int deleteAccount(AccountBean account) {
        int suc = 0;
        try{
            PreparedStatement pst = con.prepareStatement("update account set active =0, modified_date=?, modified_by=? where account_id=?");
            pst.setTimestamp(1, account.getModifiedDate());
            pst.setInt(2, account.getModifiedBy());
            pst.setInt(3, account.getAccountId());
            suc = pst.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return suc;
    }

    @Override
    public int updateAccount(AccountBean account) {
        int suc = 0;
        
        try{
        PreparedStatement pst = con.prepareStatement("update account set name =?, contact=?, description=?, modified_date=?, modified_by = ? where account_id = ?");
        
        pst.setString(1,account.getName());
        pst.setString(2, account.getContact());
        pst.setString(3,account.getDescription());
        pst.setTimestamp(4, account.getModifiedDate());
        pst.setInt(5, account.getModifiedBy());
        pst.setInt(6, account.getAccountId());
         return suc = pst.executeUpdate();
        }catch(Exception e){
        e.printStackTrace();
        }
        return suc;
    }

    @Override
    public ResultSet getAccounts() {
        ResultSet rst = null;
         try{
            PreparedStatement pst = con.prepareStatement("Select account_id,name,contact,description from account where active=1");
            rst = pst.executeQuery();
            return rst;
        }catch(Exception e){
        
        }
         return rst;
    }

    @Override
    public AccountBean getAccountById(Integer id) {
        AccountBean account = null;
        try{
             PreparedStatement pst = con.prepareStatement("Select account_id,name,contact,description from account where account_id=?");
             pst.setInt(1, id);
            ResultSet rst = pst.executeQuery();
            while(rst.next()){
                account = new AccountBean();
                account.setAccountId(rst.getInt("account_id"));
                account.setName(rst.getString("name"));
                account.setContact(rst.getString("contact"));
                account.setDescription(rst.getString("description"));
                return account;
            }
        }catch(Exception e){
        
        }
        return account;
    }

    @Override
    public List<AccountBean> getAccount() {
       List<AccountBean> accountList = new ArrayList();
       try{
           PreparedStatement pst =con.prepareStatement("Select * from account where active =1");
           ResultSet rst = pst.executeQuery();
           while(rst.next()){
              
              AccountBean accountBean = new AccountBean();
              accountBean.setAccountId(rst.getInt("account_id"));
              accountBean.setName(rst.getString("name"));
              accountBean.setDescription(rst.getString("description"));
              accountList.add(accountBean);
           }
       }catch(Exception e){
           e.printStackTrace();
       }
       return accountList;
    }

    @Override
    public AccountBean getAccountByName(String name) {
        AccountBean accountBean = null;
        try{
            PreparedStatement pst = con.prepareStatement("select * from account where name =?");
            pst.setString(1, name);
            ResultSet rst = pst.executeQuery();
            while(rst.next()){
                accountBean = new AccountBean();
                accountBean.setAccountId(rst.getInt("account_id"));
                accountBean.setContact(rst.getString("contact"));
                accountBean.setDescription(rst.getString("description"));

            }
        }catch(Exception e){e.printStackTrace();}
        return accountBean;
    }

    
    
}
