package univ.iwa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import univ.iwa.model.Individuals;

@Repository
public interface IndividuRepository extends JpaRepository<Individuals, Long> {
	
	public void deleteById(int id);
	List<Individuals> findAll();
	 List<Individuals> findByFormationId(Long formationId);
}
