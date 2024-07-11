/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import blo.AccountAuthBLO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AccountAuth;

/**
 *
 * @author Lenovo
 */
public class UserLogoutServlet extends HttpServlet {

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
        request.getSession().removeAttribute("user");

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            String selector = "";

            for (Cookie aCookie : cookies) {
                if (aCookie.getName().equals("selector")) {
                    selector = aCookie.getValue();
                }
            }

            if (!selector.isEmpty()) {
                // delete token from database
                AccountAuthBLO accountAuthBLO = new AccountAuthBLO();
                AccountAuth token = accountAuthBLO.findBySelector(selector);

                if (token != null) {
                    accountAuthBLO.deleteRec(token);

                    Cookie cookieSelector = new Cookie("selector", "");
                    cookieSelector.setMaxAge(0);

                    Cookie cookieValidator = new Cookie("validator", "");
                    cookieValidator.setMaxAge(0);
                    
                    response.addCookie(cookieSelector);
                    response.addCookie(cookieValidator);
                }
            }
        }

        response.sendRedirect(request.getContextPath());
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
