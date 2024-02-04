package univ.iwa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import univ.iwa.dto.GroupeDto;
import univ.iwa.model.Formation;
import univ.iwa.model.Groupe;
import univ.iwa.repository.FormationReposetory;
import univ.iwa.repository.GroupeReposetory;

@Service
public class GroupeService {
	
	@Autowired
	GroupeReposetory repository;
	@Autowired
	FormationReposetory formationReposetory;
	
	@Autowired
    ModelMapper modelMapper;

    public List<GroupeDto> getAllGroupesByFormation(Long formationId) {
    	Formation formation =formationReposetory.findById(formationId).get();
        List<Groupe> groupes = repository.findGroupeByFormation(formation)
        		.orElseThrow(() -> new RuntimeException("there is no groupe found with th idFormation: " + formationId));
        return groupes.stream()
                .map(groupe -> modelMapper.map(groupe, GroupeDto.class))
                .collect(Collectors.toList());
    }
    public Groupe getGroupById(Long id){
        return  this.repository.findById(id).get();
    }
}
