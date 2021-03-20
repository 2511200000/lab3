/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhhq.tbllogin;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import khanhhq.utilities.DbHelp;

/**
 *
 * @author Administrator
 */
public class TblLoginDAO implements Serializable {

    public String checklogin(String userID, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String name = "";
        try {
            con = DbHelp.makeConnection();
            if (con != null) {
                String sql = "Select fullname "
                        + "From tblLogin "
                        + "where userID = ? And password = ? And status = '123'";
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                     name = rs.getString(1);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return name;
    }

    public String getRoleID(String id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String name = "";
        try {
            con = DbHelp.makeConnection();
            String sql = "select roleID "
                    + "from tblLogin "
                    + "where userID = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                name = rs.getString("roleID");
                return name;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public boolean createAccount(String email, String name, String password, String role, String status, String vertify) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DbHelp.makeConnection();
            if (con != null) {
                String sql = "Insert into tblLogin(userID,fullname,password,roleID,status,codeVertify)"
                        + "Values(?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, name);
                stm.setString(3, password);
                stm.setString(4, role);
                stm.setString(5, status);
                stm.setString(6, vertify);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateAccount(boolean status, String vertify) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DbHelp.makeConnection();
            if (con != null) {
                String sql = "Update tblLogin "
                        + "set status = ? "
                        + "where codeVertify = ?";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, status);
                stm.setString(2, vertify);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public String getCodeVertify(String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String name = "";
        try {
            con = DbHelp.makeConnection();
            String sql = "select codeVertify "
                    + "from tblLogin "
                    + "where userID = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                name = rs.getString("codeVertify");
                return name;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public TblLoginDTO findEmailByCode(String id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbHelp.makeConnection();
            String sql = "Select userID,fullname,password,roleID,status,codeVertify "
                    + "from tblLogin "
                    + "where codeVertify = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                String userID = rs.getString("userID");
                String fullname = rs.getString("fullname");
                String password = rs.getString("password");
                String roleID = rs.getString("roleID");
                boolean status = rs.getBoolean("status");
                String code = rs.getString("codeVertify");
                TblLoginDTO dto = new TblLoginDTO(userID, fullname, password, roleID, status, code);
                return dto;
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public String getStatus(String id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String name = "";
        try {
            con = DbHelp.makeConnection();
            String sql = "select status "
                    + "from tblLogin "
                    + "where userID = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                name = rs.getString("status");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return name;
    }
}
