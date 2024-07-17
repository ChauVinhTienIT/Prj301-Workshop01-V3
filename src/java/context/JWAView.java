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
    public String APP_CONTEXT = "/SE183243_ChauVinhTien_WS2";
    public String ADMIN_PAGE = "/admin";
    
    public String LOGIN_JSP = "login.jsp";
    public String HOME_JSP = "home.jsp";
    public String ITEM_JSP = "product-single.jsp";
    public String CART_JSP = "viewCart.jsp";
  
    public String ACCOUNT_MANAGER_JSP = String.format("%s/%s", ADMIN_PAGE, "accountManager.jsp");
    public String CATEGORY_MANAGER_JSP = String.format("%s/%s", ADMIN_PAGE, "categoryManager.jsp");
    public String PRODUCT_MANAGER_JSP = String.format("%s/%s", ADMIN_PAGE, "productManager.jsp");
    
    
    
    public String ACCOUNT_MANAGER_SERVLET = String.format("%s/%s", ADMIN_PAGE, "account-manager");
    public String PRODUCT_MANAGER_SERVLET = String.format("%s/%s", ADMIN_PAGE, "product-manager");
    public String CATEGORY_MANAGER_SERVLET = String.format("%s/%s", ADMIN_PAGE, "catregory-manager");
    
    public String CART_MANAGER_SERVLET = "cart-manager";
    
    public String HOME_PAGE_SERVLET = "home";
}
