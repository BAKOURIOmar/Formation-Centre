package univ.iwa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType; 
import jakarta.persistence.Id; 
import lombok.AllArgsConstructor; 
import lombok.Data; 
import lombok.NoArgsConstructor; 

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@JsonIgnoreProperties
public class UserInfo { 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;//both
	private String name; //both
	private String Ville;//assitant
	private String email; //both
	private String motcles;//formateur
	private String remarque;//formateur
	private String password; //both
	private String roles;//both
	private String type;//formateur
} 

