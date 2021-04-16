/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.servlet;

import huyvl.cart.CartObject;
import huyvl.daos.OrderDAO;
import huyvl.daos.RegistrationDAO;
import huyvl.dtos.FoodsDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

    private String SEARCH_PAGE = "searchFood.jsp";

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

        String url = "ListFoodServlet";

        try {
            HttpSession session = request.getSession();
            if (session != null) {
                CartObject cart = (CartObject) session.getAttribute("CUSTCART");
                if (cart != null) {
                    Map<String, FoodsDTO> foods = cart.getFoods();
                    if (foods != null) {
                        OrderDAO orderDAO = new OrderDAO();
                        boolean checkCreateOrder = false;
                        String orderID = "";
                        String username = (String) session.getAttribute("USERNAME");
                        String lastID = orderDAO.getLastOrderIDByUser(username);
                        System.out.println(lastID);

                        if (lastID == null) {
                            orderID = username + "-1";
                        } else {
                            String[] tmp = lastID.split("-");
                            orderID = username + "-" + (Integer.parseInt(tmp[1]) + 1);
                        }

                        checkCreateOrder = orderDAO.createOrder(orderID, username,
                                cart.getTotal(), new Date(), "true");
                        if (checkCreateOrder) {
                            int count = 0;
                            boolean checkStatusCheckOut = false;
                            for (FoodsDTO dto : cart.getFoods().values()) {
                                String orderDetailID = orderID + "-" + count++;
                                orderDAO.createOrderDetail(orderDetailID, orderID, dto.getFoodID(), dto.getQuantity(), dto.getPrice()*dto.getQuantity());

                                checkStatusCheckOut = true;
                            }
                            if (checkStatusCheckOut == true) {
                                session.removeAttribute("CUSTCART");
                            }
                        }
                    }
                }
            }

        } catch (Exception ex) {
            log("CheckOutServlet_Exception:" + ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
