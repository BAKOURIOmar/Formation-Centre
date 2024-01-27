package univ.iwa.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import univ.iwa.dto.Inndividualsdto;
import univ.iwa.model.Individuals;
import univ.iwa.repository.IndividuRepository;

@Service
public class IndividuService {

    @Autowired
    private IndividuRepository individurepo;

    private ModelMapper modelMapper;

    @Autowired
    public IndividuService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // Ajouter un individu
    public Inndividualsdto addindividu(Inndividualsdto individudto) {
        Individuals individu = modelMapper.map(individudto, Individuals.class);
        Individuals createdInndividual =individurepo.save(individu);
        return modelMapper.map(createdInndividual, Inndividualsdto.class);
    }


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
