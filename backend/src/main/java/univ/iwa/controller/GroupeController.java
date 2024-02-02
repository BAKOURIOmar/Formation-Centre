package univ.iwa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import univ.iwa.dto.Groupedto;
import univ.iwa.model.Groupe;
import univ.iwa.service.GroupeService;

@RestController
@RequestMapping("/groupe")
public class GroupeController {
	@Autowired
    private GroupeService groupeService;

    @PostMapping("/create")
    public ResponseEntity<Groupe> createGroup(@RequestBody Groupedto groupedto) {
        Groupe groupe = groupeService.createGroup(groupedto);
        return new ResponseEntity<>(groupe, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Groupe>> getAllGroups() {
        List<Groupe> groupes = groupeService.getAllGroups();
        return new ResponseEntity<>(groupes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Groupe> getGroupById(@PathVariable("id") Long id) {
        Optional<Groupe> groupe = groupeService.getGroupById(id);
        return groupe.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGroupById(@PathVariable("id") Long id) {
        groupeService.deleteGroupById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
