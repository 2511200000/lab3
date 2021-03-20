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
public class TblHistoryRentalDAO implements Serializable {

    public boolean createHistoryRental(int historyID, int rentalID, String status, String action, String userID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DbHelp.makeConnection();
            if (con != null) {

                String sql = "Insert into tblHistoryRental (historyID, rentalID, status, action, userID) "
                        + "Values(?, ?, ?, ?, ?)";
                 stm = con.prepareStatement(sql);
                stm.setInt(1, historyID);
                stm.setInt(2, rentalID);
                stm.setString(3, status);
                stm.setString(4, action);
                stm.setString(5, userID);
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
            String sql = "Select historyID=MAX(historyID) from tblRental";
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
            String sql = "Select historyID, rentalID, status, action, userID "
                    + "from tblHistoryRental "
                    + " where userID = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, userIDCheck);
            rs = stm.executeQuery();
            while (rs.next()) {
                int historyID = rs.getInt("historyID");
                int rentalID = rs.getInt("rentalID");

                String userID = rs.getString("userID");
//                tblItemDAO dao = new tblItemDAO();
//                String itemname = dao.getItemName(itemID);
//                tblLoginDAO daoLogin = new tblLoginDAO();
//                String username = daoLogin.getUsername(userID);

                String status = rs.getString("status");
                String action = rs.getString("action");

                TblHistoryRentalDTO dto = new TblHistoryRentalDTO(historyID, rentalID, status, action, userID);
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
}
