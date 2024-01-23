package univ.iwa.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import io.jsonwebtoken.io.IOException;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import univ.iwa.dto.Userdto;
import univ.iwa.model.Formation;
import univ.iwa.repository.FormationReposetory;
import univ.iwa.dto.Formationdto;
import java.nio.file.StandardCopyOption;

@Service
public class FormationService {
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	FormationReposetory formrepo;
//Ajouter Formation
	public Formation addFormation(Formationdto formationdto) throws IOException, java.io.IOException {
	    Formation formation = modelMapper.map(formationdto, Formation.class);

	    MultipartFile image = formationdto.getImage();

	    if (image != null && !image.isEmpty()) {
	        try {
	            // Enregistrer l'image
	            byte[] imageBytes = image.getBytes();
	            Path imagePath = Path.of("src/main/resources/static/photos/" + formation.getId() + ".png");
	            Files.copy(new ByteArrayInputStream(imageBytes), imagePath, StandardCopyOption.REPLACE_EXISTING);

	            // Mettre à jour le champ image dans la formation
	            formation.setImagePath(imagePath.toString());
	            System.out.print("Requête envoyée");
	        } catch (IOException e) {
	            throw new IOException("Failed to save image.", e);
	        }
	    }

	    return formrepo.save(formation);
	}

	//Lister tous les formations
	public List<Formationdto> getAllFormations() throws java.io.IOException {
	    List<Formation> formations = formrepo.findAll();
	    List<Formationdto> formationdtos = new ArrayList<>();

	    for (Formation formation : formations) {
	        Formationdto formationdto = modelMapper.map(formation, Formationdto.class);

	        // Vérifier si le champ imagePath est non null
	        if (formation.getImagePath() != null) {
	            // Charger le contenu de l'image en tant que tableau de bytes
	            try {
	                Path imagePath = Path.of(formation.getImagePath());
	                byte[] imageBytes = Files.readAllBytes(imagePath);
	                formationdto.setImageBytes(imageBytes);
	            } catch (IOException e) {
	                // Gérer l'exception, par exemple en journalisant l'erreur
	                e.printStackTrace();
	            }
	        }

	        formationdtos.add(formationdto);
	    }

	    return formationdtos;
	}
// Modifier Formation
	public Formationdto updateformation(Long id, Formationdto forma, MultipartFile image) throws java.io.IOException {
	    Optional<Formation> formoption = formrepo.findById(id);
	    Formation form = new Formation();
	    if (formoption.isPresent()) {
	        form = formoption.get();
	        form.setName(forma.getName());
	        form.setNombreh(forma.getNombreh());
	        form.setCout(forma.getCout());
	        form.setProgramme(forma.getProgramme());
	        form.setVille(forma.getVille());
	        form.setCategorie(forma.getCategorie());

	        // Mettez à jour le champ image si le fichier est présent
	        if (image != null && !image.isEmpty()) {
	            try {
	                byte[] imageBytes = image.getBytes();
	                Path imagePath = Path.of("src/main/resources/static/photos/" + form.getId() + ".png");
	                Files.copy(new ByteArrayInputStream(imageBytes), imagePath, StandardCopyOption.REPLACE_EXISTING);

	                // Mettez à jour le champ image dans la formation
	                form.setImagePath(imagePath.toString());
	                System.out.print("Requête envoyée");
	            } catch (IOException e) {
	                throw new IOException("Failed to save image.", e);
	            }
	        }
	        return  modelMapper.map(formrepo.save(form), Formationdto.class);
	       
	    } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Skill with ID %d does not exist", id));
        }

	    
	    
	}

// Supprimer Formation
	public boolean DeleteFormation(Long formationId) {
		if (!formrepo.existsById(formationId)) {
	      return false; // Devuelve false si el usuario no existe
	    }
		formrepo.deleteById(formationId);
		return true;

	}
//Afficher les formations par catégoeies
	public List<Formationdto> getformationcategorie(String categorie) {
	    List<Formation> formations = formrepo.findByCategorie(categorie);
	    List<Formationdto> formationsdto = formations.stream()
	            .map(formation -> {
	                Formationdto formationdto = modelMapper.map(formation, Formationdto.class);
	               
	             // Vérifier si le champ imagePath est non null
	    	        if (formation.getImagePath() != null) {
	    	            // Charger le contenu de l'image en tant que tableau de bytes
	    	            try {
	    	                Path imagePath = Path.of(formation.getImagePath());
	    	                byte[] imageBytes = Files.readAllBytes(imagePath);
	    	                formationdto.setImageBytes(imageBytes);
	    	            } catch (IOException e) {
	    	                // Gérer l'exception, par exemple en journalisant l'erreur
	    	                e.printStackTrace();
	    	            } catch (java.io.IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	    	        }

	                return formationdto;
	            })
	            .collect(Collectors.toList());

	    return formationsdto;
	}

//Afficher les formations par les villes
	public List<Formationdto> getformtionville(String ville) {
	    List<Formation> formations = formrepo.findByVille(ville);
	    List<Formationdto> formationdto = formations.stream()
	            .map(formation -> {
	                Formationdto dto = modelMapper.map(formation, Formationdto.class);

	                // Vérifier si le champ imagePath est non null
	                if (formation.getImagePath() != null) {
	                    // Charger le contenu de l'image en tant que tableau de bytes
	                    try {
	                        Path imagePath = Path.of(formation.getImagePath());
	                        byte[] imageBytes = Files.readAllBytes(imagePath);
	                        // Mettre à jour le champ image dans l'objet Formationdto
	                        dto.setImageBytes(imageBytes);
	                    } catch (IOException e) {
	                        // Gérer l'exception, par exemple en journalisant l'erreur
	                        e.printStackTrace();
	                    } catch (java.io.IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                }

	                return dto;
	            })
	            .collect(Collectors.toList());

	    return formationdto;
	}


}