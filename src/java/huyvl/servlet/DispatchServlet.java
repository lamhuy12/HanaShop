    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HUYVU
 */
public class DispatchServlet extends HttpServlet {
//    private final String SEARCH_PAGE = "searchFood.jsp";
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String GET20FOOD_CONTROLLER = "ListFoodServlet";
    private final String LOGOUT_CONTROLLER = "LogoutServlet";
    private final String SEACHFOODS_CONTROLLER = "SearchFoodsServlet";
    private final String SEACHFOODSBYPRICE_CONTROLLER = "SearchFoodsByPriceServlet";
    private final String DELETEFOOD_CONTROLLER = "DeleteFoodsServlet";
    private final String ADDFOODTOCART_CONTROLLER = "AddFoodToCartServlet";
    private final String REMOVEFOODFROMCART_CONTROLLER = "RemoveFoodFromCartServlet";
    private final String UPDATEFOODFROMCART_CONTROLLER = "UpdateQuantityFromCartServlet";
    private final String VIEWCART = "viewCart.jsp";
    private final String CHECKOUT_CONTROLLER = "CheckOutServlet";
    private final String UPDATEFOODS_CONTROLLER = "UpdateFoodsServlet";
    private final String CREATEFOOD_CONTROLLER = "CreateFoodServlet";
    private final String HISTORY_CONTROLLER = "HistoryServlet";
    private final String HISTORYSHOPPING_CONTROLLER = "HistorShoppingServlet";
    
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
        
        String url = GET20FOOD_CONTROLLER;
        String button = request.getParameter("btAction");
        System.out.println("button: " + button);
        
        try {
            if (button == null) {
                url = GET20FOOD_CONTROLLER;
            }
            else if (button.equals("Login")) {
                url = LOGIN_CONTROLLER;
                System.out.println(url);
            } else if (button.equals("Logout")) {
                url = LOGOUT_CONTROLLER;
            } else if (button.equals("Search")) {
                url = SEACHFOODS_CONTROLLER;
//            } else if (button.equals("SearchByPrice")) {
//                url = SEACHFOODSBYPRICE_CONTROLLER;
            } else if ("Delete selected book".equals(button)) {
                url = DELETEFOOD_CONTROLLER;
            } else if ("Add to cart".equals(button)) {
                url = ADDFOODTOCART_CONTROLLER;
            } else if ("Remove selected food".equals(button)) {
                url = REMOVEFOODFROMCART_CONTROLLER;
            } else if ("View your cart".equals(button)) {
                url = VIEWCART;
            } else if ("Update quantity".equals(button)) {
                url = UPDATEFOODFROMCART_CONTROLLER;
            } else if (button.equals("checkOut")) {
                url = CHECKOUT_CONTROLLER;
            } else if (button.equals("Update")) {
                url = UPDATEFOODS_CONTROLLER;
            } else if (button.equals("Create")) {
                url = CREATEFOOD_CONTROLLER;
            } else if ("View history".equals(button)) {
                url = HISTORY_CONTROLLER;
            } else if ("Search by date".equals(button)) {
                url = HISTORYSHOPPING_CONTROLLER;
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
