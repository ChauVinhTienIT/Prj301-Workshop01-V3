/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import blo.ProductBLO;
import cart.Cart;
import context.JWAView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Product;
import model.Item;

/**
 *
 * @author Lenovo
 */
public class CartManagerServlet extends HttpServlet {

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
        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }
        try {
            switch (action) {
                case "show":
                    listAllItem(request, response);
                    break;
                case "add":
                    addNewItem(request, response);
                    break;
                case "remove":
                    removeItem(request, response);
                    break;
                case "update":
                    updateItem(request, response);
                    break;
                default:
                    listAllItem(request, response);
                    break;
            }
        } catch (IOException ex) {
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

    private void listAllItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(JWAView.CART_JSP);
        dispatcher.forward(request, response);
    }

    private void addNewItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productID = request.getParameter("productId");
        String quantityRaw = request.getParameter("quantity");
        
        int quantity = 1;
        if (quantityRaw != null) {
            quantity = Integer.parseInt(quantityRaw);
        }
        ProductBLO productBLO = new ProductBLO();
        Product product = productBLO.getObjectById(productID);

        Item item = new Item();
        item.setProduct(product);
        item.setQuantity(quantity);
        HttpSession session = request.getSession();

        Cart cart = (Cart) session.getAttribute("Cart");
        if (cart == null) {
            cart = new Cart();
        }

        cart.add(item);

        session.setAttribute("Cart", cart);

        request.setAttribute("Message", "Added " + product.getProductName() + " - " + 1 + " cái" + " - " + product.getProductImage());

        RequestDispatcher dispatcher = request.getRequestDispatcher(JWAView.CART_JSP);
        dispatcher.forward(request, response);
    }

    private void removeItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("productId");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("Cart");
        if (cart != null) {
            cart.remove(id);
            session.setAttribute("Cart", cart);
            request.getRequestDispatcher(JWAView.CART_JSP).forward(request, response);
        }
    }
    

    private void updateItem(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
