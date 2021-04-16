/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.dtos;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author HUYVU
 */
public class FoodsDTO implements Serializable{
    private String foodID;
    private String name;
    private String image;
    private String description;
    private float price;
    private Date createDate;
    private int quantity;
    private boolean status;
    private String cateID;

    public FoodsDTO() {
    }

    public FoodsDTO(String foodID, String name, String image, String description, float price, Date createDate, int quantity, boolean status, String cateID) {
        this.foodID = foodID;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.quantity = quantity;
        this.status = status;
        this.cateID = cateID;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the Quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param Quantity the Quantity to set
     */
    public void setQuantity(int Quantity) {
        this.quantity = Quantity;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return the cateID
     */
    public String getCateID() {
        return cateID;
    }

    /**
     * @param cateID the cateID to set
     */
    public void setCateID(String cateID) {
        this.cateID = cateID;
    }

    
    
}
