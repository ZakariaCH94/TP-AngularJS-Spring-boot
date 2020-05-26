package com.example.produit.Web;

import com.example.produit.Metier.implementsProduitInterface;
import com.example.produit.Model.Categorie;
import com.example.produit.Model.Produit;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.minidev.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Optional;



@RestController
@CrossOrigin("*")

public class ApiRestProduit {
	
	

    @Autowired
    private implementsProduitInterface produitImp;

    @Value("${dir.imagesprod}")
    private String imageDirprod;
 

    @RequestMapping(value = "/produits", method = RequestMethod.GET)
    public ArrayList<Object> getAllproduitbyMc(
            @RequestParam(name = "mc", defaultValue = "") String mc,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) throws IOException {
    	
    	Page<Produit> produits = produitImp.getProduitbyName( mc , page, size);
    	
      ArrayList<Object> list=new ArrayList<Object>();
        
        
     JSONObject jo = new JSONObject();
            
     for( Produit produit : produits ) {
        	
         jo.put("produit",produit);
         jo.put("data", "aaa");
         
         list.add(jo);
       		
        }
           
       
       return list;
    }

    @RequestMapping(value = "/produit/{id}", method = RequestMethod.GET)
    public Optional<Produit> getAllproduitbyMc(@PathVariable Long id) {
        return produitImp.getProduitById(id);
    }

    @RequestMapping(value = "/produitsCat/{id}", method = RequestMethod.GET)
    public Page<Produit> getAllproduitbyMc(
            @PathVariable Long id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {
        
    	return produitImp.getProduitbyCategorie(id , page, size);
    	
    	 
    }

    @RequestMapping(value = "/produitsName", method = RequestMethod.GET)
    public Page<Produit> getAllNameproduits(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {
        return produitImp.findAllNomProduit(page, size);
    }

    @RequestMapping(value = "/saveProduit", method = RequestMethod.POST)
    public Produit saveProduit(  
    		@RequestHeader("headers") HttpHeaders  headers,
    		@RequestParam("produit") String objectString) throws IOException{
    	
    	System.out.println(headers.get("Content-Type"));
    	
    	Produit prod = new ObjectMapper().readValue(objectString, Produit.class);
 
      
        return produitImp.save_produit(prod);

    }

    @PostMapping(value = "/saveProduct")
    public HashMap<String, Object> saveProduct(@RequestHeader("headers") HttpHeaders  headers,
    						   @RequestParam("produit") String objectString,
                               @RequestParam(name = "categorie") Long cat,
                               @RequestParam (name="image",required = false) MultipartFile file) throws IOException {

    	
        HashMap<String, Object> rtn = new LinkedHashMap<String, Object>();
        
    	Produit prod = new ObjectMapper().readValue(objectString, Produit.class);
    	System.out.println(headers.get("Content-Type"));
    	String message=""; 
        if (!file.isEmpty()) {
        	 
            Produit produit = produitImp.getProduitByName(prod.getNom());

            if (produit == null) {

                prod.setPhoto(file.getOriginalFilename());
                Categorie categorie = produitImp.getCategorieById(cat);
                prod.setCategorie(categorie);
                produitImp.save_produit(prod);
                file.transferTo(new File(imageDirprod + file.getOriginalFilename()));
                //Files.write(Paths.get(System.getProperty(imageDirprod)+prod.getId()),file.getBytes());

                message="succes";
            } else {
 
                message="produit existe !";
            } 
        }
        
        
        
         rtn.put("reponse", message);       
         return rtn; 
 
        
    }
    
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<String> uploadFile( @RequestParam (name="photo") MultipartFile file) throws IOException {

     
        file.transferTo(new File(imageDirprod + file.getOriginalFilename()));

    	
    	return new ResponseEntity<String>("succes",HttpStatus.OK);
    }
}
