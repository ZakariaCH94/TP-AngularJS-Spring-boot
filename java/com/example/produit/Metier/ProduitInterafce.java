package com.example.produit.Metier;

import com.example.produit.Model.Categorie;
import com.example.produit.Model.Produit;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;


public interface ProduitInterafce {

    public Produit save_produit(Produit produit);
    public List<Produit> getAllProduit();
    public Page<Produit> getProduitbyName(String mc,int page,int size);
    public Page<Produit> getProduitbyCategorie(Long id,int page, int size);
    public Page<Produit> findAllNomProduit(int page, int size);
    public Optional<Produit> getProduitById(Long id);
    public Produit getProduitByName(String nom);
    public Categorie getCategorieById(Long id);






}
