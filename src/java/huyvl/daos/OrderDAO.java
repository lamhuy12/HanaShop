/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.daos;

import huyvl.ultites.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author HUYVU
 */
public class OrderDAO implements Serializable{
    public boolean createOrder(String orderID, String username,
                    float total, Date dateOfCreate, String status) throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean check = false;
        
        try {
            String sql = "Insert into Orders (OrderID, Username, Total, DateOfCreate, Status) "
                    + "values (?,?,?,?,?)";
            con = DBHelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, orderID);
            stm.setString(2, username);
            stm.setFloat(3, total);
            stm.setTimestamp(4, new Timestamp(dateOfCreate.getTime()));
            stm.setString(5, status);
            check = stm.executeUpdate() > 0;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }
    
    public boolean createOrderDetail(String orderDetailID, String orderID, String foodID, int quantity, float price) throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean check = false;
        
        try {
            String sql = "Insert into OrderFoodsDetail(OrderDetailID, OrderID, foodID, Quantity, Price) values (?,?,?,?,?)";
            con = DBHelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, orderDetailID);
            stm.setString(2, orderID);
            stm.setString(3, foodID);
            stm.setInt(4, quantity);
            stm.setFloat(5, price);
            check = stm.executeUpdate() >0;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }
    
    public String getLastOrderIDByUser(String username) throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String orderID = null;
        try {
            String sql = "Select OrderID from Orders "
                    + "Where DateOfCreate = (Select MAX(DateOfCreate) "
                    + "From Orders where Username = ?)";
            con = DBHelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, username);
            rs = stm.executeQuery();
            if (rs.next()) {
                orderID = rs.getString("OrderID");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return orderID;
    }
}
