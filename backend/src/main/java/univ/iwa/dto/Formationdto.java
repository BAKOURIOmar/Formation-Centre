package univ.iwa.dto;


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
    private  double cout;
    private String programme;
    private String ville;
    private String categorie;

}
