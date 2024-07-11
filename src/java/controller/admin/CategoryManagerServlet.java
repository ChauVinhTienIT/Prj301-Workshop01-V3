/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import context.JWAView;
import blo.CategoryBLO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Categorie;

/**
 *
 * @author Lenovo
 */
public class CategoryManagerServlet extends HttpServlet {
    
    private CategoryBLO categoryBLO;

    @Override
    public void init() {
        categoryBLO = new CategoryBLO();
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
        request.setCharacterEncoding("UTF-8");
        String action = "";
        if(request.getParameter("action") != null){
            action = request.getParameter("action");
        }
        
        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertCategory(request, response);
                    break;
                case "delete":
                    deleteCategory(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateCategory(request, response);
                    break;
                default:
                    listCategory(request, response);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(JWAView.CATEGORY_MANAGER_JSP);
        request.setAttribute("action", "new");
        dispatcher.forward(request, response);
    }
    
    private void insertCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
        request.setCharacterEncoding("UTF-8");
        
        String cateName = request.getParameter("categoryName");
        String memo = request.getParameter("memo");
        
        Categorie newCategory = new Categorie(cateName, memo);
        

        int result = categoryBLO.insertRec(newCategory);

        response.sendRedirect("category-manager?action=list");
    }

    private void listCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Categorie> categoryList = categoryBLO.listAll();
        request.setAttribute("categoryList", categoryList);
        RequestDispatcher dispatcher = request.getRequestDispatcher(JWAView.CATEGORY_MANAGER_JSP);
        dispatcher.forward(request, response);
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String typeIdRaw = request.getParameter("typeId");
        int typeId = Integer.parseInt(typeIdRaw);
        
        Categorie toDelete = categoryBLO.getObjectById(typeId);
        categoryBLO.deleteRec(toDelete);
        response.sendRedirect("category-manager?action=list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String typeId = request.getParameter("typeId");
        Categorie existCategory = categoryBLO.getObjectById(typeId);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(JWAView.CATEGORY_MANAGER_JSP);
        request.setAttribute("action", "edit");
        request.setAttribute("existCategory", existCategory);
        
        dispatcher.forward(request, response);
    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        request.setCharacterEncoding("UTF-8");
        
        String typeIdRaw = request.getParameter("typeId");
        int typeId = Integer.parseInt(typeIdRaw);
        
        String categoryName = request.getParameter("categoryName");
        String memo= request.getParameter("memo");
        
        
        Categorie updateCategory = new Categorie(categoryName,memo);
        updateCategory.setTypeId(typeId);
        
        int result = categoryBLO.updateRec(updateCategory);

        response.sendRedirect("category-manager?action=list");
    }
}
