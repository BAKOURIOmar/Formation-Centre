package univ.iwa.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import univ.iwa.model.*;
import univ.iwa.dto.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Formationplanifierdto {

    private Long id;
    private Date date;
    Formationdto formation;
    Userdto formateur;
    Entreprisedto entreprise;
    Inndividualsdto groupe;

}
