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
    private String 	title;
    private Long formationId;
    private Integer formateurId;
    private Long entrepriseId;
    private Long groupeId;

}
