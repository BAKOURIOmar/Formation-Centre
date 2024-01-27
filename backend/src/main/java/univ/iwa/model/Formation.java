package univ.iwa.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Transient; 
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
	//private MultipartFile image;
	private String imagePath;



}
