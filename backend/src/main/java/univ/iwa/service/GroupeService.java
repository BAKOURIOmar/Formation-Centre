package univ.iwa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import univ.iwa.dto.GroupeDto;
import univ.iwa.model.Groupe;
import univ.iwa.repository.GroupeReposetory;

@Service
public class GroupeService {
	
	@Autowired
	GroupeReposetory repository;
	
	@Autowired
    ModelMapper modelMapper;

    public List<GroupeDto> getAllGroupes() {
        List<Groupe> groupes = repository.findAll();
        return groupes.stream()
                .map(groupe -> modelMapper.map(groupe, GroupeDto.class))
                .collect(Collectors.toList());
    }
    public Groupe getGroupById(Long id){
        return  this.repository.findById(id).get();
    }
}
