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
	private long id;
	private String name; 
	private String email; 
	private String motcles;
	private String note;
	private String password; 
	private String roles;
	private String type;
} 

