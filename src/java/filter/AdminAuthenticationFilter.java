/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import blo.AccountAuthBLO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.AccountAuth;
import tools.HashGenerationException;
import tools.HashGeneratorUtils;

/**
 *
 * @author Lenovo
 */
public class AdminAuthenticationFilter implements Filter {

    public AdminAuthenticationFilter() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        
        boolean isLoggedIn = checkIsRememberMe(httpRequest, httpResponse, session, false);
        if (session != null && session.getAttribute("user") != null) {
            Account user = (Account) session.getAttribute("user");
            //Role = 1 : admin
            isLoggedIn = user.getRoleId().getRoleId() == 1;
        }
        

        String loginURI = httpRequest.getContextPath() + "/admin/login";

        boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);

        boolean isLoginPage = httpRequest.getRequestURI().endsWith("login.jsp");

        if (isLoggedIn && (isLoginRequest || isLoginPage)) {
            // the admin is already logged in and he's trying to login again
            // then forwards to the admin's homepage
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/product-manager");
            dispatcher.forward(request, response);

        } else if (isLoggedIn || isLoginRequest) {
            // continues the filter chain
            // allows the request to reach the destination
            chain.doFilter(request, response);

        } else {
            // the admin is not logged in, so authentication is required
            // forwards to the Login page
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }

    }
    
    
    private boolean checkIsRememberMe(HttpServletRequest request, HttpServletResponse response, HttpSession session, boolean isLoggedIn) {
        Cookie[] cookies = request.getCookies();
        boolean isLogged = isLoggedIn;
        if (!isLogged && cookies != null) {
            String selector = "";
            String rawValidator = "";
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("selector")) {
                    selector = cookie.getValue();
                } else if (cookie.getName().equals("validator")) {
                    rawValidator = cookie.getValue();
                }
            }
            if (!"".equals(selector) && !"".equals(rawValidator)) {
                AccountAuthBLO accountAuthBLO = new AccountAuthBLO();
                AccountAuth token = accountAuthBLO.findBySelector(selector);

                if (token != null) {
                    try {
                        String hashedValidatorDatabase = token.getValidator();
                        String hashedValidatorCookie = HashGeneratorUtils.generateSHA256(rawValidator);

                        if (hashedValidatorCookie.equals(hashedValidatorDatabase)) {
                            session = request.getSession();
                            session.setAttribute("user", token.getAccountId());
                            isLogged = true;
                        }
                    } catch (HashGenerationException ex) {
                        Logger.getLogger(FontEndAuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return isLogged;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
    }

}
