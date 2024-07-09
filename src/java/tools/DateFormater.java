/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class DateFormater {
    public static java.util.Date stringToJUDate( String dateRaw){
        java.util.Date date = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            date = format.parse(dateRaw);
        } catch (ParseException ex) {
            Logger.getLogger(DateFormater.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }
    
    public static String juDateToString(java.util.Date date){
        String dateRaw = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        dateRaw = format.format(date);
        return dateRaw;
    }
}
