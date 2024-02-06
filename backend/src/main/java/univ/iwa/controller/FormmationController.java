package univ.iwa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/*import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;*/

import java.io.IOException;
import java.time.LocalDate;
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
		 @RequestParam("seuil") Long  seuil,
		 @RequestParam("cout") double cout,
		 @RequestParam("programme") String programme,
		 @RequestParam("ville") String ville,
		 @RequestParam("categorie") String categorie,
		 @RequestParam("date") LocalDate date
		 ) throws java.io.IOException {
  
         
         
         return ResponseEntity.ok(formservice.addFormation(picture,name,nombreh,seuil,cout,programme,ville,categorie,date));
   
 }
 //Afficher tous les formations
 @GetMapping("/getformation")
    public ResponseEntity<Page<Formationdto>> allformation(
    		@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size) throws java.io.IOException{
		int pageNumber = (page != null) ? page : 0;
		int pageSize = (size != null) ? size : Integer.MAX_VALUE;
		
     return new ResponseEntity<Page<Formationdto>>(formservice.getAllFormations(PageRequest.of(pageNumber, pageSize)),HttpStatus.OK);     
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
// @GetMapping("/getformationfiltre")
// public ResponseEntity<Page<Formationdto>> getFormations(@RequestParam(required = false)String Ville 
//		 ,@RequestBody (required = false)String categorie 
//		 ,@RequestParam (required = false)String name
//		 ,@RequestParam(name = "page", required = false) Integer page,
// 		@RequestParam(name = "size", required = false) Integer size) throws IOException{
//	 
//		int pageNumber = (page != null) ? page : 0;
//		int pageSize = (size != null) ? size : Integer.MAX_VALUE;
//	 
//		Page<Formationdto> formations=null;
//	 if(Ville!=null) {
//		 formations=formservice.getformtionville(Ville,PageRequest.of(pageNumber, pageSize));
//	 }else if(categorie !=null){
//		 formations=formservice.getformationCategorie(categorie,PageRequest.of(pageNumber, pageSize));
//		 
//	 }else if(name!=null) {
//		 formations=formservice.getFormationByName(name,PageRequest.of(pageNumber, pageSize));
//	 }else {
//		 formations=formservice.getAllFormations(PageRequest.of(pageNumber, pageSize));
//	 }
//	 if (formations != null && !formations.isEmpty()) {
//	        return ResponseEntity.ok(formations);
//	    } else {
//	        return ResponseEntity.notFound().build();
//	    }
// }
 
 //recuperer formation par id 
 @GetMapping("/getformationbyid/{id}")
 public ResponseEntity<Formationdto> recuperformaationid(@PathVariable long id){
	 Formationdto formation = formservice.getFormationByid(id);
	  return new ResponseEntity<>(formation, HttpStatus.OK);

 }
@PostMapping("/filtreSearch")
public ResponseEntity<Page<Formationdto>> filtreSearch(filtredto filters ,
		@RequestParam(name = "page", required = false) Integer page,
		@RequestParam(name = "size", required = false) Integer size) throws java.io.IOException {
 
	int pageNumber = (page != null) ? page : 0;
	int pageSize = (size != null) ? size : Integer.MAX_VALUE;
        
      return ResponseEntity.ok(formservice.filtreSearch(filters, PageRequest.of(pageNumber, pageSize)));
  


}
 
//recuperer les formatiion filter par ville categorie nam
@GetMapping("/getformations")
public ResponseEntity<Page<Formationdto>> filterforamtion(@RequestParam String searchkey,@RequestParam(name = "page", required = false) Integer page,
		@RequestParam(name = "size", required = false) Integer size) throws java.io.IOException{
			int pageNumber = (page != null) ? page : 0;
			int pageSize = (size != null) ? size : Integer.MAX_VALUE;
return ResponseEntity.ok(formservice.getforamtions(searchkey,PageRequest.of(pageNumber, pageSize))) ;

	
}
//recuperer les formation par nom
@GetMapping("/getFormationByName")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
public ResponseEntity<Page<Formationdto>>getformationByName(@RequestParam String name,@RequestParam(name = "page", required = false) Integer page,@RequestParam(name = "size", required = false) Integer size) throws java.io.IOException{
	int pageNumber = (page != null) ? page : 0;
	int pageSize = (size != null) ? size : Integer.MAX_VALUE;
	System.out.println("-------------------------------------------");
	System.out.println("name"+name);
	 return ResponseEntity.ok(formservice.getFormationByName(name, PageRequest.of(pageNumber, pageSize)));	
	
}
}