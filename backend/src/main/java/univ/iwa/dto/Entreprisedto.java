package univ.iwa.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entreprisedto {

    private Long id;
    private String name;
    private String adresse;
    private Long tel;
    private String url;
    private String email;
}
