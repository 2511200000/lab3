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
public class TblCarsRentDetailsDTO implements Serializable{
    private int billID;
    private String carID;
    private int quantity;
    private float price;
    private float total;

    public TblCarsRentDetailsDTO(int billID, String carID, int quantity, float price, float total) {
        this.billID = billID;
        this.carID = carID;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    
}
