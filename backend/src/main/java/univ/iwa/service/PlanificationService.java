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
import univ.iwa.model.Formationplanifier;
import univ.iwa.model.*;
import univ.iwa.repository.*;
import org.modelmapper.ModelMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
    //ajpiter une formation a la planification
    public Formationplanifierdto addplanification(Formationplanifierdto formplani) throws ParseException {
        System.out.println("date is : "+formplani.getDate());
        Formationplanifier formation=new Formationplanifier();
      formation= modelMapper.map(formplani,Formationplanifier.class);
        System.out.println("date is : "+formation.getDate());
        LocalDate parsedDate = formplani.getDate();
        System.out.println("date is : "+parsedDate);
        formation.setDate(parsedDate);
      planirepo.save(formation);
     return formplani;
    }
  //recuper les planification d une formation
public List<Formationplanifierdto> afficherformation( String nom, String date) throws ParseException {
       System.out.println("recus");
        List<Formationplanifierdto>formationdto= new ArrayList<>();
        if( date== null && nom==null){
            System.out.println("n est pas reçus");
            List<Formationplanifier> formationsplan=planirepo.findAll();
             formationdto=formationsplan.stream()
                    .map(formationplan->modelMapper.map(formationplan,Formationplanifierdto.class))
                    .collect(Collectors.toList());

        } else if ( date!=null) {
            System.out.println("date recus"+date);
            // je vaie faire le filltrage par le nom
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(date);
            List<Formationplanifier> formations=planirepo.findByDate(parsedDate);

           formationdto=formations.stream()
                    .map(formation->modelMapper.map(formation,Formationplanifierdto.class))
                    .collect(Collectors.toList());


        } else if (nom!=null) {
            System.out.println("name recus"+nom);
            List<Formationplanifier> list=planirepo.findByFormationName(nom);
            formationdto= list.stream().map(form->modelMapper.map(form,Formationplanifierdto.class)).collect(Collectors.toList());

        }
    return formationdto;
}


    //supprimer une formation
    public boolean  delete(long id ){
    	if (!planirepo.existsById(id)) {
  	      return false; // Devuelve false si el usuario no existe
  	    }
        planirepo.deleteById(id);
        return true;
    }

    //update une planification
    public Formationplanifierdto updateplanification(long id ,Formationplanifierdto formationplan){
        Optional<Formationplanifier> formation= planirepo.findById(id);

        if(formation.isPresent()){
            Formationplanifier  form=formation.get();
            form.setDate(formationplan.getDate());
            form.setFormation(modelMapper.map(formationplan.getFormation(), Formation.class));
            form.setFormateur(modelMapper.map(formationplan.getFormateur(), UserInfo.class));
            form.setEntreprise(modelMapper.map(formationplan.getEntreprise(), Entreprise.class));
//            TODO groupe
            
            planirepo.save(form);
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
}
