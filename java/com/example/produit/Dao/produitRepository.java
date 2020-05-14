package com.example.produit.Dao;

import com.example.produit.Model.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface produitRepository extends JpaRepository<Produit,Long> {
   // public List<Produit> findByNomContains(@Param("mc") String mc);

    public Page<Produit> findByNomContains(@Param("mc") String mc, Pageable pageable);
    @Query("select p from Produit p where p.categorie.id =:x")
    public Page<Produit> findByCategorie(@Param("x")Long id,Pageable pageable);

    @Query("select p.nom from Produit p")
    public Page<Produit> findAllNomProduit(Pageable pageable);

    public Produit findByNom(String nom);

}
