/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.dtos;

import java.io.Serializable;

/**
 *
 * @author HUYVU
 */
public class OrderDetailDTO implements Serializable{
    private String orderDetailID;
    private String orderID;
    private String foodID;
    private float price;
    private int quantity;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String orderDetailID, String orderID, String foodID, float price, int quantity) {
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.foodID = foodID;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * @return the orderDetailID
     */
    public String getOrderDetailID() {
        return orderDetailID;
    }

    /**
     * @param orderDetailID the orderDetailID to set
     */
    public void setOrderDetailID(String orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    /**
     * @return the orderID
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * @return the foodID
     */
    public String getFoodID() {
        return foodID;
    }

    /**
     * @param foodID the foodID to set
     */
    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
    
}
