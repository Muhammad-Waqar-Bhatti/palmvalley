/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palm.valley.dao;

import com.palm.valley.beans.AccountBean;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface AccountDAO {
    public int addAccount (AccountBean account);
    public int deleteAccount(AccountBean account);
    public int updateAccount(AccountBean account);
    public ResultSet getAccounts();
    public AccountBean getAccountById(Integer id);
    public List<AccountBean> getAccount();
    public AccountBean getAccountByName(String name);
}
