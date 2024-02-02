package univ.iwa.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import univ.iwa.model.*;
import univ.iwa.dto.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import univ.iwa.dto.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Formationplanifierdto {

    private long id;
    private LocalDate datedebut;
    private LocalDate datefin;
    private Formationdto formation;
    private Userdto formateur;
    private Entreprisedto entreprise;
    private Groupedto groupe;

}
