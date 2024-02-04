package univ.iwa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import univ.iwa.model.Formation;
import univ.iwa.model.Groupe;

public interface GroupeReposetory extends JpaRepository<Groupe, Long> {
	Optional<List<Groupe>> findGroupeByFormation(Formation formation);
}
