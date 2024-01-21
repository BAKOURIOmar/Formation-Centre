package univ.iwa.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import univ.iwa.dto.Userdto;
import univ.iwa.model.Formation;
import univ.iwa.model.UserInfo;
import univ.iwa.repository.FormationReposetory;
import univ.iwa.dto.Formationdto;
@Service
public class FormationService {
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	FormationReposetory formrepo;

	public Formationdto addformation(Formationdto formationdto) {
		Formation formation = modelMapper.map(formationdto, Formation.class);
		formrepo.save(formation);
		return modelMapper.map(formation, Formationdto.class);
	}

	public List<Formationdto> getAllFormations() {
		List<Formation> formations = formrepo.findAll();
		List<Formationdto> formationdtos = formations.stream()
				.map(formation -> modelMapper.map(formation, Formationdto.class))
				.collect(Collectors.toList());

		return formationdtos;
	}

	//update formation
	public String updateformation(Long id, Formationdto forma) {
		Optional<Formation> formoption = formrepo.findById(id);
		Formation form = new Formation();
		if (formoption.isPresent()) {
			form = formoption.get();
			modelMapper.map(forma, form);
		}
		formrepo.save(form);
		return "updated";
	}

	public String DeleteFormation(Long formationId) {
		Optional<Formation> formationD = formrepo.findById(formationId);
		formrepo.delete(formationD.get());
		return "Formation deleted";

	}

	//get by categorie
	public List<Formationdto> getformationcategorie(String categorie) {
		List<Formation> formations = formrepo.findByCategorie(categorie);

		List<Formationdto> formationsdto = formations.stream().map(formation -> modelMapper.map(formation, Formationdto.class))
				.collect(Collectors.toList());
		return formationsdto;
	}

	public List<Formationdto> getformtionville(String ville) {
		List<Formation> formations = formrepo.findByVille(ville);
		List<Formationdto> formationdto = formations.stream().map(formation -> modelMapper.map(formation, Formationdto.class)).collect(Collectors.toList());
		return formationdto;
	}

}