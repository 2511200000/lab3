/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhhq.cart;

import java.util.HashMap;
import java.util.Map;
import khanhhq.tbllogin.TblCarDTO;

/**
 *
 * @author Administrator
 */
public class CartObject {

    private Map<TblCarDTO, Integer> items;

    public Map<TblCarDTO, Integer> getItems() {
        return items;
    }

    public void addtemToCart(TblCarDTO dto) {
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        int quantity = 1;
        boolean isExist = false;
        if (dto != null) {
           
            for (TblCarDTO car : items.keySet()) {
                if (car.getCarID().equals(dto.getCarID())) {
                    quantity = this.items.get(car);
                    isExist = true;
                    this.items.replace(car, quantity + 1);
                    break;
                } 
            }
            if (!isExist) {
                
                this.items.put(dto, quantity);
            }
        }

    }

    public void removeItemFromCart(String id) {
        if (this.items == null) {
            return;
        }
        for (TblCarDTO car : items.keySet()) {
            if (car.getCarID().equals(id)) {
                this.items.remove(car);
                if (this.items.isEmpty()) {
                    this.items = null;
                }
                break;
            }
        }

    }
}
