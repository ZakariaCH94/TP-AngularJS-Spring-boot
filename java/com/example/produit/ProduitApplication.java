package com.example.produit;

import com.example.produit.Dao.categorieRepository;
import com.example.produit.Dao.produitRepository;
import com.example.produit.Model.Categorie;
import com.example.produit.Model.Produit;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;


@SpringBootApplication
public class ProduitApplication implements CommandLineRunner {


    @Autowired
    private produitRepository produitrepository;
    @Autowired
    private categorieRepository categorierepository;
    public static void main(String[] args)
    {
        SpringApplication.run(ProduitApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        categorierepository.save(new Categorie("Télévision"));
        categorierepository.save(new Categorie("Console de jeux"));
        categorierepository.save(new Categorie("micro onde"));


        Random rm=new Random();

        categorierepository.findAll().forEach(categorie -> {

            for (int i=0;i<10;i++) {
                produitrepository.save(new Produit(RandomString.make(20), 100 + rm.nextDouble(), rm.nextInt(10000),RandomString.make(10), categorie));
            }
        });



    }

}

