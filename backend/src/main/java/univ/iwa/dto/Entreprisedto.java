package univ.iwa.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collector;

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
