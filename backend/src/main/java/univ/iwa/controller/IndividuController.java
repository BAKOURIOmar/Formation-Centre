package univ.iwa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import univ.iwa.service.IndividuService;

@RestController
@RequestMapping("/indiv")
public class IndividuController {
	@Autowired
	IndividuService individuservice;
	
	 //Ajouter un individu
    @PostMapping("/addindividu")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSITANT')")
    public String addindividu(@RequestBody Inndividualsdto indiv){
        individuservice.addindividu(indiv);
        return "added";
    }
    //Récuperer un individu
    @GetMapping ("/getallindividus")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSITANT')")
    public List<Inndividualsdto> getallindividus(){
       return individuservice.getallindividus();
    }
    //Supprimer un individu
    @DeleteMapping("/deleteindividu/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSITANT')")
    public String deleteindividu(@PathVariable Long id){
        individuservice.deleteindividu(id);
        return "deleted";
    }
    //Modifier l'individu
    @PutMapping("/updateIndividu/{id}")
    public String updateIndividu(@PathVariable Long id,@RequestBody Inndividualsdto individu){
        individuservice.updateIndividu(id,individu);
        return "updated";

}
}
