package univ.iwa.model;
import java.time.LocalDate;
import java.util.*;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import univ.iwa.model.*;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Formationplanifier {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String 	title;
    private LocalDate datedebut;
    private LocalDate datefin;
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "formation_id", nullable = false)
    Formation formation;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "formateur_id", nullable = false)
    UserInfo formateur;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "entreprise_id", nullable = true)
    Entreprise entreprise;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "groupe_id", nullable = true)
    Groupe groupe;


}
