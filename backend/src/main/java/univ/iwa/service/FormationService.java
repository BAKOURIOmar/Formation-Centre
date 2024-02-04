package univ.iwa.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;


import io.jsonwebtoken.io.IOException;
import univ.iwa.dto.Formationdto;
import univ.iwa.dto.filtredto;
import univ.iwa.model.Formation;
import univ.iwa.model.Formationplanifier;
import univ.iwa.repository.FormationReposetory;
import univ.iwa.repository.PlanificationReposertory;
import univ.iwa.util.Util;

@Service
public class FormationService {
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	FormationReposetory formrepo;
	@Autowired
	PlanificationReposertory planificationReposertory;

//Ajouter Formation
	public Formationdto addFormation(MultipartFile picture, String name, Long nombreh,Long seuil, double cout, String programme,
			String ville, String categorie, LocalDate date) throws IOException, java.io.IOException {
		Formationdto formationdto = new Formationdto();
		formationdto.setName(name);
		formationdto.setNombreh(nombreh);
		formationdto.setSeuil(seuil);
		formationdto.setCout(cout);
		formationdto.setProgramme(programme);
		formationdto.setVille(ville);
		formationdto.setCategorie(categorie);
		formationdto.setDate(date);
		formationdto.setPicture(Util.compressZLib(picture.getBytes()));
		Formation formation = modelMapper.map(formationdto, Formation.class);
		return modelMapper.map(formrepo.save(formation), Formationdto.class);
	}
	
	public Optional<Formation> findById(Long formationId) {
        return formrepo.findById(formationId);
    }

	// Lister tous les formations
//	public List<Formationdto> getAllFormations( Pageable pageable) throws java.io.IOException {
//		List<Formation> list = new ArrayList();
//		try {
//			List<Formation> formations = formrepo.findAll();
//
//			if (formations.size() > 0) {
//
//				formations.stream().forEach((f) -> {
//					byte[] imageDescompressed = Util.decompressZLib(f.getPicture());
//					f.setPicture(imageDescompressed);
//					list.add(f);
//				});
//
//			} else {
//				throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("there is no Formations"));
//			}
//		} catch (Exception e) {
//			e.getStackTrace();
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Error catch"));
//
//		}
//
//		return list.stream().map(f -> modelMapper.map(f, Formationdto.class)).collect(Collectors.toList());
//	}
	public Page<Formationdto> getAllFormations(Pageable pageable) throws java.io.IOException {
	  
	        Page<Formation> formationsPage = formrepo.findAll(pageable);
	        
	        List<Formationdto> formationdtoList = formationsPage.getContent().stream()
	            .map(f -> {
	                try {
	                    byte[] imageDescompressed = Util.decompressZLib(f.getPicture());
	                    f.setPicture(imageDescompressed);
	                    return modelMapper.map(f, Formationdto.class);
	                } catch (IOException e) {
	                    // Handle IOException if necessary
	                    return null; // Or throw an exception
	                }
	            })
	            .collect(Collectors.toList());

	        return new PageImpl<>(formationdtoList, pageable, formationsPage.getTotalElements());
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
			formation.setDate(formationdto.getDate());
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
		Formation formation =formrepo.getById(formationId);
		formation.getGroupes().clear();
		formation.getInscrits().clear();
		List<Formationplanifier> lists = planificationReposertory.findByFormation(formation);
		for (Formationplanifier formationplanifier : lists) {
			formationplanifier.setFormation(null);
			planificationReposertory.save(formationplanifier);
			
		}
		formrepo.deleteById(formationId);
		return true;

	}

////Afficher les formations par catégoeies
//	public List<Formationdto> getformationCategorie(String categorie,Pageable pageable) {
//		List<Formation> list = new ArrayList();
//		try {
//			List<Formation> formations = formrepo.findByCategorie(categorie);
//
//			if (formations.size() > 0) {
//
//				formations.stream().forEach((f) -> {
//					byte[] imageDescompressed = Util.decompressZLib(f.getPicture());
//					f.setPicture(imageDescompressed);
//					list.add(f);
//				});
//
//			} else {
//				throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("there is no Formations"));
//			}
//		} catch (Exception e) {
//			e.getStackTrace();
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Error catch"));
//
//		}
//
//		return list.stream().map(f -> modelMapper.map(f, Formationdto.class)).collect(Collectors.toList());
//	}
	public Page<Formationdto> getformationCategorie(String categorie, Pageable pageable) {
	    try {
	        Page<Formation> formationsPage = formrepo.findByCategorie(categorie, pageable);
	        
	        if (formationsPage.isEmpty()) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no formations for the category: " + categorie);
	        }
	        
	        List<Formationdto> formationdtoList = formationsPage.getContent().stream()
	            .map(f -> {
	                try {
	                    byte[] imageDescompressed = Util.decompressZLib(f.getPicture());
	                    f.setPicture(imageDescompressed);
	                    return modelMapper.map(f, Formationdto.class);
	                } catch (IOException e) {
	                    // Handle IOException if necessary
	                    return null; // Or throw an exception
	                }
	            })
	            .collect(Collectors.toList());

	        return new PageImpl<>(formationdtoList, pageable, formationsPage.getTotalElements());
	    } catch (Exception e) {
	        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred", e);
	    }
	}
	
	
	
