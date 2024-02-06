package univ.iwa.controller;

import lombok.SneakyThrows;

import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import univ.iwa.dto.Formationplanifierdto;
import univ.iwa.dto.Userdto;
import univ.iwa.model.Formation;
import univ.iwa.model.Groupe;
import univ.iwa.model.Individuals;
import univ.iwa.model.UserInfo;
import univ.iwa.service.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
@RestController
@RequestMapping("/plan")
public class FormationPlanificationController {
    @Autowired
    PlanificationService planserv;
    @Autowired
    GroupeService  groupeService ;
    @Autowired EmailService emailService ;
    //ajouter une planification poour une formation
    @PostMapping("/addplanification")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT')")
    public ResponseEntity<Formationplanifierdto> addplanification(@RequestBody Formationplanifierdto formpla) throws ParseException {   
        return new ResponseEntity<Formationplanifierdto>(planserv.addplanification(formpla),HttpStatus.OK);
    }
    
    //recuperer tout les formation planifiee et faire le filtrage des donnes
    @GetMapping("/getPlanifications")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT')")
    public ResponseEntity<List<Formationplanifierdto>> getallplanification() throws ParseException {
      return new ResponseEntity<List<Formationplanifierdto>>(planserv.getPlanifications(),HttpStatus.OK);

    }
    
    @GetMapping("/getFormateurPlanifications/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_FORMATEUR')")
    public ResponseEntity<List<Formationplanifierdto>> getPlanificationsByFormateurId(@PathVariable Integer id) throws ParseException {
      return new ResponseEntity<List<Formationplanifierdto>>(planserv.getPlanificationsByFormateurId(id),HttpStatus.OK);

    }


    //update la planification d une formation
    @PutMapping("/updatepan/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT')")
    public ResponseEntity<Formationplanifierdto> modifierplanification(@PathVariable long id,@RequestBody Formationplanifierdto formationplan){
		return new ResponseEntity<Formationplanifierdto>(planserv.updateplanification(id,formationplan),HttpStatus.OK);
    }

    @DeleteMapping("/deleteplan/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT')")
    public ResponseEntity<Boolean> deleteplanification(@PathVariable long id){
        
        return new ResponseEntity<Boolean>(planserv.supprimerplanification(id),HttpStatus.OK);
    }

    @GetMapping("/send-feedback-request/{groupId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT')")
    public void sendFeedbacakRequest(@PathVariable Long groupId) throws Exception{
        Groupe groupe = groupeService.getGroupById(groupId);

        for (Individuals individual : groupe.getInscrits()){
        	if(groupe.getFormateur()!=null && groupe.getFormation() !=null) {
            UserInfo  formateur = groupe.getFormateur();
            Formation formation = groupe.getFormation();
            Long individualId = individual.getId();
            String url = "http://localhost:4200/#/feedback?individualId="+individual.getId()+"&formateurId="+formateur.getId();

            String Subject = "Please let a feedback On your trainer ";
            String Body = "Hi "+individual.getNom()+" ,\n"
                    +"we hope this email finds you well, please let we know what you thought about your latest formation in our center about "+formation.getName()+
                    "Organized by "+formateur.getName()+"\n"
                    +"plese take a minute to give us your feedback using the link below,that may help us to improve the quality of our formations \n"
                    +"         "+url+"\n"
                    +"Best regard\n";
            String emailto = individual.getEmail();

            emailService.sendSimpleEmail(emailto,Subject,Body);
        }else {
        	throw new Exception("le groupe est sans formateur ");
        }
        
        }
    }


}
