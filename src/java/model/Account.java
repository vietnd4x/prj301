/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author hoang
 */
public class Account {

    private int accountID;
    private String accountName;
    private String accountEmail;
    private String accountPass;
    private String accountPhone;
    private int isAdmin;
    private int status;

    public Account() {
    }

    public Account(int accountID, String accountName, String accountEmail, String accountPass, String accountPhone, int isAdmin, int status) {
        this.accountID = accountID;
        this.accountName = accountName;
        this.accountEmail = accountEmail;
        this.accountPass = accountPass;
        this.accountPhone = accountPhone;
        this.isAdmin = isAdmin;
        this.status = status;
    }

    
    public Account(int accountID, String accountName, String accountEmail, String accountPass, String accountPhone, int isAdmin) {
        this.accountID = accountID;
        this.accountName = accountName;
        this.accountEmail = accountEmail;
        this.accountPass = accountPass;
        this.accountPhone = accountPhone;
        this.isAdmin = isAdmin;
    }
    // sua 26-10
    public Account(String accountName, String accountEmail, String accountPass, String accountPhone) {
        this.accountName = accountName;
        this.accountEmail = accountEmail;
        this.accountPass = accountPass;
        this.accountPhone = accountPhone;
    }
    public Account(int accountID, String accountName, String accountEmail, String accountPass, String accountPhone) {
        this.accountID = accountID;
        this.accountName = accountName;
        this.accountEmail = accountEmail;
        this.accountPass = accountPass;
        this.accountPhone = accountPhone;
    }
    //
    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public String getAccountPass() {
        return accountPass;
    }

    public void setAccountPass(String accountPass) {
        this.accountPass = accountPass;
    }

    public String getAccountPhone() {
        return accountPhone;
    }

    public void setAccountPhone(String accountPhone) {
        this.accountPhone = accountPhone;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getStatus() {
        return status;
    }
    public String getStatus2() {
        if (this.status == 1) {
            return "Activated";
        } else {
            return "Blocking";
        }
    }
    public void setStatus(int status) {
        this.status = status;
    }

    

}
