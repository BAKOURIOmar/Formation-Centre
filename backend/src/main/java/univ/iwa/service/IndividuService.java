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
import univ.iwa.model.Formation;
import univ.iwa.model.Groupe;
import univ.iwa.model.Individuals;
import univ.iwa.repository.GroupeReposetory;
import univ.iwa.repository.IndividuRepository;

@Service
public class IndividuService {

    @Autowired
    private IndividuRepository individurepo;
    
    @Autowired
    private FormationService formationService;
    
    @Autowired
    private GroupeReposetory groupeReposetory;

    private ModelMapper modelMapper;

    @Autowired
    public IndividuService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // Ajouter un individu
    public Inndividualsdto inscription(Inndividualsdto individuDto, Long idFormation) {
        Formation formation = formationService.findById(idFormation)
                .orElseThrow(() -> new IllegalArgumentException("Formation non trouvée"));
        Groupe groupe = obtenirOuCreeGroupe(formation);
        Individuals individus = modelMapper.map(individuDto, Individuals.class);
        individus.setGroupe(groupe);
        individus.setFormation(formation);
        Individuals individuSauvegarde = individurepo.save(individus);
        return modelMapper.map(individuSauvegarde, Inndividualsdto.class);
    }

    private Groupe obtenirOuCreeGroupe(Formation formation) {
        List<Groupe> groupesExistants = formation.getGroupes();
        if (groupesExistants == null || groupesExistants.isEmpty()) {
            return creerNouveauGroupe(formation);
        }
        Groupe groupeActuel = groupesExistants.get(groupesExistants.size() - 1);
        if (groupeActuel.getInscrits().size() >= formation.getSeuil()) {
            return creerNouveauGroupe(formation);
        }
        return groupeActuel;
    }

    private Groupe creerNouveauGroupe(Formation formation) {
        Groupe nouveauGroupe = new Groupe();
        nouveauGroupe.setFormation(formation);
        return groupeReposetory.save(nouveauGroupe);
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
