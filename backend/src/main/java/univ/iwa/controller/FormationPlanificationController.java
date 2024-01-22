package univ.iwa.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import univ.iwa.dto.Formationplanifierdto;
import univ.iwa.service.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
@RestController
@RequestMapping("/plan")
public class FormationPlanificationController {
    @Autowired
    PlanificationService planserv;
    //ajouter une planification poour un formation
    @PostMapping("/addplanification")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT')")
    public String addplanification(@RequestBody Formationplanifierdto formpla) throws ParseException {
        planserv.addplanification(formpla);
        return "planification effectues";
    }

    //recuperer tout les formation planifiee et faire le filtrage des donnes
    @GetMapping("/getform")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT')")
    public List<Formationplanifierdto> getallplanification(@RequestParam (required = false) String nom,@RequestParam (required = false)String date ) throws ParseException {
       
      return planserv.afficherformation(nom,date);

    }


    //update la planification d une formation
    @PutMapping("/updatepan/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT')")
    public void modifierplanification(@PathVariable long id,@RequestBody Formationplanifierdto formationplan){
        planserv.updateplanification(id,formationplan);

    }

    @DeleteMapping("/deleteplan/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT')")
    public String deleteplanification(@PathVariable long id){
        planserv.supprimerplanification(id);
        return "deleted";
    }
}
