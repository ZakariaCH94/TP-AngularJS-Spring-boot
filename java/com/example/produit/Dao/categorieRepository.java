package com.example.produit.Dao;

import com.example.produit.Model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource (path="categorie")
public interface categorieRepository extends JpaRepository<Categorie,Long> {
}
