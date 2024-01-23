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
@RequestMapping("/entr")
public class EntrepriseController {
    @Autowired
    EtrepriseService entreser;

    //ajouter une entreprise
    @PostMapping("/addentreprise")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT')")
    public ResponseEntity<Entreprisedto> addentreprise(@RequestBody Entreprisedto entr){        
        return new ResponseEntity<Entreprisedto>(entreser.addentreprise(entr),HttpStatus.OK);
    }

    //recuperer la liste des entreprises
    @GetMapping ("/getentreprise")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT')")
    public ResponseEntity<List<Entreprisedto>> getallentreprise(){
       return new ResponseEntity<List<Entreprisedto>>(entreser.getallentreprise(),HttpStatus.OK);
    }
    //suprimer une entreprise
    @DeleteMapping("/deleteentreprise/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT')")
    public ResponseEntity<Boolean> deleteentreprise(@PathVariable Long id){
        
        return new ResponseEntity<Boolean>(entreser.deleteEntreprise(id),HttpStatus.OK);
    }
    //update entreprise
    @PutMapping("/updateentreprise/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT')")
    public ResponseEntity<Entreprisedto> updateentreprise(@PathVariable Long id,@RequestBody Entreprisedto entreprise){
        System.out.println("==============================================");
        System.out.println("id : "+id);
        System.out.println("enterprise : "+entreprise);
        
        System.out.println("==============================================");
        return new ResponseEntity<Entreprisedto>(entreser.updateentreprise(id,entreprise),HttpStatus.OK);
    }

}
