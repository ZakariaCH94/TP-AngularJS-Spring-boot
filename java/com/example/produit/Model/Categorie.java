package com.example.produit.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Categorie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @OneToMany(mappedBy = "categorie", fetch = FetchType.LAZY)
    private Collection<Produit> produits;

    public Categorie() {
        super();
    }
    public Categorie(String nom) {
        super();
        this.nom=nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Collection<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Collection<Produit> produits) {
        this.produits = produits;
    }
}
