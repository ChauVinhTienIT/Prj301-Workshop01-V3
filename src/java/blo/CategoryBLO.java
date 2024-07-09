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
import model.Categorie;

/**
 *
 * @author Lenovo
 */
public class CategoryBLO implements Accessible<Categorie>{

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
    public int insertRec(Categorie obj) {
        return this.persist(obj);
    }

    @Override
    public int updateRec(Categorie obj) {
        int result = 0;
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Categorie categorie = em.find(Categorie.class, obj.getTypeId());

            categorie.setCategoryName(obj.getCategoryName());
            categorie.setMemo(obj.getMemo());
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
    public int deleteRec(Categorie obj) {
        int result = 0;
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Categorie acCategorie = em.find(Categorie.class, obj.getTypeId());
            em.remove(acCategorie);
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
    public Categorie getObjectById(String idRaw) {
        int id = Integer.parseInt(idRaw);
        Categorie categorie = null;
        EntityManager em = emf.createEntityManager();
        try {
            categorie = em.find(Categorie.class, id);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
        } finally {
            em.close();
        }
        return categorie;
    }

    @Override
    public Categorie getObjectById(int id) {
        Categorie categorie = null;
        EntityManager em = emf.createEntityManager();
        try {
            categorie = em.find(Categorie.class, id);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
        } finally {
            em.close();
        }
        return categorie;
    }

    @Override
    public List<Categorie> listAll() {
        EntityManager em = emf.createEntityManager();
        String jpql = "Categorie.findAll";
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
    
}
