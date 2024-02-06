package univ.iwa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import univ.iwa.dto.Formationdto;
import univ.iwa.repository.FormationReposetory;
import univ.iwa.repository.PlanificationReposertory;
import univ.iwa.dto.Formationplanifierdto;
import univ.iwa.dto.Inndividualsdto;
import univ.iwa.model.Formationplanifier;
import univ.iwa.model.*;
import univ.iwa.repository.*;
import org.modelmapper.ModelMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.*;
@Service
public class PlanificationService {
    @Autowired
    PlanificationReposertory planirepo;
   @Autowired
    FormationReposetory formrepo;
   @Autowired
   ModelMapper modelMapper;
   @Autowired
   UserInfoRepository userrepo;
   @Autowired
   EmailService emailService;
   
   @Autowired
   GroupeReposetory groupeReposetory;
   
   @Autowired
   EntrepriseReposetory entrepriseReposetory;
   
   
   
   
    //ajouter une formation a la planification
    public Formationplanifierdto addplanification(Formationplanifierdto formplani) throws ParseException {
    	Formationplanifier planification =modelMapper.map(formplani, Formationplanifier.class);
    	planification.setFormation(formrepo.findById(formplani.getFormationId()).get());
    	planification.setFormateur(userrepo.findById(formplani.getFormateurId()).get());
    	if(formplani.getGroupeId()!=null) {
    		Groupe groupe =groupeReposetory.findById(formplani.getGroupeId()).get();
    		groupe.setFormateur(userrepo.findById(formplani.getFormateurId()).get());
    		planification.setGroupe(groupe);
    	}else if(formplani.getEntrepriseId()!=null) {
    		planification.setEntreprise(entrepriseReposetory.findById(formplani.getEntrepriseId()).get());
    	}
    	
    	Formationplanifier CreatedPlanification =planirepo.save(planification);
    	return modelMapper.map(CreatedPlanification,Formationplanifierdto.class);
    }
  //recuper les planification d une formation
public List<Formationplanifierdto> getPlanifications() throws ParseException {
	List<Formationplanifier> formationplanifiers = planirepo.findAll();
	List<Formationplanifierdto> formationplanifierdtos = formationplanifiers.stream()
			.map(planififctaion ->{
				Formationplanifierdto formationplanifierdto =modelMapper.map(planififctaion, Formationplanifierdto.class);
				if(planififctaion.getFormation()!=null) {
					formationplanifierdto.setFormationId(planififctaion.getFormation().getId());
				}
				if(planififctaion.getFormateur()!=null) {
					formationplanifierdto.setFormateurId(Integer.parseInt(""+planififctaion.getFormateur().getId()) );
				}
				if(planififctaion.getEntreprise()!=null) {
					formationplanifierdto.setEntrepriseId(planififctaion.getEntreprise().getId());	
				}else {
					formationplanifierdto.setGroupeId(planififctaion.getGroupe().getId());
				}
				
				return formationplanifierdto;
				}
					
					).collect(Collectors.toList());
    return formationplanifierdtos;
}
public List<Formationplanifierdto> getPlanificationsByFormateurId(Integer idFormateur)  {
	UserInfo formateur = userrepo.getById(idFormateur);
	List<Formationplanifier> formationplanifiers = planirepo.findByFormateur(formateur);
	List<Formationplanifierdto> formationplanifierdtos = formationplanifiers.stream()
			.map(planififctaion ->{
				Formationplanifierdto formationplanifierdto =modelMapper.map(planififctaion, Formationplanifierdto.class);
				if(planififctaion.getFormation()!=null) {
					formationplanifierdto.setFormationId(planififctaion.getFormation().getId());
				}
				if(planififctaion.getFormateur()!=null) {
					formationplanifierdto.setFormateurId(Integer.parseInt(""+planififctaion.getFormateur().getId()) );
				}
				if(planififctaion.getEntreprise()!=null) {
					formationplanifierdto.setEntrepriseId(planififctaion.getEntreprise().getId());	
				}else {
					formationplanifierdto.setGroupeId(planififctaion.getGroupe().getId());
				}
				
				return formationplanifierdto;
				}
					
					).collect(Collectors.toList());
    return formationplanifierdtos;
}

    //supprimer une formation
//    public boolean  delete(long id ){
//    	if (!planirepo.existsById(id)) {
//  	      return false; // Devuelve false si el usuario no existe
//  	    }
//        planirepo.deleteById(id);
//        return true;
//    }

    //update une planification
    public Formationplanifierdto updateplanification(long id ,Formationplanifierdto formationplan){
        Optional<Formationplanifier> formation= planirepo.findById(id);

        if(formation.isPresent()){
            Formationplanifier  form=formation.get();
            form.setDatedebut(formationplan.getDatedebut());
            form.setDatefin(formationplan.getDatefin());
            form.setTitle(formationplan.getTitle());
            form.setFormation(modelMapper.map(formrepo.findById(formationplan.getFormationId()).get() , Formation.class));
            form.setFormateur(modelMapper.map(userrepo.findById(formationplan.getFormateurId()).get() , UserInfo.class));
            if(formationplan.getGroupeId()!=null) {
            	form.setGroupe(groupeReposetory.findById(formationplan.getGroupeId()).get());
        	}else if(formationplan.getEntrepriseId()!=null) {
        		form.setEntreprise(entrepriseReposetory.findById(formationplan.getEntrepriseId()).get());
        	}
            return modelMapper.map(planirepo.save(form),Formationplanifierdto.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Formationplanifier with ID %d does not exist", id));
        }
    }

    //supprimer une planification
    public boolean  supprimerplanification(long id){
    	if (!planirepo.existsById(id)) {
    	      return false; // Devuelve false si el usuario no existe
    	    }
        planirepo.deleteById(id);
        return true;
    }
    
    
    // recuperer les formations planifier qui ont terminer 
//    public void  EnvoyerEvaluationFormamulair(){
//    	LocalDate currentdate=LocalDate.now();
//    	List<Formationplanifier> formationsTermine=planirepo.findByDatefinAfter(currentdate);
//    	 for (Formationplanifier formation : formationsTermine) {
//             List<Individuals> participants = formation.getGroupe();
//             String formLink = "https://docs.google.com/forms/d/e/1FAIpQLScHWlS8V71oEiMK1DL4QrQpihnESp8uYcTkqY6Lnh0TtsL0_g/viewform?usp=pp_url&entry.1737993738=bien";
//             for (Individuals participant : participants) {
//                 String participantEmail = participant.getEmail();
//                 String subject = "Évaluation de la formation terminée";
//                 String emailContent = "Cher participant,\n\nNous vous remercions d'avoir participé à notre formation. Pour nous aider à améliorer nos services, veuillez remplir notre formulaire d'évaluation en ligne : " + formLink + "\n\nCordialement,\nL'équipe de formation";
//                 emailService.sendSimpleEmail(participantEmail, subject, emailContent);
//             }
//    	 
//    	 }

    	
   // }
    
    
}
