/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import blo.AccountAuthBLO;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
import javax.servlet.http.HttpSession;
import model.Account;
import model.AccountAuth;
import tools.HashGenerationException;
import tools.HashGeneratorUtils;

/**
 *
 * @author Lenovo
 */
public class FontEndAuthenticationFilter implements Filter {

    private HttpServletRequest httpRequest;

    private static final String[] loginRequiredURLs = {
        "/user-manager", "/cart-manager"
    };

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        if (path.startsWith("/admin/")) {
            //Admin's functionpage
            chain.doFilter(request, response);
            return;
        }

        boolean isLoggedIn = checkIsRememberMe(httpRequest, session, false);

        if (session != null && session.getAttribute("user") != null) {
            isLoggedIn = true;
        }

        String loginURI = httpRequest.getContextPath() + "/login";
        boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
        boolean isLoginPage = httpRequest.getRequestURI().endsWith("login.jsp");

        if (isLoggedIn && (isLoginRequest || isLoginPage)) {
            // the user is already logged in and he's trying to login again
            // then forward to the homepage
            httpRequest.getRequestDispatcher("/").forward(request, response);

        } else if (!isLoggedIn && isLoginRequired()) {
            // the user is not logged in, and the requested page requires
            // authentication, then forward to the login page
            String loginPage = "/login.jsp";
            RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginPage);
            dispatcher.forward(request, response);
        } else {
            // for other requested pages that do not require authentication
            // or the user is already logged in, continue to the destination
            chain.doFilter(request, response);
        }
    }

    private boolean checkIsRememberMe(HttpServletRequest request, HttpSession session, boolean isLoggedIn) {
        Cookie[] cookies = request.getCookies();
        boolean isLogged = isLoggedIn;
        if (!isLogged && cookies != null) {
            Map<String, String> tokens = new HashMap<>();
            String selector = "";
            String rawValidator = "";
            
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("selector")) {
                    selector = cookie.getValue();
                } else if (cookie.getName().equals("validator")) {
                    rawValidator = cookie.getValue();
                }
                tokens.put(selector, rawValidator);
            }

            for (Map.Entry<String, String> entry : tokens.entrySet()) {
                selector = entry.getKey();
                rawValidator = entry.getValue();
                if (!"".equals(selector) && !"".equals(rawValidator)) {
                    AccountAuthBLO accountAuthBLO = new AccountAuthBLO();
                    AccountAuth token = accountAuthBLO.findBySelector(selector);

                    if (token != null) {
                        try {
                            String hashedValidatorDatabase = token.getValidator();
                            String hashedValidatorCookie = HashGeneratorUtils.generateSHA256(rawValidator);

                            if (hashedValidatorCookie.equals(hashedValidatorDatabase)) {
                                session.setAttribute("user", token.getAccountId());
                                isLogged = true;
                            }
                        } catch (HashGenerationException ex) {
                            Logger.getLogger(FontEndAuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
        return isLogged;
    }

    private boolean isLoginRequired() {
        boolean result = false;
        String requestURL = httpRequest.getRequestURL().toString();
        for (String loginRequiredURL : loginRequiredURLs) {
            if (requestURL.contains(loginRequiredURL)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

}
