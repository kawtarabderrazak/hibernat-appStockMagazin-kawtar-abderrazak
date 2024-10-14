/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.classes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author PC
 */
@Table(name="commande")
@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;
  @OneToMany(mappedBy="commande")
       private List<LigneCommande> ligneCommandes = new ArrayList<>(); // Initialisez ici
   

    public Commande() {
        this.ligneCommandes = new ArrayList<>(); // Initialiser la liste ici
    }
  
  public Commande(Date date) {
        this.date = date;
    }

  
    

    public Commande(int id, Date date) {
        this.id = id;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }
 public List<LigneCommande> getLigneCommandes() {
        return ligneCommandes;
    }
    
    // Ajoutez des méthodes pour ajouter des lignes de commande
    public void addLigneCommande(LigneCommande ligneCommande) {
        ligneCommandes.add(ligneCommande);
        ligneCommande.setCommande(this); // Assurez-vous que la ligne de commande sait à quelle commande elle appartient
    }
   }
