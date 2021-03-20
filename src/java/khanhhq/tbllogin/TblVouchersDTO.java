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
public class TblVouchersDTO implements Serializable{
    private String voucherID;
    int sale;
    Date expiryDate;
    String status;

    public TblVouchersDTO(String voucherID, int sale, Date expiryDate, String status) {
        this.voucherID = voucherID;
        this.sale = sale;
        this.expiryDate = expiryDate;
        this.status = status;
    }

    public String getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(String voucherID) {
        this.voucherID = voucherID;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

    

     

    
    
    
}
