/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhhq.tbllogin;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class TblHistoryRentalDTO implements Serializable {

    int historyID;
    int rentalID;
    private String status;
    private String action;
    private String userID;
    private Date bookingDate;
    private String carID;

    public TblHistoryRentalDTO(int historyID, int rentalID, String status, String action, String userID, Date bookingDate, String carID) {
        this.historyID = historyID;
        this.rentalID = rentalID;
        this.status = status;
        this.action = action;
        this.userID = userID;
        this.bookingDate = bookingDate;
        this.carID = carID;
    }

    public int getHistoryID() {
        return historyID;
    }

    public void setHistoryID(int historyID) {
        this.historyID = historyID;
    }

    public int getRentalID() {
        return rentalID;
    }

    public void setRentalID(int rentalID) {
        this.rentalID = rentalID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

  
}
