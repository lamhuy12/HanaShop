/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.servlet;

import huyvl.daos.FoodsDAO;
import huyvl.daos.HistoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HUYVU
 */
@WebServlet(name = "DeleteFoodsServlet", urlPatterns = {"/DeleteFoodsServlet"})
public class DeleteFoodsServlet extends HttpServlet {

    private String ERROR_PAGE = "error.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

//        String[] checkDelete = request.getParameterValues("pkDelete");
        String foodID = request.getParameter("pkDelete");

        String lastSearchValue = request.getParameter("lastSearchValue");
        String urlRewriting = ERROR_PAGE;
        String minValueTemp = request.getParameter("lastSearchMinPrice");
        String maxValueTemp = request.getParameter("lastSearchMaxPrice");
        float minPrice = Float.parseFloat(minValueTemp);
        float maxPrice = Float.parseFloat(maxValueTemp);
        String cateValue = request.getParameter("lastSearchCate");
        //boolean resultDelete = false;

        try {
            FoodsDAO foodsDAO = new FoodsDAO();
//            for (String foodDelete : checkDelete) {
//                resultDelete = foodsDAO.deleteFoods(foodDelete);
//                
//                HttpSession session = request.getSession();
//                String username = (String) session.getAttribute("USERNAME");
//                HistoryDAO dao = new HistoryDAO();
//                dao.history(username + " delete " + foodDelete, new Timestamp(new Date().getTime()));
//
//            }
            boolean result = foodsDAO.deleteFoods(foodID);
            if (result) {
                urlRewriting = "DispatchServlet"
                        + "?txtSearchFood=" + lastSearchValue
                        + "&btAction=Search"
                        + "&cbCategory=" + cateValue
                        + "&txtSearchFoodWithMinPrice=" + minPrice
                        + "&txtSearchFoodWithMaxPrice=" + maxPrice;
                
                HttpSession session = request.getSession();
                String username = (String) session.getAttribute("USERNAME");
                HistoryDAO dao = new HistoryDAO();
                dao.history(username + " delete " + foodID, new Timestamp(new Date().getTime()));

            }
        } catch (SQLException ex) {
            log("DeleteFoodsServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("DeleteFoodsServlet_Naming: " + ex.getMessage());
        } finally {
            response.sendRedirect(urlRewriting);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
