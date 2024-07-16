/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import blo.AccountBLO;
import blo.ProductBLO;
import blo.RoleBLO;
import blo.CategoryBLO;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import model.Account;
import model.Categorie;
import model.Product;
import model.Role;

/**
 *
 * @author Lenovo
 */
public class Test{
    public static void main(String[] args) {
        testAccount();
    }
    
    private static void testAccount(){
        System.out.println(formatPrice(1000));
    }
    
    private static String formatPrice(int price) {
        Locale locale = new Locale("vi", "VND");
        Currency currency = Currency.getInstance("VND");
        DecimalFormatSymbols df = DecimalFormatSymbols.getInstance(locale);
        df.setCurrency(currency);
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        numberFormat.setCurrency(currency);
        
        System.out.println(numberFormat.format(price));
        return numberFormat.format(price);
    }
}
