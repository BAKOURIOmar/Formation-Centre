package univ.iwa.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Userdto {

    private int id;
    private String name;
    private String email;
    private String motcles;
    private String note;
    private String password;
    private String roles;
    private String type;
}
