package univ.iwa.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
public class Individuals {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	long id;
	String nom;
	String prenom;
	String ville;
	String email;
	String tel;
	LocalDate dateDeNaissance;
	@ManyToOne
	@JoinColumn(name = "formation_id") // Assurez-vous de spécifier le nom de la colonne dans la table Individuals
	private Formation formation;
	@ManyToOne
	@JoinColumn(name = "groupe_id")
	private Groupe groupe;

}
