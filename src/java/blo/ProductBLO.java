package blo;


import blo.Accessible;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Categorie;
import model.Product;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lenovo
 */
public class ProductBLO implements Accessible<Product>{

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
    public int insertRec(Product obj) {
        return this.persist(obj);
    }

    @Override
    public int updateRec(Product obj) {
        int result = 0;
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Product product = em.find(Product.class, obj.getProductId());
            product.setProductName(obj.getProductName());
            product.setProductImage(obj.getProductImage());
            product.setBrief(obj.getBrief());
            product.setTypeId(obj.getTypeId());
            product.setPrice(obj.getPrice());
            product.setDiscount(obj.getDiscount());
            product.setAccountId(obj.getAccountId());
            product.setPostedDate(obj.getPostedDate());
            product.setUnit(obj.getUnit());
            
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
    public int deleteRec(Product obj) {
        int result = 0;
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Product product = em.find(Product.class, obj.getProductId());
            em.remove(product);
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
    public Product getObjectById(String id) {
        Product product = null;
        EntityManager em = emf.createEntityManager();
        try {
            product = em.find(Product.class, id);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
        } finally {
            em.close();
        }
        return product;
    }

    @Override
    public Product getObjectById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> listAll() {
        EntityManager em = emf.createEntityManager();
        String jpql = "Product.findAll";
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
    
    public List<Product> listByCategory(Categorie category){
        EntityManager em = emf.createEntityManager();
        String jpql = "Product.findByCategorie";
        List result = null;
        try{
            Query query = em.createNamedQuery(jpql);
            query.setParameter("typeId", category);
            result = query.getResultList();
        }catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
        } finally {
            em.close();
        }
        return result;
    }
    
    public List<Product> listIfDiscount(){
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT p FROM Product p WHERE p.discount != 0 ";
        List result = null;
        try{
            Query query = em.createQuery(jpql);
            result = query.getResultList();
        }catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
        } finally {
            em.close();
        }
        return result;
    }
    
}
