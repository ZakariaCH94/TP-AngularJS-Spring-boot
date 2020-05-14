package com.example.produit.Metier;

import com.example.produit.Dao.categorieRepository;
import com.example.produit.Dao.produitRepository;
import com.example.produit.Model.Categorie;
import com.example.produit.Model.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class implementsProduitInterface implements ProduitInterafce {

    @Autowired
    private produitRepository produitrepository;

    @Autowired
    private categorieRepository categorierepository;
    @Override
    public Produit save_produit(Produit produit) {
        return produitrepository.save(produit);
    }
    @Override
    public List<Produit> getAllProduit() {
        return produitrepository.findAll();
    }

   /* @Override
    public List<Produit> getProduitPagebyName(String mc) {
        return produitrepository.findByNomContains(mc);
    }
    */


    @Override
    public Page<Produit> getProduitbyName(String mc, int page, int size) {
        return produitrepository.findByNomContains(mc,PageRequest.of(page,size));
    }

    @Override
    public Page<Produit> getProduitbyCategorie(Long id,int page,int size) {
        return produitrepository.findByCategorie(id,PageRequest.of(page,size));
    }

    @Override
    public Page<Produit> findAllNomProduit(int page, int size) {
        return produitrepository.findAllNomProduit(PageRequest.of(page,size));

    }

    @Override
    public Optional<Produit> getProduitById(Long id) {
        return produitrepository.findById(id);
    }

    @Override
    public Produit getProduitByName(String nom) {
        return produitrepository.findByNom(nom);
    }

    @Override
    public Categorie getCategorieById(Long id) {

        Optional<Categorie> cat1 = categorierepository.findById(id);
        Categorie categorie = cat1.get();
        return categorie;
    }


}
