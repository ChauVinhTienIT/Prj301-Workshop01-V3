/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import context.JWAView;
import blo.CategoryBLO;
import blo.ProductBLO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Categorie;

import model.Product;

/**
 *
 * @author Lenovo
 */
public class HomePageController extends HttpServlet {
    
    private ProductBLO productBlo;
    private CategoryBLO categoryBlo;

    @Override
    public void init() {
        productBlo = new ProductBLO();
        categoryBlo = new CategoryBLO();
    }

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = "";
        if(request.getParameter("action") != null){
            action = request.getParameter("action");
        }
        
        try {
            switch (action) {
                case "all":
                    listAllProduct(request, response);
                    break;
                case "sale":
                    listSaleProduct(request, response);
                    break;
                case "category":
                    listProductByCate(request, response);
                    break;
                case "detail":
                    showProductDetail(request, response);
                    break;
                default:
                    listNewProduct(request, response);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void listNewProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Product> productList = productBlo.listAll();
        List<Product> newProductList = new ArrayList();
        if(productList.size() >= 8){
            for (int i = 0; i < 7; i++) {
                newProductList.add(productList.get(i));
            }
        }else{
            newProductList = productList;
        }
        
        request.setAttribute("productList", newProductList);
        request.setAttribute("pageContent", "New Arrivals");
        RequestDispatcher dispatcher = request.getRequestDispatcher(JWAView.HOME_JSP);
        dispatcher.forward(request, response);
    }

    private void listAllProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Product> productList = productBlo.listAll();
        
        for (Product product : productList) {
            System.out.println(product);
        }
        request.setAttribute("productList", productList);
        request.setAttribute("pageContent", "Our's Products");
        RequestDispatcher dispatcher = request.getRequestDispatcher(JWAView.HOME_JSP);
        dispatcher.forward(request, response);
    }
    
    private void listSaleProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Product> productList = productBlo.listIfDiscount();
        request.setAttribute("productList", productList);
        request.setAttribute("pageContent", "In Sale Products");
        RequestDispatcher dispatcher = request.getRequestDispatcher(JWAView.HOME_JSP);
        dispatcher.forward(request, response);
    }
    
    private void listProductByCate(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String typeIdRaw = request.getParameter("typeId");
        int typeId =  Integer.parseInt(typeIdRaw);
        
        List<Product> cateProduct = productBlo.listByCategory(categoryBlo.getObjectById(typeId));
        String category = categoryBlo.getObjectById(typeIdRaw).getCategoryName();
        
        request.setAttribute("productList", cateProduct);
        request.setAttribute("pageContent", category);
        RequestDispatcher dispatcher = request.getRequestDispatcher(JWAView.HOME_JSP);
        dispatcher.forward(request, response);
    }

    private void showProductDetail(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String productId = request.getParameter("productId");
        
        Product product = productBlo.getObjectById(productId);
        Categorie typeId = product.getTypeId();
        List<Product> productList = productBlo.listByCategory(typeId);
        
        request.setAttribute("product", product);
        request.setAttribute("relateProductList", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher(JWAView.ITEM_JSP);
        dispatcher.forward(request, response);
    }
}
