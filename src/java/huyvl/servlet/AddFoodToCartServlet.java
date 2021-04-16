/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.servlet;

import huyvl.cart.CartObject;
import huyvl.daos.FoodsDAO;
import huyvl.dtos.FoodsDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
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
@WebServlet(name = "AddFoodToCartServlet", urlPatterns = {"/AddFoodToCartServlet"})
public class AddFoodToCartServlet extends HttpServlet {

    private final String SEARCHFOOD_PAGE = "searchFood.jsp";
    private final String LOGIN_PAGE = "login.html";
    private final String ERROR_PAGE = "error.jsp";

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

        String lastSearchValue = request.getParameter("lastSearchValue");
        String urlRewriting = ERROR_PAGE;
        String minValueTemp = request.getParameter("lastSearchMinPrice");
        String maxValueTemp = request.getParameter("lastSearchMaxPrice");
        float minPrice = Float.parseFloat(minValueTemp);
        float maxPrice = Float.parseFloat(maxValueTemp);
        String cateValue = request.getParameter("lastSearchCate");

        try {
            HttpSession session = request.getSession();

            if (session != null) {
                String checkLogin = (String) session.getAttribute("FULLNAME");
                if (checkLogin != null) {
                    CartObject cart = (CartObject) session.getAttribute("CUSTCART");
                    if (cart == null) {
                        cart = new CartObject();
                    }

                    String foodID = request.getParameter("txtFoodIDUser");
                    FoodsDAO foodsDAO = new FoodsDAO();
                    FoodsDTO foodsDTO = foodsDAO.findPrimaryKey(foodID);
                    foodsDTO.setQuantity(1);

                    cart.addFoodToCart(foodsDTO);
                    session.setAttribute("CUSTCART", cart);
                    urlRewriting = "DispatchServlet"
                            + "?txtSearchFood=" + lastSearchValue
                            + "&btAction=Search"
                            + "&cbCategory=" + cateValue
                            + "&txtSearchFoodWithMinPrice=" + minPrice
                            + "&txtSearchFoodWithMaxPrice=" + maxPrice;
                } else {
                    urlRewriting = LOGIN_PAGE;
                }
            } 

        } catch (SQLException ex) {
            log("AddFoodToCartServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("AddFoodToCartServlet_Naming: " + ex.getMessage());
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
