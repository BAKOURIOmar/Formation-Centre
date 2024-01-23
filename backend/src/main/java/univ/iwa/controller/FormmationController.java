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
    public ResponseEntity<List<Formationdto>> allformation() throws java.io.IOException{
     return new ResponseEntity<List<Formationdto>>(formservice.getAllFormations(),HttpStatus.OK);
     
 }

 //Modifier formation
 @PutMapping("/updateformation/{id}")
 @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
 public ResponseEntity<Formationdto> updateformation(
         @PathVariable Long id,
         @RequestPart("image") MultipartFile image,
         @ModelAttribute("form") Formationdto form) throws java.io.IOException {
	 Formationdto result = formservice.updateformation(id, form, image);
     System.out.print("Requête envoyée");
     return ResponseEntity.ok(result);
 }

 //Supprimer Formation
 @DeleteMapping("/deleteform/{id}")
 @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
 public ResponseEntity<Boolean> deleteFormation(@PathVariable Long id) {
  return new ResponseEntity<Boolean>(formservice.DeleteFormation(id),HttpStatus.OK);
 }

 //Récupere Formation par categorie
 @GetMapping("/getformationcat/{categorie}")
 @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT')")
 public ResponseEntity<List<Formationdto>> getformatiocate(@PathVariable String categorie){
  return  new ResponseEntity<List<Formationdto>>( formservice.getformationcategorie(categorie),HttpStatus.OK);
 }
 //Récupere Formation par ville
@GetMapping("/getbyville/{ville}")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT')")
 public ResponseEntity<List<Formationdto>> getbyville(@PathVariable String ville){
  return new ResponseEntity<List<Formationdto>>( formservice.getformtionville(ville),HttpStatus.OK);
}


}
