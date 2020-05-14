package com.example.produit.Web;

import com.example.produit.Metier.implementsProduitInterface;
import com.example.produit.Model.Categorie;
import com.example.produit.Model.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;


@RestController
@CrossOrigin("*")
public class ApiRestProduit {

    @Autowired
    private implementsProduitInterface produitImp;

    @Value("${dir.imagesprod}")
    private String imageDirprod;


    @RequestMapping(value = "/produits", method = RequestMethod.GET)
    public Page<Produit> getAllproduitbyMc(
            @RequestParam(name = "mc", defaultValue = "") String mc,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {
        return produitImp.getProduitbyName( mc , page, size);
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
    public Produit saveProduit(@RequestBody Produit prod) {

        Produit p=new Produit();
        p.setNom(prod.getNom());
        p.setPrix(prod.getPrix());
        p.setQuantite(prod.getQuantite());
        return produitImp.save_produit(p);

    }

    @RequestMapping(value = "/saveProduct", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String saveProduct( @RequestParam (name="photo") MultipartFile file,
                               @RequestBody Produit prod,
                               @RequestParam(name = "categorie") Long cat) throws IOException {

        String message="";
        if (!file.isEmpty()) {

            Produit produit = produitImp.getProduitByName(prod.getNom());

            if (produit == null) {

                prod.setPhoto(file.getOriginalFilename());
                Categorie categorie = produitImp.getCategorieById(cat);
                prod.setCategorie(categorie);
                produitImp.save_produit(prod);
                
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            	Path path = Paths.get(imageDirprod + fileName);
            	try {
            		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            	} catch (IOException e) {
            		e.printStackTrace();
            	}
               //file.transferTo(new File(imageDirprod + prod.getId()));
                //Files.write(Paths.get(System.getProperty(imageDirprod)+prod.getId()),file.getBytes());

                message="succes";
            } else {

                message="failed";
            }
        }

        return message;
    }
    
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<String> uploadFile( @RequestParam (name="photo") MultipartFile file) {

     
    	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    	Path path = Paths.get(imageDirprod + fileName);
    	try {
    		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	return ResponseEntity.ok("succes");
    }
}


