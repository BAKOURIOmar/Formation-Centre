package univ.iwa.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import univ.iwa.dto.Userdto;
import univ.iwa.model.Formation;
import univ.iwa.model.UserInfo;
import univ.iwa.repository.FormationReposetory;
import univ.iwa.dto.Formationdto;
@Service
public class FormationService {
	@Autowired
	FormationReposetory formrepo;
	
  public String addformation(Formationdto formationdto) {
	 Formation formation=new Formation();
	  formation.setId(formationdto.getId());
	  formation.setName(formationdto.getName());
	  formation.setCout(formationdto.getCout());
	  formation.setNombreh(formationdto.getNombreh());
	  formation.setProgramme(formationdto.getProgramme());
	  formation.setVille(formationdto.getVille());
	  formation.setCategorie(formationdto.getCategorie());
	  formrepo.save(formation);
	  return "added";
	  }

	  public  List<Formationdto> getAllFormations() {
		List<Formation>formation= formrepo.findAll();
	  return formation.stream().map(this::convertEntityToDto).collect(Collectors.toList());
  }
     //update formation
	public String updateformation(Long id,Formationdto forma ){
	  Optional<Formation> formoption= formrepo.findById(id);
		Formation form=new Formation();
	  if(formoption.isPresent()){
		   form=formoption.get();
		  form.setName(forma.getName());
		  form.setCout(forma.getCout());
		  form.setNombreh(forma.getNombreh());
		  form.setCategorie(forma.getCategorie());
		  form.setVille(forma.getVille());
		  form.setProgramme(forma.getProgramme());
	  }
	 formrepo.save(form);
     return "updated";
	}
	public String DeleteFormation(Long formationId) {
		Optional<Formation>  formationD =formrepo.findById(formationId);
		formrepo.delete(formationD.get());
		return "Formation deleted";

	}
	//get by categorie
	public List<Formationdto> getformationcategorie(String categorie){
	  List<Formation> formations=formrepo.findByCategorie(categorie);

	  return formations.stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}

	public List<Formationdto> getformtionville(String ville){
	  List<Formation> formations=formrepo.findByVille( ville);
	  return formations.stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}
	public Formationdto convertEntityToDto(Formation formation){
		Formationdto formationdto=new Formationdto();
		formationdto.setId(formation.getId());
		formationdto.setName(formation.getName());
		formationdto.setCategorie(formation.getCategorie());
		formationdto.setCout(formation.getCout());
		formationdto.setNombreh(formation.getNombreh());
		formationdto.setProgramme(formation.getProgramme());
		return formationdto;
	}
}
