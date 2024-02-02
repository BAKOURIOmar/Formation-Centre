package univ.iwa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import univ.iwa.model.Formationplanifier;
import java.util.*;
import univ.iwa.model.*;
import java.util.List;
import java.time.LocalDate;

@Repository
public interface PlanificationReposertory extends JpaRepository<Formationplanifier,Long > {
   public List<Formationplanifier> findByDatedebut(Date datedebut);
   
   public List<Formationplanifier> findByDatefin(Date datefin);

   @Query("SELECT fp FROM Formationplanifier fp JOIN fp.formation f WHERE f.name = :name")
   List<Formationplanifier> findByFormationName(String name);
   
   public List<Formationplanifier> findByDatefinAfter(LocalDate date);
}
