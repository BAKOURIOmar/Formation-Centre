package univ.iwa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import univ.iwa.dto.GroupeDto;
import univ.iwa.service.GroupeService;

@RestController
@RequestMapping("/group")
public class GroupController {
	
	private GroupeService groupeService;	
	
	   @PostMapping("/getAllGroupes")
	    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	    public ResponseEntity<List<GroupeDto>> getAllGroupes(){        
	        return new ResponseEntity<List<GroupeDto>>(groupeService.getAllGroupes(),HttpStatus.OK);
	    }
}
