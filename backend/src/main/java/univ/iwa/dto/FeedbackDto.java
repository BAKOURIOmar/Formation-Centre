package univ.iwa.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import univ.iwa.model.Individuals;
import univ.iwa.model.UserInfo;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDto {

    public Long id ;
    public int rating ;
    public String description ;
    private Individuals individual;
    private UserInfo user;

}
