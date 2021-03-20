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
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import khanhhq.utilities.DbHelp;

/**
 *
 * @author Administrator
 */
public class TblRentalDAO implements Serializable {

    public boolean createOrder(int rentalID, int customerID, String rentalDate, String returnDate, float totalAll,String voucher) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
         System.out.println("rentalID " + rentalID);
        try {
            con = DbHelp.makeConnection();
            if (con != null) {
                String sql = "Insert into tblRental(rentalID,customerID,rentalDate,returnDate,totalAll,voucherID)"
                        + "Values(?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, rentalID);
                stm.setInt(2, customerID);
                stm.setString(3, rentalDate);
                stm.setString(4, returnDate);
                stm.setFloat(5, totalAll);
                stm.setString(6, voucher);
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

    private List<TblRentalDTO> listRental;

    public List<TblRentalDTO> getListRental() {
        return listRental;
    }

    public void printOrder(int billMAX, int customerBillId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DbHelp.makeConnection();
            String sql = "Select rentalID,customerID,rentalDate,returnDate,totalAll,voucherID from tblRental where rentalID = ? AND customerID = ?";
            stm = con.prepareStatement(sql);
            stm.setInt(1, billMAX);
            stm.setInt(2, customerBillId);
            rs = stm.executeQuery();
            while (rs.next()) {
                int rentalID = rs.getInt("rentalID");
                int customerID = rs.getInt("customerID");
                String rentalDate = rs.getString("rentalDate");
                String returnDate = rs.getString("returnDate");
                float totalAll = rs.getFloat("totalAll");
                String voucherID = rs.getString("voucherID");
                TblRentalDTO dto = new  TblRentalDTO(rentalID, customerID, rentalDate, returnDate, totalAll, voucherID);
                if (this.listRental == null) {
                    this.listRental = new ArrayList<>();
                }
                this.listRental.add(dto);
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
    }

    public int printIdOrder() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbHelp.makeConnection();
            String sql = "Select rentalID=MAX(rentalID) from tblRental";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("rentalID");
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
    
    private List<TblRentalDTO> listRentalHistory;

    public List<TblRentalDTO> getListRentalHistory() {
        return listRentalHistory;
    }
    
    public void printRental(int rentalIDCheck) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DbHelp.makeConnection();
            String sql = "Select rentalID,customerID,rentalDate,returnDate,totalAll,voucherID from tblRental where rentalID = ?";
            stm = con.prepareStatement(sql);
            stm.setInt(1, rentalIDCheck);
               rs = stm.executeQuery();
            while (rs.next()) {
                int rentalID = rs.getInt("rentalID");
                int customerID = rs.getInt("customerID");
                String rentalDate = rs.getString("rentalDate");
                String returnDate = rs.getString("returnDate");
                float totalAll = rs.getFloat("totalAll");
                String voucherID = rs.getString("voucherID");
                TblRentalDTO dto = new  TblRentalDTO(rentalID, customerID, rentalDate, returnDate, totalAll, voucherID);
                if (this.listRentalHistory == null) {
                    this.listRentalHistory = new ArrayList<>();
                }
                this.listRentalHistory.add(dto);
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
    }
}
