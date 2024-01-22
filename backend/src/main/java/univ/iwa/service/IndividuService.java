package univ.iwa.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        individurepo.save(individu);
        return individudto;
    }


    // Récupérer tous les individus en utilisant ModelMapper
    public List<Inndividualsdto> getallindividus() {
        List<Individuals> individus = individurepo.findAll();
        return individus.stream()
                .map(individu -> modelMapper.map(individu, Inndividualsdto.class))
                .collect(Collectors.toList());
    }

    // Supprimer un individu
    public void deleteindividu(Long id) {
        individurepo.deleteById(id);
    }
    
    // Modifier l'individu
    public Inndividualsdto updateIndividu(Long id, Inndividualsdto updatedIndividudto) {
        Optional<Individuals> existingIndividuOptional = individurepo.findById(id);

        if (existingIndividuOptional.isPresent()) {
            Individuals existingIndividu = existingIndividuOptional.get();
            modelMapper.map(updatedIndividudto, existingIndividu);
            individurepo.save(existingIndividu);
            return modelMapper.map(existingIndividu, Inndividualsdto.class);
        } else {
            
            return null;
        }
    }
}
