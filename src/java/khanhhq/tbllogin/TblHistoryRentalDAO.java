/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhhq.tbllogin;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
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
public class TblHistoryRentalDAO implements Serializable {
    
    public boolean createHistoryRental(int historyID, int rentalID, String status, String action, String userID, Date bookingDate, String carID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DbHelp.makeConnection();
            if (con != null) {
                
                String sql = "Insert into tblHistoryRental(historyID, rentalID, status, action, userID, bookingDate, carID) "
                        + "Values(?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, historyID);
                stm.setInt(2, rentalID);
                stm.setString(3, status);
                stm.setString(4, action);
                stm.setString(5, userID);
                stm.setDate(6, bookingDate);
                stm.setString(7, carID);
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
    
    public int printIdOrder() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbHelp.makeConnection();
            String sql = "Select historyID=MAX(historyID) from tblHistoryRental";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("historyID");
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
    
    private List<TblHistoryRentalDTO> listHistoryRental;
    
    public List<TblHistoryRentalDTO> getListHistoryRental() {
        return listHistoryRental;
    }
    
    public void printHistoryRental(String userIDCheck) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbHelp.makeConnection();
            String sql = "Select historyID, rentalID, status, action, userID, bookingDate, carID "
                    + "from tblHistoryRental "
                    + "where userID = ? AND status = '123'"
                    + "ORDER BY bookingDate";
            stm = con.prepareStatement(sql);
            stm.setString(1, userIDCheck);
            rs = stm.executeQuery();
            while (rs.next()) {
                int historyID = rs.getInt("historyID");
                int rentalID = rs.getInt("rentalID");
                
                String userID = rs.getString("userID");           

                String status = rs.getString("status");
                String action = rs.getString("action");
                Date bookingDate = rs.getDate("bookingDate");
                String carID = rs.getString("carID");
                
                TblHistoryRentalDTO dto = new TblHistoryRentalDTO(historyID, rentalID, status, action, userID, bookingDate, carID);
                if (this.listHistoryRental == null) {
                    this.listHistoryRental = new ArrayList<>();
                }
                this.listHistoryRental.add(dto);
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
    
    private List<TblHistoryRentalDTO> searchHistory;
    
    public List<TblHistoryRentalDTO> getSearchHistory() {
        return searchHistory;
    }
    
    public void searchHistory(String carIDCheck, String bookingDateCheck) throws SQLException, NamingException {
        {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            try {
                con = DbHelp.makeConnection();
                if (con != null) {
                    String sql = "Select historyID, rentalID, status, action, userID, bookingDate, carID "
                            + "From tblHistoryRental "
                            + "Where carID in (select carID from tblCars where carname LIKE ?) AND bookingDate LIKE ?";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, "%" + carIDCheck + "%");
                    stm.setString(2, "%" + bookingDateCheck + "%");
                    
                    rs = stm.executeQuery();
                    while (rs.next()) {
                        int historyID = rs.getInt("historyID");
                        int rentalID = rs.getInt("rentalID");
                        String status = rs.getString("status");
                        String action = rs.getString("action");
                        String userID = rs.getString("userID");
                        Date bookingDate = rs.getDate("bookingDate");
                        String carID = rs.getString("carID");
                        TblHistoryRentalDTO dto = new TblHistoryRentalDTO(historyID, rentalID, status, action, userID, bookingDate, carID);
                        if (this.listHistoryRental == null) {
                            this.listHistoryRental = new ArrayList<>();
                        }
                        this.listHistoryRental.add(dto);
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
        }
    }
    
     public boolean updateHistory(int historyID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DbHelp.makeConnection();
            if (con != null) {
                String sql = "Update tblHistoryRental "
                        + "set status = '321' "
                        + "where historyID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, historyID);
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
}
