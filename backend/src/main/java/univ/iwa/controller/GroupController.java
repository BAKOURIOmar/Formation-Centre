package univ.iwa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import univ.iwa.dto.GroupeDto;
import univ.iwa.service.GroupeService;

@RestController
@RequestMapping("/group")
public class GroupController {
	
	@Autowired
	private GroupeService groupeService;	
	
	   @GetMapping("/getAllGroupes")
	    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_FORMATEUR')")
	    public ResponseEntity<List<GroupeDto>> getAllGroupesByFormation(@RequestParam Long formationId){        
	        return new ResponseEntity<List<GroupeDto>>(groupeService.getAllGroupesByFormation(formationId),HttpStatus.OK);
	    }
}
