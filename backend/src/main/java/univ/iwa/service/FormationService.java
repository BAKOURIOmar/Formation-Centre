package univ.iwa.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import univ.iwa.model.Formation;
import univ.iwa.repository.FormationReposetory;
@Service
public class FormationService {
	@Autowired
	FormationReposetory formrepo;
	
  public String addformation(Formation formation) {
	 
	  formation.setId(formation.getId());
	  formation.setName(formation.getName());
	  formation.setCout(formation.getCout());
	  formation.setNombreh(formation.getNombreh());
	  formation.setProgramme(formation.getProgramme());
	  formation.setVille(formation.getVille());
	  formation.setCategorie(formation.getCategorie());
	  formrepo.save(formation);
	  return "added";
	  }

	  public  List<Formation> getAllFormations() {
		return formrepo.findAll();
	  }
     //update formation
	public String updateformation(Long id,Formation forma ){
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
	public List<Formation> getformationcategorie(String categorie){
	  List<Formation> formations=formrepo.findByCategorie(categorie);
	  return formations;
	}

	public List<Formation> getformtionville(String ville){
	  List<Formation> formations=formrepo.findByVille( ville);
	  return formations;
	}
}
