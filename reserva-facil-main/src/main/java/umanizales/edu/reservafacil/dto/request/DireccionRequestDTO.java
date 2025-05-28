package umanizales.edu.reservafacil.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DireccionRequestDTO {
	@NotBlank(message = "La ciudad debe ser obligatoria")
	@Size(max = 50, message = "La ciudad no debe exceder los 50 caracteres")
	private String ciudad;

	@NotBlank(message = "El barrio debe ser obligatorio")
	@Size(max = 50, message = "El barrio no debe exceder los 50 caracteres")
	private String barrio;

	@NotBlank(message = "La calle debe ser obligatoria")
	@Size(max = 100, message = "La calle no debe exceder los 100 caracteres")
	private String calle;
}