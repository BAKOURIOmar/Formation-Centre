package univ.iwa.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
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
	private long seuil;
	private String programme;
	private String ville;
	private LocalDate date;
	private String categorie;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column( name ="picture", columnDefinition = "longblob")
	private byte[] picture;
	
	 @OneToMany(mappedBy = "formation", cascade = {CascadeType.REMOVE, CascadeType.MERGE})
	    private List<Individuals> inscrits;

	    @OneToMany(mappedBy = "formation", cascade = {CascadeType.REMOVE, CascadeType.MERGE})
	    private List<Groupe> groupes;
}
