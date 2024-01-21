package univ.iwa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import univ.iwa.model.Formation;
import univ.iwa.service.FormationService;
import univ.iwa.dto.Formationdto;
@RestController
@RequestMapping("/form")
public class FormmationController {
 @Autowired FormationService formservice;
 
 //ajouter une formation
 @PostMapping("/addformation")
 @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
 public String adduser(@RequestBody Formationdto formation) {
	 formservice.addformation(formation);
	 return "formationadded succefully";
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
 @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT')")
 public List<Formationdto> getformatiocate(@PathVariable String categorie){
  return  formservice.getformationcategorie(categorie);
 }
 //recuper les formation par ville
@GetMapping("/getbyville/{ville}")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT')")
 public List<Formationdto> getbyville(@PathVariable String ville){
  return formservice.getformtionville(ville);
}

}
