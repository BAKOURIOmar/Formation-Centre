package univ.iwa.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.jsonwebtoken.io.IOException;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import univ.iwa.model.Formation;
import univ.iwa.repository.FormationReposetory;
import univ.iwa.dto.Formationdto;
@Service
public class FormationService {
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	FormationReposetory formrepo;

	/*public Formation addformation(Formationdto formationdto, MultipartFile image) throws IOException, java.io.IOException {
        Formation formation = modelMapper.map(formationdto, Formation.class);
        try {
            // Sauvegarder la formation
            formrepo.save(formation);
            // Enregistrer l'image
            String imagePath = "src/main/resources/static/photos/" + formation.getId() + ".png";
            image.transferTo(Path.of(imagePath));
            // Mettre à jour le chemin de l'image dans la formation
            String imageUrl = "http://localhost:8080/photos/" + formation.getId() + ".png";
            formation.setImage(imageUrl);
            // Sauvegarder la formation mise à jour
            return formrepo.save(formation);

        } catch (IOException e) {
            e.printStackTrace(); // Gérer l'exception selon vos besoins
            return null; // Retourner null en cas d'échec
        }
    }*/
	public Formation addformation(Formationdto formationdto, MultipartFile image) throws IOException, IllegalStateException, java.io.IOException {
	    Formation formation = modelMapper.map(formationdto, Formation.class);
	    try {
	        // Sauvegarder la formation
	        formrepo.save(formation);
	        // Enregistrer l'image
	        String imagePath = "src/main/resources/static/photos/" + formation.getId() + ".png";
	        image.transferTo(Path.of(imagePath));
	        // Mettre à jour le chemin de l'image dans la formation
	        String imageUrl = "http://localhost:8080/photos/" + formation.getId() + ".png";
	        formation.setImage(imageUrl);
	        // Sauvegarder la formation mise à jour
	        return formrepo.save(formation);
	    } catch (IOException e) {
	        // Propagate the exception or wrap it in a custom exception
	        throw new IOException("Failed to add formation with image.", e);
	    }
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