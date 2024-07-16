/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import blo.AccountAuthBLO;
import blo.AccountBLO;
import context.JWAView;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.AccountAuth;

import org.apache.commons.lang3.RandomStringUtils;
import tools.HashGenerationException;
import tools.HashGeneratorUtils;

/**
 *
 * @author Lenovo
 */
public class UserLoginServlet extends HttpServlet {

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
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
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
        String userName = request.getParameter("account");
        String password = request.getParameter("password");

        boolean rememberMe = "true".equals(request.getParameter("rememberMe"));

        AccountBLO accountBLO = new AccountBLO();

        Account user = accountBLO.checkLogin(userName, password);
        String destPage = "login.jsp";

        if (user != null && user.getRoleId().getRoleId() == 3) {
            
            HttpSession session = request.getSession();
            session.removeAttribute("user");
            session.setAttribute("user", user);

            if (rememberMe) {
                try {
                    AccountAuth newToken = new AccountAuth();
                    String selector = RandomStringUtils.randomAlphabetic(12);
                    String rawValidator = RandomStringUtils.randomAlphabetic(64);

                    String hashedValidator = HashGeneratorUtils.generateSHA256(rawValidator);

                    newToken.setSelector(selector);
                    newToken.setValidator(hashedValidator);

                    newToken.setAccountId(user);

                    AccountAuthBLO accountAuthBLO = new AccountAuthBLO();
                    accountAuthBLO.insertNewRec(newToken);

                    Cookie cookieSelector = new Cookie("selector", selector);
                    cookieSelector.setMaxAge(604800);

                    Cookie cookieValidator = new Cookie("validator", rawValidator);
                    cookieValidator.setMaxAge(604800);

                    response.addCookie(cookieSelector);
                    response.addCookie(cookieValidator);

                } catch (HashGenerationException ex) {
                    Logger.getLogger(UserLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            
            destPage = "home";
        } else {
            String message = "Invalid user name/password";
            request.setAttribute("message", message);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);

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
