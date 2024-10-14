package ma.projet.test;

import ma.projet.service.ProduitService;
import ma.projet.classes.Produit;
import ma.projet.classes.Categorie;
import java.util.Date;
import java.util.List;
import ma.projet.classes.Commande;
import ma.projet.classes.LigneCommande;
import ma.projet.service.CategorieService;
import ma.projet.service.CommandeService;
import ma.projet.service.LigneCommandeService;

public class TestProduitService {
    public static void main(String[] args) {
        ProduitService produitService = new ProduitService();
        CategorieService categorieService = new CategorieService();
        LigneCommandeService ligneCommandeService = new LigneCommandeService();
        CommandeService commandeService = new CommandeService();

        // Étape 1 : Créer et enregistrer les catégories
        Categorie c1 = new Categorie(1, "1111", "cat1");
        Categorie c2 = new Categorie(2, "1112", "cat2");
        categorieService.create(c1);
        categorieService.create(c2);

        // Étape 2 : Créer et enregistrer les produits
        Produit p1 = new Produit(1, 2000, "Ref123", c1);
        Produit p2 = new Produit(2, 3000, "Ref124", c1);
        Produit p3 = new Produit(3, 1500, "Ref125", c2);
        Produit p4 = new Produit(4, 1200, "Ref126", c2);
        produitService.create(p1);
        produitService.create(p2);
        produitService.create(p3);
        produitService.create(p4);

        // Afficher les produits par catégorie
        afficherProduitsParCategorie(produitService, c1);
        afficherProduitsParCategorie(produitService, c2);

        // Étape 3 : Créer et enregistrer les lignes de commande
        LigneCommande l1 = new LigneCommande(1, 3);  // Quantité : 3
        LigneCommande l2 = new LigneCommande(2, 5);  // Quantité : 5
        ligneCommandeService.create(l1);
        ligneCommandeService.create(l2);

        // Étape 4 : Créer et enregistrer les commandes
        Commande co1 = new Commande(1, new Date());
        Commande co2 = new Commande(2, new Date(System.currentTimeMillis() - 86400000)); // Date d'hier
        commandeService.create(co1);
        commandeService.create(co2);

        // Étape 5 : Tester la méthode getCommandesBetweenDates
        Date startDate = new Date(System.currentTimeMillis() - 172800000); // Deux jours avant
        Date endDate = new Date(); // Aujourd'hui
        List<Commande> commandes = commandeService.getCommandesBetweenDates(startDate, endDate);
        System.out.println("Commandes entre " + startDate + " et " + endDate + ":");
        for (Commande commande : commandes) {
            System.out.println("Commande ID: " + commande.getId() + ", Date: " + commande.getDate());
        }
        System.out.println("\nAffichage des produits pour la commande ID 1 :");
        commandeService.afficherProduitsDeCommande(2);

    }

    private static void afficherProduitsParCategorie(ProduitService produitService, Categorie categorie) {
        System.out.println("Produits de la catégorie " + categorie.getLibelle() + " :");
        List<Produit> produits = produitService.getByCategorie(categorie);
        if (produits.isEmpty()) {
            System.out.println("Aucun produit dans cette catégorie.");
        } else {
            for (Produit produit : produits) {
                System.out.println("Le produit " + produit.getReference() + " (ID: " + produit.getId() + ") est un produit de la catégorie " + categorie.getLibelle() + ", Prix: " + produit.getPrix());
            }
        }
        System.out.println(); // Pour une meilleure séparation visuelle entre les catégories
    }
   
    }
   
