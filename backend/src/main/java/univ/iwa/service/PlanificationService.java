package univ.iwa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univ.iwa.repository.PlanificationReposertory;
import univ.iwa.dto.Formationplanifierdto;
import univ.iwa.model.Formationplanifier;
import univ.iwa.model.*;
@Service
public class PlanificationService {
    @Autowired
    PlanificationReposertory plnirepo;

    //ajpiter une formation a la planification
    public String addplanification(Formationplanifierdto formplani){
        Formationplanifier formation=new Formationplanifier();
        formation.setId(formplani.getId());
  formation.setDate(formplani.getDate());
  
    return "planifid";
    }


}
