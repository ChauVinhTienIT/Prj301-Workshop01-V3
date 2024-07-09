/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Account;

/**
 *
 * @author Lenovo
 */
public class AccountBLO implements Accessible<Account> {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prj301-WorkShop01-V3PU");

    public int persist(Object object) {
        int result = 0;
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
            result = 1;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return result;
    }

    @Override
    public List<Account> listAll() {
        EntityManager em = emf.createEntityManager();
        String jpql = "Account.findAll";
        List result = null;
        try{
            Query query = em.createNamedQuery(jpql);
            result = query.getResultList();
        }catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
        } finally {
            em.close();
        }
        return result;
    }

    @Override
    public int insertRec(Account obj) {
        return this.persist(obj);
    }

    @Override
    public int updateRec(Account obj) {
        int result = 0;
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Account account = em.find(Account.class, obj.getAccountId());

            account.setFirstName(obj.getFirstName());
            account.setLastName(obj.getLastName());
            account.setPhone(obj.getPhone());
            account.setGender(obj.getGender());
            account.setRoleId(obj.getRoleId());
            account.setIsUse(obj.getIsUse());
            account.setBirthday(obj.getBirthday());
            em.getTransaction().commit();
            result = 1;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return result;
    }

    @Override
    public int deleteRec(Account obj) {
        int result = 0;
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Account account = em.find(Account.class, obj.getAccountId());
            em.remove(account);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return result;
    }
    
    public int deleteRec(int id){
        int result = 0;
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Account account = em.find(Account.class, id);
            em.remove(account);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return result;
    }
    
    public int updateIsUse(int id){
        int result = 0;
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Account account = em.find(Account.class, id);
            account.setIsUse(!account.getIsUse());
            
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return result;
    }

    @Override
    public Account getObjectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Account getObjectById(int id) {
        Account account = null;
        EntityManager em = emf.createEntityManager();
        try {
            account = em.find(Account.class, id);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
        } finally {
            em.close();
        }
        return account;
    }
    
    public Account getObjectByAccount(String account){
        Account acc = null;
        EntityManager em = emf.createEntityManager();
        String jpql = "Account.findByAccount";
        try {
            Query query = em.createNamedQuery(jpql);
            query.setParameter("account", account);
            acc = (Account)query.getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
        } finally {
            em.close();
        }
        return acc;
    }
    
    public Account checkLogin(String userName, String password){
        Account user = null;
        EntityManager em = emf.createEntityManager();
        String jppl = "Account.checkLogin";
        try {
            Query query = em.createNamedQuery(jppl);
            query.setParameter("account", userName);
            query.setParameter("pass", password);
            user = (Account)query.getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
        } finally {
            em.close();
        }
        return user;
    }
}
