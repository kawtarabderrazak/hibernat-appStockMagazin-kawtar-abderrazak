package ma.projet.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ma.projet.classes.Commande;
import ma.projet.classes.LigneCommande;
import ma.projet.classes.Produit;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * CommandeService - Service class for managing Commande entities
 */
public class CommandeService implements IDao<Commande> {

    @Override
    public boolean create(Commande o) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(o);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return false;
    }

    @Override
    public Commande getById(int id) {
        Session session = null;
        Commande commande = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            commande = (Commande) session.get(Commande.class, id);
            session.getTransaction().commit();
            return commande;
        } catch (HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return commande;
    }

     @Override
    public List<Commande> getAll() {
        Session session = null;
        List<Commande>  Commandes = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Commandes = session.createQuery("from Commande").list();
            session.getTransaction().commit();
            return Commandes;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }finally{
            session.close();
        }
        return Commandes;
    }

    /**
     * Méthode pour obtenir toutes les commandes entre deux dates sans utiliser de Session ou Query
     * 
     * @param startDate la date de début
     * @param endDate la date de fin
     * @return liste des commandes entre les deux dates
     */
    public List<Commande> getCommandesBetweenDates(Date startDate, Date endDate) {
        List<Commande> commandesEntreDates = new ArrayList<>();
        List<Commande> allCommandes = getAll(); // Récupère toutes les commandes

        // Filtrer les commandes entre les deux dates
        for (Commande commande : allCommandes) {
            if (commande.getDate().after(startDate) && commande.getDate().before(endDate)) {
                commandesEntreDates.add(commande);
            }
        }
        return commandesEntreDates;
    }

    /**
     * Méthode pour afficher les produits commandés entre deux dates
     * 
     * @param startDate la date de début
     * @param endDate la date de fin
     */
    public void afficherProduitsCommandesEntreDates(Date startDate, Date endDate) {
        List<Commande> commandes = getCommandesBetweenDates(startDate, endDate);
        if (commandes.isEmpty()) {
            System.out.println("Aucune commande trouvée entre les dates spécifiées.");
            return;
        }

        System.out.println("Produits commandés entre " + startDate + " et " + endDate + ":");
        for (Commande commande : commandes) {
            for (LigneCommande ligne : commande.getLigneCommandes()) {
                // Supposant que LigneCommande a une méthode getProduit() et getQuantite()
                Produit produit = ligne.getProduit(); // Remplacez par votre méthode pour obtenir le produit
                System.out.println("Produit: " + produit.getReference() + ", Quantité: " + ligne.getQuantite());
            }
        }
        
    }
    
    
   public void afficherProduitsDeCommande(int commandeId) {
    Session session = null;
    try {
        session = HibernateUtil.getSessionFactory().openSession();
        Commande commande = (Commande) session.get(Commande.class, commandeId);
        
        if (commande == null) {
            System.out.println("Commande introuvable pour l'ID : " + commandeId);
            return;
        }

        // Initialiser la collection ligneCommandes
        Hibernate.initialize(commande.getLigneCommandes());

        // Afficher les informations de la commande
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        String dateStr = dateFormat.format(commande.getDate());
        System.out.println("Commande : " + commande.getId() + " Date : " + dateStr);
        System.out.println("Liste des produits :");
        System.out.println("Référence\tPrix\tQuantité");

        List<LigneCommande> ligneCommandes = commande.getLigneCommandes();
        if (ligneCommandes == null || ligneCommandes.isEmpty()) {
            System.out.println("Aucun produit trouvé dans cette commande.");
            return;
        }

        for (LigneCommande ligne : ligneCommandes) {
            Produit produit = ligne.getProduit();
            String reference = produit.getReference();
            double prix = produit.getPrix();
            int quantite = ligne.getQuantite();
            System.out.println(reference + "\t" + prix + " DH\t" + quantite);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (session != null) {
            session.close();
        }
    }
}


}
