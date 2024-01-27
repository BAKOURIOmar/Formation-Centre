package univ.iwa.dto;


import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
