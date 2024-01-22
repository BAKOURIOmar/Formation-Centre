package univ.iwa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.io.IOException;

import java.util.*;
import univ.iwa.model.Formation;
import univ.iwa.service.FormationService;
import univ.iwa.dto.Formationdto;
@RestController
@RequestMapping("/form")
public class FormmationController {
 @Autowired
 FormationService formservice;
 
 //Ajouter Formation
 @PostMapping("/addformation")
 @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
 public ResponseEntity<String> addFormation(@ModelAttribute Formationdto formationdto) throws java.io.IOException {
     try {
         formservice.addFormation(formationdto);
         System.out.print("Requête envoyée");
         return ResponseEntity.ok("Formation ajoutée avec succès");
     } catch (IOException e) {
         e.printStackTrace(); // Enregistrez l'exception à des fins de débogage
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'ajout de la formation avec l'image.");
     }
 }

 //Afficher tous les formations
 @GetMapping("/getformation")
    public List<Formationdto> allformation() throws java.io.IOException{
     return formservice.getAllFormations();
 }

 //Modifier formation
 @PutMapping("/updateformation/{id}")
 @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
 public ResponseEntity<String> updateformation(
         @PathVariable Long id,
         @RequestPart("image") MultipartFile image,
         @ModelAttribute("form") Formationdto form) throws java.io.IOException {
     String result = formservice.updateformation(id, form, image);
     System.out.print("Requête envoyée");
     return ResponseEntity.ok(result);
 }

 //Supprimer Formation
 @DeleteMapping("/deleteform/{id}")
 @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
 public String deleteFormation(@PathVariable Long id) {
  formservice.DeleteFormation(id);
  return "Formation deleted succesfuly";
 }

 //Récupere Formation par categorie
 @GetMapping("/getformationcat/{categorie}")
 @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSITANT')")
 public List<Formationdto> getformatiocate(@PathVariable String categorie){
  return  formservice.getformationcategorie(categorie);
 }
 //Récupere Formation par ville
@GetMapping("/getbyville/{ville}")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSITANT')")
 public List<Formationdto> getbyville(@PathVariable String ville){
  return formservice.getformtionville(ville);
}


}
