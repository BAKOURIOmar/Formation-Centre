package univ.iwa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import univ.iwa.repository.GroupeReposetory;

@Service
public class GroupeService {
	
	@Autowired
	GroupeReposetory groupe;

}
