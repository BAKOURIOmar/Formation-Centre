package univ.iwa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import univ.iwa.model.Entreprise;
import univ.iwa.model.Feedback;

@Repository
public interface FeedbackReposetory extends JpaRepository<Feedback, Long> {

}
