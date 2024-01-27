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
import univ.iwa.util.Util;
import univ.iwa.dto.Formationdto;
import java.nio.file.StandardCopyOption;

@Service
public class FormationService {
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	FormationReposetory formrepo;

//Ajouter Formation
	public Formationdto addFormation(MultipartFile picture, String name, Long nombreh, double cout, String programme,
			String ville, String categorie) throws IOException, java.io.IOException {
		Formationdto formationdto = new Formationdto();
		formationdto.setName(name);
		formationdto.setNombreh(nombreh);
		formationdto.setNombreh(nombreh);
		formationdto.setCout(cout);
		formationdto.setProgramme(programme);
		formationdto.setVille(ville);
		formationdto.setCategorie(categorie);
		formationdto.setPicture(Util.compressZLib(picture.getBytes()));

		Formation formation = modelMapper.map(formationdto, Formation.class);
		return modelMapper.map(formrepo.save(formation), Formationdto.class);
	}

	// Lister tous les formations
	public List<Formationdto> getAllFormations() throws java.io.IOException {
		List<Formation> list = new ArrayList();
		try {
			List<Formation> formations = formrepo.findAll();

			if (formations.size() > 0) {

				formations.stream().forEach((f) -> {
					byte[] imageDescompressed = Util.decompressZLib(f.getPicture());
					f.setPicture(imageDescompressed);
					list.add(f);
				});

			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("there is no Formations"));
			}
		} catch (Exception e) {
			e.getStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Error catch"));

		}

		return list.stream().map(f -> modelMapper.map(f, Formationdto.class)).collect(Collectors.toList());
	}

// Modifier Formation
	public Formationdto updateformation(Long id, Formationdto formationdto, MultipartFile picture)
			throws java.io.IOException {
		Optional<Formation> result = formrepo.findById(id);
		if (result.isPresent()) {
			Formation formation = result.get();
			formation.setName(formationdto.getName());
			formation.setNombreh(formationdto.getNombreh());
			formation.setCout(formationdto.getCout());
			formation.setProgramme(formationdto.getProgramme());
			formation.setVille(formationdto.getVille());
			formation.setCategorie(formationdto.getCategorie());
			formation.setPicture(Util.compressZLib(picture.getBytes()));
			return modelMapper.map(formrepo.save(formation), Formationdto.class);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("Formation with ID %d does not exist", id));
		}

	}

//
//// Supprimer Formation
	public boolean DeleteFormation(Long formationId) {
		if (!formrepo.existsById(formationId)) {
			return false; // Devuelve false si el usuario no existe
		}
		formrepo.deleteById(formationId);
		return true;

	}

////Afficher les formations par catégoeies
	public List<Formationdto> getformationcategorie(String categorie) {
		List<Formation> list = new ArrayList();
		try {
			List<Formation> formations = formrepo.findByCategorie(categorie);

			if (formations.size() > 0) {

				formations.stream().forEach((f) -> {
					byte[] imageDescompressed = Util.decompressZLib(f.getPicture());
					f.setPicture(imageDescompressed);
					list.add(f);
				});

			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("there is no Formations"));
			}
		} catch (Exception e) {
			e.getStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Error catch"));

		}

		return list.stream().map(f -> modelMapper.map(f, Formationdto.class)).collect(Collectors.toList());
	}
//
////Afficher les formations par les villes
	public List<Formationdto> getformtionville(String ville) {
		List<Formation> list = new ArrayList();
	try {
		List<Formation> formations = formrepo.findByVille(ville);
	    
	    
		if( formations.size() > 0) {
			
			formations.stream().forEach( (f) -> {
				byte[] imageDescompressed = Util.decompressZLib(f.getPicture());
				f.setPicture(imageDescompressed);
				list.add(f);
			});
			
		} else {
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("there is no Formations"));
		}
	} catch (Exception e) {
		e.getStackTrace();
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Error catch"));

	} 

    return list.stream()
            .map(f -> modelMapper.map(f, Formationdto.class))
            .collect(Collectors.toList());
	}

}