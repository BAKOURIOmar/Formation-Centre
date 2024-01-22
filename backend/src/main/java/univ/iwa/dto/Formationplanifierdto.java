package univ.iwa.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import univ.iwa.model.*;
import univ.iwa.dto.*;
import java.util.Date;
import univ.iwa.dto.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Formationplanifierdto {

    private long id;
    private String date;
    private Formationdto formation;
    private Userdto formateur;
    private Entreprisedto entreprise;
    private Inndividualsdto groupe;

}
