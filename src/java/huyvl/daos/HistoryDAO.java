/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.daos;

import huyvl.dtos.HistoryDTO;
import huyvl.ultites.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author HUYVU
 */
public class HistoryDAO implements Serializable {

    public boolean history(String action, Timestamp time) throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean check = false;

        try {
            con = DBHelper.makeConnection();
            String sql = "insert into History(Action, Time) "
                    + "values(?,?)";

            stm = con.prepareStatement(sql);
            stm.setString(1, action);
            stm.setTimestamp(2, new Timestamp(time.getTime()));
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

    public List<HistoryDTO> getListHistory() throws Exception {
        List<HistoryDTO> list = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "Select Action, Time "
                + "From History";
        try {
            con = DBHelper.makeConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String action = rs.getString("Action");
                Timestamp time = rs.getTimestamp("Time");
                HistoryDTO dto = new HistoryDTO(action, time);

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
