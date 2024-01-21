package univ.iwa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = dateFormat.parse(formplani.getDate());
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

//modifier une planification
public  String modiferPlanification(long id,Formationplanifierdto formationplan){
        Optional<Formationplanifier> formationplanifier=planirepo.findById(id);
      Formationplanifierdto formationdto=new Formationplanifierdto();
      if(formationplanifier.isPresent()){

          Formationplanifier form   =modelMapper.map(formationplanifier.get(),Formationplanifier.class);
          planirepo.save(form);
      }
    return "updated";
    }
    //supprimer une formation
    public String  delete(long id ){
        planirepo.deleteById(id);
        return "deleted";
    }

    //update une planification
    public String updateplanification(long id ,Formationplanifierdto formationplan){
        Optional<Formationplanifier> formation= planirepo.findById(id);

        if(formation.isPresent()){
            Formationplanifier  form=formation.get();
           form =modelMapper.map(formationplan,Formationplanifier.class);
            planirepo.save(form);
        }
     return "update";
    }

    //supprimer une planification
    public void  supprimerplanification(long id){
        planirepo.deleteById(id);

    }
}
