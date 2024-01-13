package univ.iwa.controller;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSITANT')")
    public String addentreprise(@RequestBody Entreprisedto entr){
        entreser.addentreprise(entr);
        return "added";
    }

    //recuperer la liste des entreprises
    @GetMapping ("/getentreprise")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSITANT')")
    public List<Entreprisedto> getallentreprise(){
       return entreser.getallentreprise();
    }
    //suprimer une entreprise
    @DeleteMapping("/deleteentreprise/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSITANT')")
    public String deleteentreprise(@PathVariable Long id){
        entreser.deleteentreprisse(id);
        return "deleted";
    }
    //update entreprise
    @PutMapping("/updateentreprise/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSITANT')")
    public String updateentreprise(@PathVariable Long id,@RequestBody Entreprisedto entreprise){
        entreser.updateentreprise(id,entreprise);
        return "updated";
    }

}
