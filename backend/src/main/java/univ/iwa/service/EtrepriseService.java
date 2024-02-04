package univ.iwa.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import univ.iwa.dto.Userdto;
import univ.iwa.model.Entreprise;
import univ.iwa.model.Formationplanifier;
import univ.iwa.model.UserInfo;
import univ.iwa.repository.EntrepriseReposetory;
import univ.iwa.repository.PlanificationReposertory;
import univ.iwa.dto.Entreprisedto;
import java.util.*;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
@Service
public class EtrepriseService {

    @Autowired
    EntrepriseReposetory entrorepo;
    
	@Autowired
	PlanificationReposertory planificationReposertory;
	@Autowired
	ModelMapper modelMapper;
    //ajouter une liste d entreprises
    public Entreprisedto addentreprise(Entreprisedto entreprisedto){
        Entreprise entreprise= modelMapper.map(entreprisedto,Entreprise.class);
        return modelMapper.map(entrorepo.save(entreprise),Entreprisedto.class);
    }
     //recuperer la listes des entreprises
    public Page<Entreprisedto> getallentreprise(Pageable pageable) {
            Page<Entreprise> entreprisePage = entrorepo.findAll(pageable);
            List<Entreprisedto> entrepriseDtos = entreprisePage.getContent().stream()
                    .map(entreprise -> modelMapper.map(entreprise, Entreprisedto.class))
                    .collect(Collectors.toList());
            return new PageImpl<>(entrepriseDtos, pageable, entreprisePage.getTotalElements());
    }
    //suprimer une entreprise
        public boolean deleteEntreprise(long id) {
        	if (!entrorepo.existsById(id)) {
        	      return false; // Devuelve false si el usuario no existe
        	    }
        	Entreprise entreprise = entrorepo.findById(id).get();
        	List<Formationplanifier> lists = planificationReposertory.findByEntreprise(entreprise);
    		for (Formationplanifier formationplanifier : lists) {
    			formationplanifier.setEntreprise(null);
    			planificationReposertory.save(formationplanifier);
    			
    		}
            entrorepo.deleteById(id);
            return true;
        }
    //update the entreprise
    public Entreprisedto updateentreprise(Long id,Entreprisedto entreprisedto){
       Optional<Entreprise> result=entrorepo.findById(id);
       if (result.isPresent()) {
    	   Entreprise entreprise = result.get();
    	   entreprise.setName(entreprisedto.getName());
    	   entreprise.setUrl(entreprisedto.getUrl());
    	   entreprise.setEmail(entreprisedto.getEmail());
    	   entreprise.setAdresse(entreprisedto.getAdresse());
    	   entreprise.setTel(entreprisedto.getTel());
    	   
    	   
    	   Entreprise updatedEtreprise = entrorepo.save(entreprise);
           return modelMapper.map(updatedEtreprise, Entreprisedto.class);
       } else {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Entreprise with ID %d does not exist", id));
       }
    }
    //Chercher entreprise par nom
    public List<Entreprisedto> getEntrepriseByName(String name) {
        List<Entreprise> entreprises = entrorepo.findByNameContaining(name);
        List<Entreprisedto> entrepriseDtos = entreprises.stream()
                .map(entreprise -> modelMapper.map(entreprise, Entreprisedto.class))
                .collect(Collectors.toList());
        return entrepriseDtos;
    }

}
