package univ.iwa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import univ.iwa.dto.Formationdto;

import univ.iwa.model.Formation;
import univ.iwa.model.UserInfo;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormationReposetory extends JpaRepository<Formation, Long> ,JpaSpecificationExecutor<Formation>{
	List<Formation> findAll();
	Page<Formation> findByCategorie(String categorie,Pageable pageable);
	Page<Formation> findByVille(String ville,Pageable pageable);
	Page<Formation> findByName(String name,Pageable pageable);
	Page<Formation> findByNameContainingIgnoreCaseOrVilleContainingIgnoreCaseOrCategorieContainingIgnoreCase(
	          String nom, String ville,String categorie, Pageable pageable
	    );
}
