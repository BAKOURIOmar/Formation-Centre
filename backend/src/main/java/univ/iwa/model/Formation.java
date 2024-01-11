package univ.iwa.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id; 
@Entity
@Data 
@AllArgsConstructor 
@NoArgsConstructor
public class Formation {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id ;
	private String name;
	private Long nombreh;
	 private  double cout;
	 private String programme;
	 private String ville;
	 private String categorie;
	

}
