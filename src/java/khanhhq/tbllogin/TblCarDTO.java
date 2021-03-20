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
public class TblCarDTO implements Serializable{
    private String carID;
    private String carName;
    private String image;
    private String description;
    private String color;
    private String year;
    private String category;
    float price;
    int quantity;
    boolean status;

    public TblCarDTO(String carID, String carName, String image, String description, String color, String year, String category, float price, int quantity, boolean status) {
        this.carID = carID;
        this.carName = carName;
        this.image = image;
        this.description = description;
        this.color = color;
        this.year = year;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }

    public TblCarDTO(float price) {
        this.price = price;
    }

    
    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

   
    
}
