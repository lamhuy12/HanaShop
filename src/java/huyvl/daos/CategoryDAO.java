/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.daos;

import huyvl.dtos.CategoryDTO;
import huyvl.dtos.FoodsDTO;
import huyvl.ultites.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author HUYVU
 */
public class CategoryDAO implements Serializable {

    public List<CategoryDTO> getAllCategory() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<CategoryDTO> list = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select cateID, Name, Description "
                        + "from FoodCategory";

                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String cateID = rs.getString("cateID");
                    String name = rs.getString("Name");
                    String description = rs.getString("Description");
                    CategoryDTO dto = new CategoryDTO(cateID, name, description);
                    if (list == null) {
                        list = new ArrayList();
                    }
                    list.add(dto);
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
        return list;
    }
    
    
}
