package univ.iwa.dto;


import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import univ.iwa.model.Formation;
import univ.iwa.model.Groupe;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inndividualsdto {

    long id;
    String nom;
    String prenom;
    String ville;
    String email;
    String tel;
    LocalDate dateDeNaissance;
    Formation formation;
    Groupe groupe;
}
