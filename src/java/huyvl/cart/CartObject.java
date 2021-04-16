/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.cart;

import huyvl.dtos.FoodsDTO;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author HUYVU
 */
public class CartObject implements Serializable {

    private Map<String, FoodsDTO> foods;

    public Map<String, FoodsDTO> getFoods() {
        return foods;
    }

    public void setFoods(Map<String, FoodsDTO> foods) {
        this.foods = foods;
    }
    

    public void addFoodToCart(FoodsDTO dto) {
        if (this.foods == null) {
            this.foods = new HashMap<>();
        }

        if (this.foods.containsKey(dto.getFoodID())) {
            int newQuantity = this.foods.get(dto.getFoodID()).getQuantity() + 1;
            this.foods.get(dto.getFoodID()).setQuantity(newQuantity);
        } else {
            this.foods.put(dto.getFoodID(), dto);
        }
    }

    public void removeFoodFromCart(String foodID) {
        if (this.foods == null) {
            return;
        }

        if (this.foods.containsKey(foodID)) {
            this.foods.remove(foodID);
            if (this.foods.isEmpty()) {
                this.foods = null;
            }
        }
    }

    public float getTotal() throws Exception {
        float result = 0;

        for (FoodsDTO dto : this.foods.values()) {
            result += dto.getQuantity() * dto.getPrice();
        }
        return result;
    }

    public void updateCart(String foodID, int quantity) throws Exception {
        if (this.foods.containsKey(foodID)) {
            this.foods.get(foodID).setQuantity(quantity);
        }
    }

}
