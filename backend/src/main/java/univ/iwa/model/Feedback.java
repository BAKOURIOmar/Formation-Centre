package univ.iwa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id ;

    public int rating ;
    public String description ;

    @ManyToOne
    @JoinColumn(name = "individual_id")
    @JsonIgnore
    private Individuals individual;

    @ManyToOne
    @JoinColumn(name = "formateur_id")
    @JsonIgnore
    private UserInfo user;

}
