package univ.iwa.model;
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
    private Date date;
    
    @ManyToOne
    @JoinColumn(name="formation_id")
    private Formation formation;

    @ManyToOne
    @JoinColumn(name = "formateur_id")
    private UserInfo formateur;
    @ManyToOne
    @JoinColumn(name = "Entreprise_id")
    private Entreprise entreprise;

    @ManyToOne
    @JoinColumn(name = "groupe_id")
    private Individuals groupe;


}
