package univ.iwa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import univ.iwa.dto.Formationdto;

import univ.iwa.model.Formation;
import univ.iwa.model.UserInfo;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormationReposetory extends JpaRepository<Formation, Long>{
	List<Formation> findAll();
	List<Formation> findByCategorie(String categorie);
   List<Formation> findByVille(String ville);
}
