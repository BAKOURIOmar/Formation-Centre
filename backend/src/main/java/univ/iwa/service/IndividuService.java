package univ.iwa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import univ.iwa.dto.Formationdto;
import univ.iwa.dto.Inndividualsdto;
import univ.iwa.model.Formation;
import univ.iwa.model.Groupe;
import univ.iwa.model.Individuals;
import univ.iwa.repository.IndividuRepository;

@Service
public class IndividuService {

    @Autowired
    private IndividuRepository individurepo;

    private ModelMapper modelMapper;

    @Autowired
    private FormationService formationservice;

    // inscription d un indevidu dans un eformation
//    public Inndividualsdto addindividu(Inndividualsdto individudto ,Long id) {
//    	//chercher la formation ou l individus veux s inscrire
//    Formationdto formationdto= formationservice.getFormationByid(id);
//     //recuperer les groupe de cette formation
//         if(formationdto.getGroupes().size()==0) {
//        	 List<Groupe> nouveaugroupes=new ArrayList<Groupe>(new Groupe());
//        	 nouveaugroupe.setGroupe(nouveaugroupe);
//        	 
//         }
//        Individuals individu = modelMapper.map(individudto, Individuals.class);
//        Individuals createdInndividual =individurepo.save(individu);
//        return modelMapper.map(createdInndividual, Inndividualsdto.class);
//    }


    // Récupérer tous les individus en utilisant ModelMapper
    public List<Inndividualsdto> getallindividus() {
        List<Individuals> individus = individurepo.findAll();
        return individus.stream()
                .map(individu -> modelMapper.map(individu, Inndividualsdto.class))
                .collect(Collectors.toList());
    }

    // Supprimer un individu
    public boolean deleteindividu(Long id) {
    	if (!individurepo.existsById(id)) {
    	      return false; // Devuelve false si el usuario no existe
    	    }
        individurepo.deleteById(id);
        return true;
    }
    
    // Modifier l'individu
    public Inndividualsdto updateIndividu(Long id, Inndividualsdto updatedIndividudto) {
        Optional<Individuals> existingIndividuOptional = individurepo.findById(id);

        if (existingIndividuOptional.isPresent()) {
            Individuals existingIndividu = existingIndividuOptional.get();
            existingIndividu.setNom(updatedIndividudto.getNom());
            existingIndividu.setPrenom(updatedIndividudto.getPrenom());
            existingIndividu.setVille(updatedIndividudto.getVille());
            existingIndividu.setTel(updatedIndividudto.getTel());
            existingIndividu.setEmail(updatedIndividudto.getEmail());
            existingIndividu.setDateDeNaissance(updatedIndividudto.getDateDeNaissance());
            individurepo.save(existingIndividu);
            return modelMapper.map(existingIndividu, Inndividualsdto.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Inndividu with ID %d does not exist", id));
        }
    }
}
