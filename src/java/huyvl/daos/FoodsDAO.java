/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.daos;

import huyvl.dtos.FoodsDTO;
import huyvl.ultites.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author HUYVU
 */
public class FoodsDAO implements Serializable {

    private List<FoodsDTO> loadFoodList;

    public List<FoodsDTO> getLoadFoodList() {
        return loadFoodList;
    }

    public void get20FoodFirst() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select top (20) "
                        + "foodID, Name, Image, Description, Price, createDate, Quantity, status, cateID "
                        + "from FoodDetail "
                        + "where status = 1 order by createDate DESC";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String foodID = rs.getString("foodID");
                    String name = rs.getString("Name");
                    String image = rs.getString("Image");
                    String description = rs.getString("Description");
                    float price = rs.getFloat("Price");
                    Date createdate = rs.getDate("createDate");
                    int quantity = rs.getInt("Quantity");
                    boolean status = rs.getBoolean("status");
                    String cateID = rs.getString("cateID");
                    FoodsDTO dto = new FoodsDTO(foodID, name, image, description, price, createdate, quantity, status, cateID);

                    if (loadFoodList == null) {
                        this.loadFoodList = new ArrayList<>();
                    }

                    this.loadFoodList.add(dto);
                }
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
    }

    List<FoodsDTO> listSearchFoodByName;

    public List<FoodsDTO> getListSearchFoodByName() {
        return listSearchFoodByName;
    }

    public void searchFoodsByName(String searchValue) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select foodID, Name, Image, Description, Price, createDate, Quantity, status, cateID "
                        + "from FoodDetail "
                        + "where Name like ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String foodID = rs.getString("foodID");
                    String name = rs.getString("Name");
                    String image = rs.getString("Image");
                    String description = rs.getString("Description");
                    float price = rs.getFloat("Price");
                    Date createdate = rs.getDate("createDate");
                    int quantity = rs.getInt("Quantity");
                    boolean status = rs.getBoolean("status");
                    String cateID = rs.getString("cateID");
                    FoodsDTO dto = new FoodsDTO(foodID, name, image, description, price, createdate, quantity, status, cateID);

                    if (this.listSearchFoodByName == null) {
                        this.listSearchFoodByName = new ArrayList<>();
                    }

                    this.listSearchFoodByName.add(dto);
                }
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

    }

    List<FoodsDTO> listSearchFoodsByPrice;

    public List<FoodsDTO> getListSearchFoodsByPrice() {
        return listSearchFoodsByPrice;
    }

    public void searchFoodsByPrice(float minValue, float maxValue) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select foodID, Name, Image, Description, Price, createDate, Quantity, status, cateID "
                        + "from FoodDetail "
                        + "where Price between ? and ?";

                stm = con.prepareStatement(sql);
                stm.setFloat(1, minValue);
                stm.setFloat(2, maxValue);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String foodID = rs.getString("foodID");
                    String name = rs.getString("Name");
                    String image = rs.getString("Image");
                    String description = rs.getString("Description");
                    float price = rs.getFloat("Price");
                    Date createdate = rs.getDate("createDate");
                    int quantity = rs.getInt("Quantity");
                    boolean status = rs.getBoolean("status");
                    String cateID = rs.getString("cateID");
                    FoodsDTO dto = new FoodsDTO(foodID, name, image, description, price, createdate, quantity, status, cateID);

                    if (this.listSearchFoodsByPrice == null) {
                        this.listSearchFoodsByPrice = new ArrayList<>();
                    }

                    this.listSearchFoodsByPrice.add(dto);
                }
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
    }

    List<FoodsDTO> listSearchFoodsByCate;

    public List<FoodsDTO> getListSearchFoodsByCate() {
        return listSearchFoodsByCate;
    }

    public void searchFoodsByCateID(String cateIDValue) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select foodID, Name, Image, Description, Price, createDate, Quantity, status, cateID "
                        + "from FoodDetail "
                        + "where cateID = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, cateIDValue);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String foodID = rs.getString("foodID");
                    String name = rs.getString("Name");
                    String image = rs.getString("Image");
                    String description = rs.getString("Description");
                    float price = rs.getFloat("Price");
                    Date createdate = rs.getDate("createDate");
                    int quantity = rs.getInt("Quantity");
                    boolean status = rs.getBoolean("status");
                    String cateID = rs.getString("cateID");
                    FoodsDTO dto = new FoodsDTO(foodID, name, image, description, price, createdate, quantity, status, cateID);

                    if (this.listSearchFoodsByCate == null) {
                        this.listSearchFoodsByCate = new ArrayList<>();
                    }

                    this.listSearchFoodsByCate.add(dto);
                }
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
    }

    public boolean deleteFoods(String foodID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "update FoodDetail "
                        + "set Status = 0 "
                        + "where foodID = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, foodID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
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
        return false;
    }

    public boolean updateFood(String foodID, String name, String image, String description, float price, int quantity, boolean status, String cateID)
            throws SQLException, NamingException, Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql;
            if (con != null) {
                if (image.equals("")) {
                    sql = "update FoodDetail "
                            + "set Name=?, Description=?, Price=?, Quantity=?, Status=?, cateID=? "
                            + "where foodID = ?";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, name);
                    stm.setString(2, description);
                    stm.setFloat(3, price);
                    stm.setInt(4, quantity);
                    stm.setBoolean(5, status);
                    stm.setString(6, cateID);
                    stm.setString(7, foodID);
                } else {

                    sql = "update FoodDetail "
                            + "set Name=?, Image=?, Description=?, Price=?, Quantity=?, Status=?, cateID=? "
                            + "where foodID = ?";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, name);
                    stm.setString(2, image);
                    stm.setString(3, description);
                    stm.setFloat(4, price);
                    stm.setInt(5, quantity);
                    stm.setBoolean(6, status);
                    stm.setString(7, cateID);
                    stm.setString(8, foodID);
                }

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
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
        return false;
    }

    public FoodsDTO findPrimaryKey(String foodID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        FoodsDTO dto = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select Name, Image, Description, Price, createDate, Quantity, status, cateID "
                        + "from FoodDetail "
                        + "where foodID = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, foodID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("Name");
                    String image = rs.getString("Image");
                    String description = rs.getString("Description");
                    float price = rs.getFloat("Price");
                    Date createdate = rs.getDate("createDate");
                    int quantity = rs.getInt("Quantity");
                    boolean status = rs.getBoolean("status");
                    String cateID = rs.getString("cateID");
                    dto = new FoodsDTO(foodID, name, image, description, price, createdate, quantity, status, cateID);
                }
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
        return dto;
    }

    public boolean createFood(String foodID, String name, String image, String description, float price, Date createDate, int quantity, boolean status, String cateID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean check = false;

        try {
            con = DBHelper.makeConnection();
            String sql = "Insert into FoodDetail(foodID, Name, Image, Description, Price, createDate, Quantity, Status, cateID) "
                    + "values(?,?,?,?,?,?,?,?,?)";

            stm = con.prepareStatement(sql);
            stm.setString(1, foodID);
            stm.setString(2, name);
            stm.setString(3, image);
            stm.setString(4, description);
            stm.setFloat(5, price);
            stm.setTimestamp(6, new Timestamp(createDate.getTime()));
            stm.setInt(7, quantity);
            stm.setBoolean(8, status);
            stm.setString(9, cateID);
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

    public int getTotal(String foodID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select count(?) from FoodDetail";
                stm = con.prepareStatement(sql);
                stm.setString(1, foodID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    count = rs.getInt(1);
                }
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
        return count;
    }
    
    List<FoodsDTO> listSearchFoodByStatus;

    public List<FoodsDTO> getListSearchFoodByStatus() {
        return listSearchFoodByStatus;
    }


    public void searchFoodsByStatus(boolean statusValue) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select foodID, Name, Image, Description, Price, createDate, Quantity, status, cateID "
                        + "from FoodDetail "
                        + "where status like ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + statusValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String foodID = rs.getString("foodID");
                    String name = rs.getString("Name");
                    String image = rs.getString("Image");
                    String description = rs.getString("Description");
                    float price = rs.getFloat("Price");
                    Date createdate = rs.getDate("createDate");
                    int quantity = rs.getInt("Quantity");
                    boolean status = rs.getBoolean("status");
                    String cateID = rs.getString("cateID");
                    FoodsDTO dto = new FoodsDTO(foodID, name, image, description, price, createdate, quantity, status, cateID);

                    if (this.listSearchFoodByStatus == null) {
                        this.listSearchFoodByStatus = new ArrayList<>();
                    }

                    this.listSearchFoodByStatus.add(dto);
                }
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

    }

}
