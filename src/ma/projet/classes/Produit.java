/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author PC
 */
@Entity
@Table(name="produit")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column (name = "reference")
    private String reference;
    private float prix;
     @ManyToOne
    @JoinColumn(name="categorie_id")
    private Categorie categorie;

    // Constructeur avec tous les champs
    public Produit(int id, String reference, float prix) {
        this.id = id;
        this.reference = reference;
        this.prix = prix;
    }

    public Produit() {
    }
    
    
    // Constructeur sans ID (pour les nouveaux objets avant génération de l'ID)
    public Produit(String reference, float prix) {
        this.reference = reference;
        this.prix = prix;
    }
    
    
    
    public Produit(int id, double prix, String reference, Categorie categorie) {
        this.id = id;
        this.prix =  (float) prix;
        this.reference = reference;
        this.categorie = categorie; // Initialisation de la catégorie
    }
    
 
    // Constructeur corrigé
    public Produit(int id, float prix, String reference) {
        this.id = id;
        this.prix = prix;
        this.reference = reference;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public String getReference() {
        return reference;
    }

    public float getPrix() {
        return prix;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
}
