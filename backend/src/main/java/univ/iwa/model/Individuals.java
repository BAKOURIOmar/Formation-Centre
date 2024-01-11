package univ.iwa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Individuals {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	long id;
	String nom;
	String prenom;
	String ville;
	String email;
	String tel;
}
