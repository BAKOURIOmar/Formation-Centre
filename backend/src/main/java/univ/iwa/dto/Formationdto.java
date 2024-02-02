package univ.iwa.dto;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import univ.iwa.model.Individuals;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Formationdto {


    private long id ;
    private String name;
    private Long nombreh;
    private  double cout;
    private String programme;
    private String ville;
    private String categorie;
//    private MultipartFile image;
   // private String imagePath;
    private byte[] picture;
    private List<Individuals> individus;

}
