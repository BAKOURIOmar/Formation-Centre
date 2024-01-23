package univ.iwa.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import univ.iwa.dto.Formationplanifierdto;
import univ.iwa.dto.Userdto;
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
    public ResponseEntity<Formationplanifierdto> addplanification(@RequestBody Formationplanifierdto formpla) throws ParseException {   
        return new ResponseEntity<Formationplanifierdto>(planserv.addplanification(formpla),HttpStatus.OK);
    }

    //recuperer tout les formation planifiee et faire le filtrage des donnes
    @GetMapping("/getform")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT')")
    public ResponseEntity<List<Formationplanifierdto>> getallplanification(@RequestParam (required = false) String nom,@RequestParam (required = false)String date ) throws ParseException {
       
      return new ResponseEntity<List<Formationplanifierdto>>(planserv.afficherformation(nom,date),HttpStatus.OK);

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
}
