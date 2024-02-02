package univ.iwa.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Groupedto {
	 private Long id;
	 private Long formationId;
	 private List<Long> individualsIds;

}
