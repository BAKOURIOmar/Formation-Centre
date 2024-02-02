package univ.iwa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import univ.iwa.dto.Inndividualsdto;
import univ.iwa.dto.Userdto;
import univ.iwa.model.Individuals;
import univ.iwa.service.FormationService;
import univ.iwa.service.IndividuService;

@RestController
@RequestMapping("/indiv")
public class IndividuController {
	@Autowired
    FormationService formationService;
	@Autowired
	IndividuService individuservice;
	
	 //Ajouter un individu
    @PostMapping("/addindividu")
    public ResponseEntity<Inndividualsdto> addindividu(@RequestBody Inndividualsdto indiv){
        return new ResponseEntity<Inndividualsdto>(individuservice.addindividu(indiv),HttpStatus.OK);
    }
    //Récuperer un individu
    @GetMapping ("/getallindividus")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSITANT')")
    public ResponseEntity<List<Inndividualsdto>>  getallindividus(){
       return new ResponseEntity<List<Inndividualsdto>>(individuservice.getallindividus(),HttpStatus.OK);
    }
    //Supprimer un individu
    @DeleteMapping("/deleteindividu/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSITANT')")
    public ResponseEntity<Boolean> deleteindividu(@PathVariable Long id){  
        return new ResponseEntity<Boolean>(individuservice.deleteindividu(id),HttpStatus.OK);
    }
    //Modifier l'individu
    @PutMapping("/updateIndividu/{id}")
    public ResponseEntity<Inndividualsdto> updateIndividu(@PathVariable Long id,@RequestBody Inndividualsdto individu){
        
        return new ResponseEntity<Inndividualsdto>(individuservice.updateIndividu(id,individu),HttpStatus.OK);

}
 // Ajouter un individu à une formation
    @PostMapping("/addIndividuToFormation/{individuId}/{formationId}")
    public ResponseEntity<Inndividualsdto> addIndividuToFormation(@PathVariable Long individuId, @PathVariable Long formationId) {
        Inndividualsdto result = individuservice.addIndividuToFormation(individuId, formationId);
        return ResponseEntity.ok(result);
    }

    // Supprimer un individu
    @DeleteMapping("/deleteIndividu/{id}")
    public ResponseEntity<Boolean> deleteIndividu(@PathVariable Long id) {
        boolean deleted = individuservice.deleteIndividu(id);
        return ResponseEntity.ok(deleted);
    }
    
    @GetMapping("/api/formation/{formationId}/individus")
    public List<Individuals> getIndividusInFormation(@PathVariable Long formationId) {
        return formationService.getIndividusInFormation(formationId);
    }
    
}
