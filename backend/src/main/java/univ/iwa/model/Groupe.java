package univ.iwa.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Groupe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	 @OneToMany(mappedBy = "groupe")
	List<Individuals> inscrits;

	 
	    @ManyToOne
	    @JoinColumn(name = "formation_id")
	    private Formation formation;

	    @ManyToOne
	    @JoinColumn(name = "user_info_id")
	    private UserInfo formateur;
}
