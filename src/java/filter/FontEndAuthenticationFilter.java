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
import org.apache.commons.lang3.RandomStringUtils;
import tools.HashGenerationException;
import tools.HashGeneratorUtils;

/**
 *
 * @author Lenovo
 */
public class FontEndAuthenticationFilter implements Filter {

    private HttpServletRequest httpRequest;

    private static final String[] loginRequiredURLs = {
        "/user-manager", "/edit_profile", "/update_profile"
    };

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        if (path.startsWith("/admin/")) {
            //Admin's functionpage
            chain.doFilter(request, response);
            return;
        }

        boolean isLoggedIn = checkIsRememberMe(httpRequest, httpResponse, session, false);

        if (session != null && session.getAttribute("user") != null) {
            Account user = (Account) session.getAttribute("user");
            //Role = 3 : customer
            isLoggedIn = user.getRoleId().getRoleId() == 3;
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
