/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import blo.AccountBLO;
import blo.RoleBLO;
import java.util.List;
import model.Account;
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
        AccountBLO accountBLO = new AccountBLO();
        RoleBLO roleBLO = new RoleBLO();
        List<Account> result = accountBLO.listAll();
        
        System.out.println("Current Account List:");
        for (Account accounts : result) {
            System.out.println(String.format("%s, %s, %s",accounts.getAccountId(), accounts.getAccount(), accounts.getPass()));
        }
        
        System.out.println(accountBLO.getObjectByAccount("admin"));
//        Role newRole = roleBLO.getObjectById(1);
//        
//        Account newAcc = new Account();
//        newAcc.setAccount("staff1");
//        newAcc.setPass("123");
//        newAcc.setFirstName("Tien");
//        newAcc.setLastName("Chau Vinh");
//        accountBLO.insertRec(newAcc);
//        
//        newAcc.setPhone("0933935584");
//        newAcc.setRoleId(newRole);
//        accountBLO.updateRec(newAcc);
    }
}
