package univ.iwa.dto;

import java.time.LocalDate;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class filtredto {
	private Optional<String> ville;
	private Optional<String> categorie;
	private Optional<LocalDate> date;
}
