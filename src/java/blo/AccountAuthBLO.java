/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blo;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.AccountAuth;

/**
 *
 * @author Lenovo
 */
public class AccountAuthBLO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prj301-WorkShop01-V3PU");

    public int persist(AccountAuth object) {
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

    public AccountAuth findBySelector(String selector) {
        AccountAuth auth = null;
        EntityManager em = emf.createEntityManager();
        String jpql = "AccountAuth.findBySelector";
        try {
            Query query = em.createNamedQuery(jpql);
            query.setParameter("selector", selector);
            auth = (AccountAuth) query.getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
        } finally {
            em.close();
        }
        return auth;
    }

    public int insertRec(AccountAuth obj) {
        return this.persist(obj);
    }
    
    public int insertNewRec(AccountAuth obj){
        int result = 0;
        AccountAuth accountAuth = this.findByAccountId(obj.getAccountId().getAccountId());
        if(accountAuth != null){
            obj.setId(accountAuth.getId());
            this.updateRec(obj);
        }else{
            this.insertRec(obj);
        }
        return result;
    }

    public int updateRec(AccountAuth accountAuth) {
        int result = 0;
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            AccountAuth account = em.find(AccountAuth.class, accountAuth.getId());
            account.setSelector(accountAuth.getSelector());
            account.setValidator(accountAuth.getValidator());
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return result;
    }
    
    public AccountAuth findByAccountId(int accountId){
        AccountAuth auth = null;
        EntityManager em = emf.createEntityManager();
        String jpql = "AccountAuth.findByAccountId";
        try {
            Query query = em.createNamedQuery(jpql);
            query.setParameter("accountId", accountId);
            auth = (AccountAuth) query.getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
        } finally {
            em.close();
        }
        return auth;
    }
}
