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
public class TblCustomerDTO implements Serializable{
    int customerID;
    private String customerName;
    private String address;
    private String phoneNumber;
    String rentalDate;
    String returnDate;

    public TblCustomerDTO(int customerID, String customerName, String address, String phoneNumber, String rentalDate, String returnDate) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

   

   
    
    
}
