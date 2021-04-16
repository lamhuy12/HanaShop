/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.servlet;

import huyvl.daos.CategoryDAO;
import huyvl.daos.FoodsDAO;
import huyvl.dtos.CategoryDTO;
import huyvl.dtos.FoodsDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HUYVU
 */
@WebServlet(name = "SearchFoodsServlet", urlPatterns = {"/SearchFoodsServlet"})
public class SearchFoodsServlet extends HttpServlet {

    private final String SEARCHFOOD_PAGE = "searchFood.jsp";

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

        String searchValue = request.getParameter("txtSearchFood");
        String minValueTemp = request.getParameter("txtSearchFoodWithMinPrice");
        String maxValueTemp = request.getParameter("txtSearchFoodWithMaxPrice");
        float minPrice = Float.parseFloat(minValueTemp);
        float maxPrice = Float.parseFloat(maxValueTemp);
        String searchByCate = request.getParameter("cbCategory");

        String url = SEARCHFOOD_PAGE;

        try {
            if (!searchValue.trim().isEmpty()) {
                FoodsDAO foodsDAO = new FoodsDAO();
                foodsDAO.searchFoodsByName(searchValue);
                List<FoodsDTO> resultSearchByName = foodsDAO.getListSearchFoodByName();
                url = SEARCHFOOD_PAGE;
                
                CategoryDAO categoryDAO = new CategoryDAO();
                List<CategoryDTO> listCate = categoryDAO.getAllCategory();

                request.setAttribute("GETALLCATEGORY", listCate);
                request.setAttribute("RESULTSEARCH", resultSearchByName);
            } else if (!searchByCate.equals("Choose")) {
                FoodsDAO foodsDAO = new FoodsDAO();
                foodsDAO.searchFoodsByCateID(searchByCate);
                List<FoodsDTO> resultSearchByCateID = foodsDAO.getListSearchFoodsByCate();
                url = SEARCHFOOD_PAGE;
                
                CategoryDAO categoryDAO = new CategoryDAO();
                List<CategoryDTO> listCate = categoryDAO.getAllCategory();
                
                request.setAttribute("GETALLCATEGORY", listCate);
                request.setAttribute("RESULTSEARCH", resultSearchByCateID);
            } else {
                FoodsDAO foodsDAO = new FoodsDAO();
                foodsDAO.searchFoodsByPrice(minPrice, maxPrice);
                List<FoodsDTO> resultSearchByPrice = foodsDAO.getListSearchFoodsByPrice();
                url = SEARCHFOOD_PAGE;
                
                CategoryDAO categoryDAO = new CategoryDAO();
                List<CategoryDTO> listCate = categoryDAO.getAllCategory();
                
                request.setAttribute("GETALLCATEGORY", listCate);
                request.setAttribute("RESULTSEARCH", resultSearchByPrice);
            }
        } catch (NamingException ex) {
            log("SearchFoodsByNameServlet_Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("SearchFoodsByNameServlet_SQL: " + ex.getMessage());
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
