package univ.iwa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
 
 /*ajouter une formation
 @PostMapping("/addformation")
 @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
 public String adduser(@RequestBody Formationdto formation) {
	 formservice.addformation(formation);
	 return "formationadded succefully";
 }*/
 /*@PostMapping("/addformation")
 @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
 public ResponseEntity<String> addFormation(@RequestBody Formationdto formation,
                                         @RequestParam(value = "image", required = false) MultipartFile image) throws IOException, java.io.IOException {

         formservice.addformation(formation, image);
         return ResponseEntity.ok("Formation added successfully");
     
 }*/
 @PostMapping("/addformation")
 @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
 public ResponseEntity<String> addFormation(@RequestBody Formationdto formation,
                                            @RequestParam(value = "image", required = false) MultipartFile image) throws IllegalStateException, java.io.IOException {
     try {
         Formation addedFormation = formservice.addformation(formation, image);
         return ResponseEntity.status(HttpStatus.CREATED).body("Formation added successfully. ID: " + addedFormation.getId());
     } catch (IOException e) {
         e.printStackTrace(); // Log the exception for debugging purposes
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add formation with image.");
     }
 }


 //get the formation
 @GetMapping("/getformation")
    public List<Formationdto> allformation(){
     return formservice.getAllFormations();
 }

 //update les formation
 @PutMapping("/updateformation/{id}")
 @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
 public void updateformation(@PathVariable Long id,@RequestBody Formationdto form){
  formservice.updateformation(id,form);
 }
 //supprimer une formation
 @DeleteMapping("/deleteform/{id}")
 @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
 public String deleteFormation(@PathVariable Long id) {
  formservice.DeleteFormation(id);
  return "Formation deleted succesfuly";
 }

 //recuperer les formation par categorie
 @GetMapping("/getformationcat/{categorie}")
 @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSITANT')")
 public List<Formationdto> getformatiocate(@PathVariable String categorie){
  return  formservice.getformationcategorie(categorie);
 }
 //recuper les formation par ville
@GetMapping("/getbyville/{ville}")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSITANT')")
 public List<Formationdto> getbyville(@PathVariable String ville){
  return formservice.getformtionville(ville);
}


}
