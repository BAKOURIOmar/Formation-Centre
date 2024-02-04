package univ.iwa.controller;

import java.util.List;
import java.util.Optional;

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
import univ.iwa.model.Formation;
import univ.iwa.service.EmailService;
import univ.iwa.service.FormationService;
import univ.iwa.service.IndividuService;

@RestController
@RequestMapping("/indiv")
public class IndividuController {
	@Autowired
	IndividuService individuservice;
	@Autowired
	FormationService formationService;
	@Autowired
	EmailService emailService ;
	
	String subject;
	String body;
	
//	 //Ajouter un individu
	@PostMapping("/inscription/{formationId}")
    public Inndividualsdto inscription(@RequestBody Inndividualsdto individuDto,
                                    @PathVariable Long formationId) {
        Inndividualsdto result = individuservice.inscription(individuDto, formationId);
        Optional<Formation> optionalFormation = formationService.findById(formationId);
            Formation formation = optionalFormation.get();
            subject = "Inscription confirmée à " + formation.getName();
            body = "Bienvenue " + individuDto.getNom() + ",\n\nMerci de vous être inscrit à la formation " + formation.getName() + ". Nous vous contacterons dès que la formation débutera.\n\nCordialement,";
            emailService.sendSimpleEmail(individuDto.getEmail(),subject,body);
        return result;
    }
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
}
