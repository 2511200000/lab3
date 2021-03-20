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
public class TblRentalDTO implements Serializable {

    private int rentalID;
    private int customerID;
    String rentalDate;
    String returnDate;
    private float totalAll;
    String voucher;

    public TblRentalDTO(int rentalID, int customerID, String rentalDate, String returnDate, float totalAll, String voucher) {
        this.rentalID = rentalID;
        this.customerID = customerID;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.totalAll = totalAll;
        this.voucher = voucher;
    }

    public int getRentalID() {
        return rentalID;
    }

    public void setRentalID(int rentalID) {
        this.rentalID = rentalID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public float getTotalAll() {
        return totalAll;
    }

    public void setTotalAll(float totalAll) {
        this.totalAll = totalAll;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    
    
}
