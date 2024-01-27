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

import java.io.IOException;

import java.nio.file.Path;
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
 public ResponseEntity<String> addFormation(@RequestParam Long id,
                                            @RequestParam String name,
                                            @RequestParam Long nombreh,
                                            @RequestParam double cout,
                                            @RequestParam String programme, @RequestParam String ville,
                                            @RequestParam String categorie, @RequestParam(required = false) MultipartFile imagePath) throws java.io.IOException {
     System.out.println("====================================================");
     System.out.println("id" + id);
     System.out.println("nom" + name);
     System.out.println("file" + imagePath);

     try {
         Formationdto formation = new Formationdto( id,name, nombreh, cout, categorie, programme, ville, "");
        // Formation forma = formservice.addFormation(formation);
  
   if (imagePath != null) {
             String pathphoto = "src/main/resources/static/photos/"+formation.getId()+".png";
             System.out.println("idimage"+formation.getId());
             imagePath.transferTo(Path.of(pathphoto));
             String url = "http://localhost:8080/photos/"+formation.getId()+".png";
             System.out.println("idimageurl"+formation.getId());
             formation.setImagePath(url);
             formservice.addFormation(formation);
         }

         return ResponseEntity.ok("Formation ajoutée avec succès");
     } catch (IOException e) {
         e.printStackTrace();
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                 .body("Échec de l'ajout de la formation avec l'image.");
     }
 }
 //Afficher tous les formations
 @GetMapping("/getformation")
 public ResponseEntity<List<Formationdto>> allformation() throws java.io.IOException {
     List<Formationdto> formations = formservice.getAllFormations();

     // Ajouter le chemin de l'image pour chaque formation
     for (Formationdto formation : formations) {
         String imagePath = "http://localhost:8080/photos/" + formation.getId() + ".png";
         formation.setImagePath(imagePath);
     }

     return new ResponseEntity<List<Formationdto>>(formations, HttpStatus.OK);
 }


    //Modifier formation
/* @PutMapping("/updateformation/{id}")
 @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
 public ResponseEntity<Formationdto> updateformation(
         @PathVariable Long id,
         @RequestPart("image") MultipartFile image,
         @ModelAttribute("form") Formationdto form) throws java.io.IOException {
	 Formationdto result = formservice.updateformation(id, form, image);
     System.out.print("Requête envoyée");
     return ResponseEntity.ok(result);
 }*/

 //Supprimer Formation
/* @DeleteMapping("/deleteform/{id}")
 @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
 public ResponseEntity<Boolean> deleteFormation(@PathVariable Long id) {
  return new ResponseEntity<Boolean>(formservice.DeleteFormation(id),HttpStatus.OK);
 }*/

 //Récupere Formation par categorie
/* @GetMapping("/getformationcat/{categorie}")
 @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT')")
 public ResponseEntity<List<Formationdto>> getformatiocate(@PathVariable String categorie){
  return  new ResponseEntity<List<Formationdto>>( formservice.getformationcategorie(categorie),HttpStatus.OK);
 }*/
 //Récupere Formation par ville
/*@GetMapping("/getbyville/{ville}")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT')")
 public ResponseEntity<List<Formationdto>> getbyville(@PathVariable String ville){
  return new ResponseEntity<List<Formationdto>>( formservice.getformtionville(ville),HttpStatus.OK);
}
*/
 
 //recuperer formation par id 
 @GetMapping("/getformationbyid/{id}")
 public ResponseEntity<Formationdto> recuperformaationid(@PathVariable long id){
	 Formationdto formation = formservice.getformationid(id);
	  return new ResponseEntity<>(formation, HttpStatus.OK);
 }

}
