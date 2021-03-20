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
public class TblCarsRentDetailsDAO implements Serializable {

    public boolean createOrderDetails(int billID, String carID, int quantity, float price, float total) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        System.out.println("ksdskdkasd " + billID);
        try {
            con = DbHelp.makeConnection();
            if (con != null) {
                String sql = "Insert into tblCarsRentDetails(billID,carID,quantity,price,total)"
                        + "Values(?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, billID);
                stm.setString(2, carID);
                stm.setInt(3, quantity);
                stm.setFloat(4, price);
                stm.setFloat(5, total);
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

    private List<TblCarsRentDetailsDTO> listRentalHistoryDetails;

    public List<TblCarsRentDetailsDTO> getListRentalHistoryDetails() {
        return listRentalHistoryDetails;
    }

    public void printHistoryRentalDetails() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DbHelp.makeConnection();
            String sql = "Select billID,carID,quantity,price,total from tblCarsRentDetails";
            stm = con.prepareStatement(sql);
             rs = stm.executeQuery();
            while (rs.next()) {
                int billID = rs.getInt("billID");
                String carID = rs.getString("carID");
                int quantity = rs.getInt("quantity");

                float price = rs.getFloat("price");
                float total = rs.getFloat("total");
                TblCarsRentDetailsDTO dto = new TblCarsRentDetailsDTO(billID, carID, quantity, price, total);
                if (this.listRentalHistoryDetails == null) {
                    this.listRentalHistoryDetails = new ArrayList<>();
                }
                this.listRentalHistoryDetails.add(dto);
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
