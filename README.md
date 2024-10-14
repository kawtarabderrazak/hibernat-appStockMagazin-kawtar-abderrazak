Description
Ce projet consiste à développer une application de gestion de stock pour un magasin spécialisé dans la vente de produits informatiques. L'application permet la gestion des produits, des catégories, des commandes, et des lignes de commande. Elle utilise Hibernate pour la persistance des données dans une base MySQL et propose une série d'opérations CRUD ainsi que des méthodes pour des requêtes spécifiques.

Fonctionnalités
L'application offre les fonctionnalités suivantes :

Gestion des produits : Ajouter, modifier, afficher, et supprimer des produits.
Gestion des catégories : Lier les produits à des catégories et afficher les produits par catégorie.
Gestion des commandes et des lignes de commande : Gérer les commandes de produits et consulter les produits dans une commande spécifique.
Filtrage des produits :
Afficher les produits dont le prix est supérieur à 100 DH.
Afficher les produits commandés entre deux dates données.
Prérequis
NetBeans IDE ou un autre IDE supportant Java.
JDK 1.8 ou supérieur.
MySQL pour la gestion de la base de données.
Hibernate-JPA pour la gestion de la persistance des entités.
Pilote JDBC MySQL pour la connexion avec la base de données.
Structure du Projet
A. Couche Persistance
Package ma.projet.classes :
Contient les classes entités représentant les tables de la base de données (Produit, Categorie, Commande, LigneCommande).
Package ma.projet.config :
Contient le fichier de configuration Hibernate hibernate.cfg.xml qui définit la connexion à la base de données et les paramètres d'Hibernate.
Package ma.projet.util :
Contient la classe utilitaire HibernateUtil pour la gestion de la session Hibernate via SessionFactory.
B. Couche Service
Package ma.projet.dao :

Contient l'interface générique IDao<T> qui définit les opérations de base (ajouter, modifier, supprimer, afficher).
Package ma.projet.service :

Contient les classes service : ProduitService, CategorieService, CommandeService, et LigneCommandeService qui implémentent les méthodes CRUD définies dans IDao.

Méthodes spécifiques dans ProduitService :

Afficher la liste des produits par catégorie.
Afficher la liste des produits commandés entre deux dates.
Afficher la liste des produits commandés dans une commande donnée (avec référence, prix, quantité).
Afficher la liste des produits dont le prix est supérieur à 100 DH via une requête nommée.
Tests à Exécuter
Création de produits, catégories, commandes et lignes de commande.
Affichage des produits par catégorie.
Affichage des produits commandés entre deux dates.
Affichage des produits dans une commande spécifique.

https://github.com/user-attachments/assets/90678a1a-f658-44ac-9c12-9ce2aea5f68e


