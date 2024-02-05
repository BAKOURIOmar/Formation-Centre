package univ.iwa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import univ.iwa.model.Entreprise;
import univ.iwa.model.Formation;

@Repository
public interface EntrepriseReposetory extends JpaRepository<Entreprise, Long> {
public void deleteById(int id);
public List<Entreprise> findByNameContaining(String name);

}
