package univ.iwa.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import univ.iwa.dto.Groupedto;
import univ.iwa.model.Formation;
import univ.iwa.model.Groupe;
import univ.iwa.model.Individuals;
import univ.iwa.repository.FormationReposetory;
import univ.iwa.repository.GroupeRepository;
import univ.iwa.repository.IndividuRepository;

@Service
public class GroupeService {
	@Autowired
    private GroupeRepository groupeRepository;

    @Autowired
    private FormationReposetory formationRepository;

    @Autowired
    private IndividuRepository individualsRepository;

    public Groupe createGroup(Groupedto groupedto) {
        Groupe groupe = new Groupe();
        Long formationId = groupedto.getFormationId();
        Optional<Formation> formationOptional = formationRepository.findById(formationId);
        if (formationOptional.isPresent()) {
            Formation formation = formationOptional.get();
            groupe.setFormation(formation);

            List<Long> individualsIds = groupedto.getIndividualsIds();
            List<Individuals> individuals = individualsIds.stream()
                    .map(individualId -> individualsRepository.findById(individualId)
                            .orElseThrow(() -> new IllegalArgumentException("Individual not found with id: " + individualId)))
                    .collect(Collectors.toList());

            groupe.setIndividuals(individuals);

            return groupeRepository.save(groupe);
        } else {
            throw new IllegalArgumentException("Formation not found with id: " + formationId);
        }
    }

    public List<Groupe> getAllGroups() {
        return groupeRepository.findAll();
    }

    public Optional<Groupe> getGroupById(Long id) {
        return groupeRepository.findById(id);
    }

    public void deleteGroupById(Long id) {
        groupeRepository.deleteById(id);
    }


}
