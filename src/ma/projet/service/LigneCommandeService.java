/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;

import java.util.List;
import ma.projet.classes.LigneCommande;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author PC
 */
public class LigneCommandeService implements IDao<LigneCommande> {
    public boolean create(LigneCommande o) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(o);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }finally{
            session.close();
        }
        return false;
    }

    @Override
    public LigneCommande getById(int id) {
         Session session = null;
        LigneCommande e  = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            e = (LigneCommande) session.get(LigneCommande.class, id);
            session.getTransaction().commit();
            return e;
        } catch (HibernateException ex) {
            session.getTransaction().rollback();
        }finally{
            session.close();
        }
        return e;
    
    }

    @Override
    public List<LigneCommande> getAll() {
        Session session = null;
        List<LigneCommande>  LigneLigneCommande = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            LigneLigneCommande = session.createQuery("from LigneCommande").list();
            session.getTransaction().commit();
            return LigneLigneCommande;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }finally{
            session.close();
        }
        return LigneLigneCommande;
    }

 

 
   
    
}
