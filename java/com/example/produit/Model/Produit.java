package com.example.produit.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Produit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private double prix;
    private int quantite;
    private String photo;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "CODE_CAT")
    private Categorie categorie;

    public Produit() {
        super();
    }
    public Produit(String nom,double prix,int quantite,String photo,Categorie categorie) {
        super();
        this.nom=nom;
        this.prix=prix;
        this.quantite=quantite;
        this.categorie=categorie;
        this.photo=photo;


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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() { return photo; }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }



}
