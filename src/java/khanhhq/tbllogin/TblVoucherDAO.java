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
public class TblVoucherDAO implements Serializable {

    public List<TblVouchersDTO> getVoucher() throws SQLException, NamingException {
        List<TblVouchersDTO> list = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
        con = DbHelp.makeConnection();
            String sql = "select voucherID, expiryDate, status, sale "
                    + "from tblVouchers ";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String voucherID = rs.getString("voucherID");
                Date expiryDate = rs.getDate("expiryDate");
                String status = rs.getString("status");
                int sale = rs.getInt("sale");

                TblVouchersDTO dto = new TblVouchersDTO(voucherID, sale, expiryDate, status);
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(dto);
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
        return list;
    }
      public boolean updateVoucher(String status, String voucherID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DbHelp.makeConnection();
            if (con != null) {
                String sql = "Update tblVouchers "
                        + "set status = ? "
                        + "where voucherID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, status);
                stm.setString(2, voucherID);
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
