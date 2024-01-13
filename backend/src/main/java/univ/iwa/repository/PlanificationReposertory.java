package univ.iwa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import univ.iwa.model.Formationplanifier;

@Repository
public interface PlanificationReposertory extends JpaRepository<Formationplanifier,Long > {

}
