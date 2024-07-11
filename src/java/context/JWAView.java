/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package context;

/**
 *
 * @author Lenovo
 */
public interface JWAView {
    public String APP_CONTEXT = "/Prj301-Workshop01-V2";
    public String PRIVATE_FOLDER = "/admin";
    
    public String ERROR_JSP = "error.jsp";
    public String HEADER_JSP = "header.jspf";
    public String LOGIN_JSP = "login.jsp";
    public String HOME_JSP = "home.jsp";
    public String ITEM_JSP = "shopItem.jsp";
  
    public String ACCOUNT_MANAGER_JSP = String.format("%s/%s", PRIVATE_FOLDER, "accountManager.jsp");
    public String CATEGORY_MANAGER_JSP = String.format("%s/%s", PRIVATE_FOLDER, "categoryManager.jsp");
    public String PRODUCT_MANAGER_JSP = String.format("%s/%s", PRIVATE_FOLDER, "productManager.jsp");
    
    
}
