package univ.iwa.dto;


import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Formationdto {


    private long id ;
    private String name;
    private Long nombreh;
    private long seuil;
    private  double cout;
    private String programme;
    private LocalDate date;
    private String ville;
    private String categorie;
    private byte[] picture;
    private List<GroupeDto> groupes;
  
  
  
    

}
