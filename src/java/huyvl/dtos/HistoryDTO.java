/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author HUYVU
 */
public class HistoryDTO implements Serializable{
    private String action;
    private Timestamp time;

    public HistoryDTO() {
    }

    public HistoryDTO(String action, Timestamp time) {
        this.action = action;
        this.time = time;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return the time
     */
    public Timestamp getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Timestamp time) {
        this.time = time;
    }
    
    
    
}
