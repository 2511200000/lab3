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
public class TblHistoryRentalDTO implements Serializable {

    int historyID;
    int rentalID;
    private String status;
    private String action;
    private String userID;

    public TblHistoryRentalDTO(int historyID, int rentalID, String status, String action, String userID) {
        this.historyID = historyID;
        this.rentalID = rentalID;
        this.status = status;
        this.action = action;
        this.userID = userID;
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

}
