/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.servlet;

import huyvl.daos.FoodsDAO;
import huyvl.daos.HistoryDAO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author HUYVU
 */
@WebServlet(name = "UpdateFoodsServlet", urlPatterns = {"/UpdateFoodsServlet"})
public class UpdateFoodsServlet extends HttpServlet {

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
        String urlRewriting = ERROR_PAGE;

        try {
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            if (!isMultiPart) {

            } else {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                try {
                    items = upload.parseRequest(request);
                } catch (FileUploadException e) {
                    e.printStackTrace();
                }
                Iterator iter = items.iterator();
                Hashtable params = new Hashtable();
                String fileName = null;
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        params.put(item.getFieldName(), item.getString());
                    } else {
                        try {
                            String itemName = item.getName();
                            fileName = itemName.substring(itemName.lastIndexOf("\\") + 1);
                            System.out.println("path " + fileName);
                            String RealPath = getServletContext().getRealPath("/")
                                    + "images\\" + fileName;
                            System.out.println("rPath " + RealPath);
                            File savedFile = new File(RealPath);
                            item.write(savedFile);
                        } catch (Exception e) {
                            log("UpdateFoodsServlet Exception "+e.getMessage());
                        }
                    }
                }

                String foodID = (String) params.get("txtFoodID");
                String name = (String) params.get("txtFoodName");
//                String image = request.getParameter("txtFoodImage");
                String description = (String) params.get("txtFoodDescription");
                String priceTemp = (String) params.get("txtFoodPrice");
                float price = Float.parseFloat(priceTemp);
                String quantiyTemp = (String) params.get("txtFoodQuantity");
                int quantity = Integer.parseInt(quantiyTemp);
                String status = (String) params.get("chkStatus");
                boolean checkStatus = true;
                
                if (status == null) {
                    checkStatus = false;
                }

                String cateID = (String) params.get("txtCateID");

                String lastSearchValue = (String) params.get("lastSearchValue");
                String minValueTemp = (String) params.get("lastSearchMinPrice");
                String maxValueTemp = (String) params.get("lastSearchMaxPrice");
                float minPrice = Float.parseFloat(minValueTemp);
                float maxPrice = Float.parseFloat(maxValueTemp);
                String cateValue = (String) params.get("lastSearchCate");
                
                
                try {
                    FoodsDAO foodsDAO = new FoodsDAO();
                    boolean resultUpdate = foodsDAO.updateFood(foodID, name, fileName, description, price, quantity, checkStatus, cateID);

                    if (resultUpdate) {
                        urlRewriting = "DispatchServlet"
                                + "?txtSearchFood=" + lastSearchValue
                                + "&btAction=Search"
                                + "&cbCategory=" + cateValue
                                + "&txtSearchFoodWithMinPrice=" + minPrice
                                + "&txtSearchFoodWithMaxPrice=" + maxPrice;

                        HttpSession session = request.getSession();
                        String username = (String) session.getAttribute("USERNAME");
                        HistoryDAO dao = new HistoryDAO();
                        dao.history(username + " update foods", new Timestamp(new Date().getTime()));
                    }
                } catch (NamingException ex) {
                    log("UpdateFoodsServlet_Naming: " + ex.getMessage());
                } catch (Exception ex) {
                    log("UpdateFoodsServlet_Exception: " + ex.getMessage());
                }

            }
        }
        finally {
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
        
            () {
        return "Short description";
        }// </editor-fold>

    }
