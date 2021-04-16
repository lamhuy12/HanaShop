/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.daos;

import huyvl.dtos.HistoryDTO;
import huyvl.dtos.OrderDetailDTO;
import huyvl.ultites.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HUYVU
 */
public class HistoryShoppingDAO implements Serializable {

    public List<OrderDetailDTO> getListHistoryShopping(String date, String username) throws Exception {
        List<OrderDetailDTO> list = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "select ofd.foodID, ofd.OrderDetailID, ofd.OrderID, ofd.Price, ofd.Quantity\n"
                    + "from OrderFoodsDetail as ofd, Orders as od\n"
                    + "where ofd.OrderID = od.OrderID and DAY(od.DateOfCreate) =? and od.Username = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, date);
            stm.setString(2, username);
            rs = stm.executeQuery();
            while (rs.next()) {
                String foodID = rs.getString("foodID");
                String orderDetailID = rs.getString("OrderDetailID");
                String orderID = rs.getString("OrderID");
                float price = rs.getFloat("Price");
                int quantity = rs.getInt("Quantity");
                OrderDetailDTO dto = new OrderDetailDTO(orderDetailID, orderID, foodID, price, quantity);

                if (list == null) {
                    list = new ArrayList<>();
                }

                list.add(dto);
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
        return list;
    }
}
