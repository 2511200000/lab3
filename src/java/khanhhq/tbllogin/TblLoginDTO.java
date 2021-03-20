/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhhq.tbllogin;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class TblLoginDTO implements Serializable {

    private String userID;
    private String fullname;
    private String password;
    private String roleID;
    boolean status;
    String code;

    public TblLoginDTO(String userID, String fullname, String password, String roleID, boolean status, String code) {
        this.userID = userID;
        this.fullname = fullname;
        this.password = password;
        this.roleID = roleID;
        this.status = status;
        this.code = code;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
   

     
    

}
