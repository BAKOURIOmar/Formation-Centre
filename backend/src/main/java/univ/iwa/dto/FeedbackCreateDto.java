package univ.iwa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import univ.iwa.model.Individuals;
import univ.iwa.model.UserInfo;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackCreateDto {

    public Long id ;
    public int rating ;
    public String description ;
    private Long individualId;
    private Integer formateurId;

}
