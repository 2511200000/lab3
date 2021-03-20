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
public class TblCustomerDAO implements Serializable {

    public boolean createCustomer(int id, String customerName, String address, String phoneNumber, String rentalDate, String returnDate) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        System.out.println("id " + id);
        try {
            con = DbHelp.makeConnection();
            if (con != null) {
                String sql = "Insert into tblCustomer(customerID, customerName, address, phoneNumber, rentalDate, returnDate)"
                        + "Values(?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                stm.setString(2, customerName);
                stm.setString(3, address);
                stm.setString(4, phoneNumber);
                stm.setString(5, rentalDate);
                stm.setString(6, returnDate);
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

    public int printIdCustomer() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbHelp.makeConnection();
            String sql = "Select customerID=MAX(customerID) from tblCustomer";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("customerID");
                return id;
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

        return 0;
    }
}
