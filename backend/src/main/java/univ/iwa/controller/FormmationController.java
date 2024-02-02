package univ.iwa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



/*import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;*/

import java.io.IOException;

import java.util.*;
//import java.util.zip.Deflater;

//import univ.iwa.model.Formation;
import univ.iwa.service.FormationService;
//import univ.iwa.util.Util;
import univ.iwa.dto.Formationdto;
import univ.iwa.dto.filtredto;
@RestController
@RequestMapping("/form")
public class FormmationController {
 @Autowired
 FormationService formservice;
 
 
 //Ajouter Formation
 @PostMapping("/addformation")
 @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
 public ResponseEntity<Formationdto> addFormation(@RequestParam("picture") MultipartFile picture,
		 @RequestParam("name") String name,
		 @RequestParam("nombreh") Long nombreh,
		 @RequestParam("cout") double cout,
		 @RequestParam("programme") String programme,
		 @RequestParam("ville") String ville,
		 @RequestParam("categorie") String categorie) throws java.io.IOException {
  
         
         
         return ResponseEntity.ok(formservice.addFormation(picture,name,nombreh,cout,programme,ville,categorie));
   
 }
 //Afficher tous les formations
 @GetMapping("/getformation")
    public ResponseEntity<List<Formationdto>> allformation() throws java.io.IOException{
     return new ResponseEntity<List<Formationdto>>(formservice.getAllFormations(),HttpStatus.OK);     
 }

// //Modifier formation
 @PutMapping("/updateformation/{id}")
 @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
 public ResponseEntity<Formationdto> updateformation(
         @PathVariable Long id,
         @RequestPart("picture") MultipartFile picture,
         @RequestPart("form") Formationdto form) throws java.io.IOException {
	 Formationdto result = formservice.updateformation(id, form, picture);
     System.out.print("Requête envoyée");
     return ResponseEntity.ok(result);
 }
//
// //Supprimer Formation
 @DeleteMapping("/deleteform/{id}")
 @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
 public ResponseEntity<Boolean> deleteFormation(@PathVariable Long id) {
  return new ResponseEntity<Boolean>(formservice.DeleteFormation(id),HttpStatus.OK);
 }
//
// //Récupere Formation par categorie
 /*@GetMapping("/getformationcat/{categorie}")
 @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT')")
 public ResponseEntity<List<Formationdto>> getformatiocate(@PathVariable String categorie){
  return  new ResponseEntity<List<Formationdto>>( formservice.getformationcategorie(categorie),HttpStatus.OK);
 }*/
 
// //Récupere Formation par ville
/*@GetMapping("/getbyville/{ville}")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT')")
 public ResponseEntity<List<Formationdto>> getbyville(@PathVariable String ville){
  return new ResponseEntity<List<Formationdto>>( formservice.getformtionville(ville),HttpStatus.OK);


 public ResponseEntity<List<Formationdto>> getbyville(@PathVariable String ville){
  return new ResponseEntity<List<Formationdto>>( formservice.getformtionville(ville),HttpStatus.OK);
}
*/

 
 //recuperer les formations par name,ville,catagorie
 @GetMapping("/getformationfiltre")
 public ResponseEntity<List<Formationdto>> getFormations(@RequestParam(required = false)String Ville ,@RequestBody (required = false)String categorie ,@RequestParam (required = false)String name) throws IOException{
	 List<Formationdto> formations=null;
	 if(Ville!=null) {
		 formations=formservice.getformtionville(Ville);
	 }else if(categorie !=null){
		 formations=formservice.getformationCategorie(categorie);
		 
	 }else if(name!=null) {
		 formations=formservice.getFormationByName(name);
	 }else {
		 formations=formservice.getAllFormations();
	 }
	 if (formations != null && !formations.isEmpty()) {
	        return ResponseEntity.ok(formations);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
 }
 
 //recuperer formation par id 
 @GetMapping("/getformationbyid/{id}")
 public ResponseEntity<Formationdto> recuperformaationid(@PathVariable long id){
	 Formationdto formation = formservice.getFormationByid(id);
	  return new ResponseEntity<>(formation, HttpStatus.OK);}


@PostMapping("/filtreSearch")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT')")
public ResponseEntity<Page<Formationdto>> filtreSearch(@RequestBody filtredto filters,
	                                                          @RequestParam(name = "page", required = false) Integer page,
	                                                          @RequestParam(name = "size", required = false) Integer size) throws IOException {
 
	int pageNumber = (page != null) ? page : 0;
	int pageSize = (size != null) ? size : Integer.MAX_VALUE;
        
      return ResponseEntity.ok(formservice.filtreSearch(filters, PageRequest.of(pageNumber, pageSize)));
  

}

//Ajouter un individu à une formation
@PostMapping("/addIndividuToFormation")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
public ResponseEntity<Void> addIndividuToFormation(@RequestParam Long individuId, @RequestParam Long formationId) {
    formservice.addIndividuToFormation(individuId, formationId);
    return ResponseEntity.ok().build();
}

// Compter le nombre d'individus inscrits dans une formation
@GetMapping("/countIndividusInFormation/{formationId}")
public ResponseEntity<Integer> countIndividusInFormation(@PathVariable Long formationId) {
    int count = formservice.countIndividusInFormation(formationId);
    return ResponseEntity.ok(count);
}

// Créer les groupes si nécessaire pour une formation
@PostMapping("/createGroupsIfNeeded/{formationId}/{seuil}")
public ResponseEntity<Void> createGroupsIfNeeded(@PathVariable Long formationId, @PathVariable int seuil) {
    formservice.createGroupsIfNeeded(formationId, seuil);
    return ResponseEntity.ok().build();
}


}