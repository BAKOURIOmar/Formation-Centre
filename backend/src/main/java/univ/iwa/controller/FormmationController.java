package univ.iwa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import univ.iwa.model.Formation;
import univ.iwa.service.FormationService;

@RestController
@RequestMapping("/form")
public class FormmationController {
 @Autowired FormationService formservice;
 
 //ajouter une formation
 @PostMapping("/addformation")
 @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
 public String adduser(@RequestBody Formation formation) {
	 formservice.addformation(formation);
	 return "formationadded succefully";
 }

 @GetMapping("/getformation")
    public List<Formation> allformation(){
     return formservice.getAllFormations();
 }
 @PutMapping("/updateformation/{id}")
 @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
 public void updateformation(@PathVariable Long id,@RequestBody Formation form){
  formservice.updateformation(id,form);
 }
 @DeleteMapping("/deleteform/{id}")
 @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
 public String deleteFormation(@PathVariable Long id) {
  formservice.DeleteFormation(id);
  return "Formation deleted succesfuly";
 }

 @GetMapping("/getformationcat/{categorie}")
 @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSITANT')")
 public List<Formation> getformatiocate(@PathVariable String categorie){
  return  formservice.getformationcategorie(categorie);
 }
@GetMapping("/getbyville/{ville}")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSITANT')")
 public List<Formation> getbyville(@PathVariable String ville){
  return formservice.getformtionville(ville);
}
}
