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
public class TblCarDAO implements Serializable {

    public int countAllCars(boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbHelp.makeConnection();
            String sql = "Select COUNT(*)"
                    + "from tblCars "
                    + "where status = ?";
            stm = con.prepareStatement(sql);

            stm.setBoolean(1, status);

            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
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

    private List<TblCarDTO> dataList;

    public List<TblCarDTO> getDataList() {
        return dataList;
    }

    public void printData(boolean statusCheck, int index) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbHelp.makeConnection();
            String sql = "with x as(Select ROW_NUMBER() over (order by year desc) as r,carID, carname, image, description, color, year, categoryID, price, quantity, status "
                    + "From tblCars "
                    + "Where status = ?)"
                    + "select carID, carname, image, description, color, year, categoryID, price, quantity, status from x where r between ?*5-4 and ? * 5 ";
            stm = con.prepareStatement(sql);
            stm.setBoolean(1, statusCheck);
            stm.setInt(2, index);
            stm.setInt(3, index);
            rs = stm.executeQuery();
            while (rs.next()) {
                String carID = rs.getString("carID");
                String carname = rs.getString("carname");
                String image = rs.getString("image");
                String description = rs.getString("description");
                String color = rs.getString("color");
                String year = rs.getString("year");
                String category = rs.getString("categoryID");
                TblCategoryDAO dao = new TblCategoryDAO();
                String cateName = dao.getCategoryName(category);
                if (cateName != null) {
                    category = cateName;
                }
                float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                if (status == statusCheck) {
                    status = true;
                }
                TblCarDTO dto = new TblCarDTO(carID, carname, image, description, color, year, category, price, quantity, status);
                if (this.dataList == null) {
                    this.dataList = new ArrayList<>();
                }
                this.dataList.add(dto);

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

    public int countAllCarsToSearch(String carname, float price, String categoryID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbHelp.makeConnection();
            String sql = "Select COUNT(*)"
                    + "from tblCars "
                    + "where carname LIKE ? And price < ? And categoryID LIKE ?";
            stm = con.prepareStatement(sql);

            stm.setString(1, "%" + carname + "%");
            stm.setFloat(2, price);
            if (categoryID.equals("")) {
                stm.setString(3, "%" + categoryID + "%");
            } else {
                stm.setString(3, categoryID);
            }
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
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

    private List<TblCarDTO> searchCarList;

    public List<TblCarDTO> getSearchCarList() {
        return searchCarList;
    }

    public void searchCars(String name, float priceCar, String categoryID, int index) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbHelp.makeConnection();
            String sql = "with x as(Select ROW_NUMBER() over (order by year desc) as r,carID, carname, image, description, color, year, categoryID, price, quantity, status "
                    + "From tblCars "
                    + "Where carname LIKE ? And categoryID LIKE ? And price < ?)"
                    + "select carID, carname, image, description, color, year, categoryID, price, quantity, status from x where r between ?*3-2 and ? * 3";
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");

            if (categoryID.equals("")) {
                stm.setString(2, "%" + categoryID + "%");
            } else {
                stm.setString(2, categoryID);
            }
            stm.setFloat(3, priceCar);
            stm.setInt(4, index);
            stm.setInt(5, index);
            rs = stm.executeQuery();
            while (rs.next()) {
                String carID = rs.getString("carID");
                String carname = rs.getString("carname");
                String image = rs.getString("image");
                String description = rs.getString("description");
                String color = rs.getString("color");
                String year = rs.getString("year");
                String category = rs.getString("categoryID");
                TblCategoryDAO dao = new TblCategoryDAO();
                String cateName = dao.getCategoryName(category);
                if (cateName != null) {
                    category = cateName;
                }
                float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");

                TblCarDTO dto = new TblCarDTO(carID, carname, image, description, color, year, category, price, quantity, status);
                if (this.searchCarList == null) {
                    this.searchCarList = new ArrayList<>();
                }
                this.searchCarList.add(dto);

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

    public List<TblCarDTO> getAllPrice() throws SQLException, NamingException {
        List<TblCarDTO> list = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbHelp.makeConnection();
            String sql = "select price "
                    + "from tblCars ";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Float price = rs.getFloat("price");
                TblCarDTO item = new TblCarDTO(price);
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(item);
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

    public TblCarDTO findItemByID(String id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbHelp.makeConnection();
            String sql = "Select carID, carname, image, description, color, year, categoryID, price, quantity, status "
                    + "from tblCars "
                    + "where carID = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                String carID = rs.getString("carID");
                String carName = rs.getString("carname");
                String image = rs.getString("image");
                String description = rs.getString("description");
                String color = rs.getString("color");
                String year = rs.getString("year");
                String category = rs.getString("categoryID");
                float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                TblCarDTO dto = new TblCarDTO(carID, carName, image, description, color, year, category, price, quantity, status);
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
}
