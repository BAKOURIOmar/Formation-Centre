package univ.iwa.controller;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import univ.iwa.service.*;
import univ.iwa.dto.*;
@RestController
@RequestMapping("/entreprise")
public class EntrepriseController {
    @Autowired
    EtrepriseService entreser;

    //ajouter une entreprise
    @PostMapping("/addEntreprise")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT')")
    public ResponseEntity<Entreprisedto> addentreprise(@RequestBody Entreprisedto entr){        
        return new ResponseEntity<Entreprisedto>(entreser.addentreprise(entr),HttpStatus.OK);
    }

    //recuperer la liste des entreprises
    @GetMapping ("/getEntreprise")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT')")
    public ResponseEntity<List<Entreprisedto>> getallentreprise(){
       return new ResponseEntity<List<Entreprisedto>>(entreser.getallentreprise(),HttpStatus.OK);
    }
    //suprimer une entreprise
    @DeleteMapping("/deleteEntreprise/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT')")
    public ResponseEntity<Boolean> deleteentreprise(@PathVariable Long id){
        
        return new ResponseEntity<Boolean>(entreser.deleteEntreprise(id),HttpStatus.OK);
    }
    //update entreprise
    @PutMapping("/updateEntreprise/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT')")
    public ResponseEntity<Entreprisedto> updateentreprise(@PathVariable Long id,@RequestBody Entreprisedto entreprise){

        return new ResponseEntity<Entreprisedto>(entreser.updateentreprise(id,entreprise),HttpStatus.OK);
    }

}