//
////Afficher les formations par les villes
	public Page<Formationdto> getformtionville(String ville,Pageable pageable ) {
	try {
		 Page<Formation> formationsPage = formrepo.findByVille(ville,pageable);
		  
	        if (formationsPage.isEmpty()) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no formations for the City: " + ville);
	        }
	        
	        List<Formationdto> formationdtoList = formationsPage.getContent().stream()
	            .map(f -> {
	                try {
	                    byte[] imageDescompressed = Util.decompressZLib(f.getPicture());
	                    f.setPicture(imageDescompressed);
	                    return modelMapper.map(f, Formationdto.class);
	                } catch (IOException e) {
	                    // Handle IOException if necessary
	                    return null; // Or throw an exception
	                }
	            })
	            .collect(Collectors.toList());

	     
	        return new PageImpl<>(formationdtoList, pageable, formationsPage.getTotalElements());
	} catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred", e);
    }
}
	//recuperer les image by id
 public Formationdto getFormationByid(long id) {
	 Optional<Formation> formationOptional = formrepo.findById(id);
	
	    if (formationOptional.isPresent()) {
	        Formation formation = formationOptional.get();
	        byte[] imageDescompressed = Util.decompressZLib(formation.getPicture());
	        formation.setPicture(imageDescompressed);
	        
	        return modelMapper.map(formation, Formationdto.class);
	    } else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no formation with this id: " + id);
	    }
 }

 public Page<Formationdto> filtreSearch(filtredto filters, Pageable pageable) {
	    Page<Formationdto> pageResult = Page.empty();

	    try {
	        Specification<Formation> specVille = (root, query, criteriaBuilder) -> {
	            if (filters.getVille() != null && filters.getVille().isPresent() && !filters.getVille().get().isEmpty()) {
	                return criteriaBuilder.like(root.get("ville"), "%" + filters.getVille().get() + "%");
	            } else {
	                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
	            }
	        };

	        Specification<Formation> specCategorie = (root, query, criteriaBuilder) -> {
	            if (filters.getCategorie() != null && filters.getCategorie().isPresent() && !filters.getCategorie().get().isEmpty()) {
	                return criteriaBuilder.like(root.get("categorie"), "%" + filters.getCategorie().get() + "%");
	            } else {
	                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
	            }
	        };

	        Specification<Formation> specDate = (root, query, criteriaBuilder) -> {
	            if (filters.getDate() != null && filters.getDate().isPresent()) {
	                LocalDate date = filters.getDate().get();
	                return criteriaBuilder.equal(root.get("date"), date);
	            } else {
	                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
	            }
	        };

	        Specification<Formation> combinedSpec = Specification.where(specVille).and(specCategorie);

	        Page<Formation> formationsPage = formrepo.findAll(combinedSpec, pageable);
	        pageResult = formationsPage.map(formation -> modelMapper.map(formation, Formationdto.class));

	    } catch (Exception e) {
	        e.printStackTrace();
	        pageResult = Page.empty();
	    }

	    return pageResult;
	}

 public Page<Formationdto> getFormationByName(String name, Pageable pageable) {
	    try {
	        Page<Formation> formationsPage = formrepo.findByName(name, pageable);
	        
	        if (formationsPage.isEmpty()) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no formations with the name: " + name);
	        }
	        
	        List<Formationdto> formationdtoList = formationsPage.getContent().stream()
	            .map(f -> {
	                try {
	                    byte[] imageDescompressed = Util.decompressZLib(f.getPicture());
	                    f.setPicture(imageDescompressed);
	                    return modelMapper.map(f, Formationdto.class);
	                } catch (IOException e) {
	                    // Handle IOException if necessary
	                    return null; // Or throw an exception
	                }
	            })
	            .collect(Collectors.toList());

	        return new PageImpl<>(formationdtoList, pageable, formationsPage.getTotalElements());
	    } catch (Exception e) {
	        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred", e);
	    }
	}
 
 //recuper les foemation 
 public Page<Formationdto> getforamtions( String searchkey,Pageable pageable){
 	Page<Formation> formations= formrepo.findByNameContainingIgnoreCaseOrVilleContainingIgnoreCaseOrCategorieContainingIgnoreCase(searchkey, searchkey, searchkey, pageable);
 	List<Formationdto> list= formations.getContent().stream().map(f -> {
        try {
            byte[] imageDescompressed = Util.decompressZLib(f.getPicture());
            f.setPicture(imageDescompressed);
            return modelMapper.map(f, Formationdto.class);
        } catch (IOException e) {
            // Handle IOException if necessary
            return null; // Or throw an exception
        }
    })
    .collect(Collectors.toList());
 	
 	return new PageImpl<>(list,pageable,formations.getTotalElements());
 }
}