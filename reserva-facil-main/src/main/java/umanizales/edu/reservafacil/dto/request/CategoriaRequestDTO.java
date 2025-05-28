package umanizales.edu.reservafacil.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CategoriaRequestDTO {
	@NotBlank(message = "El nombre de la categoría debe ser obligatorio")
	@Size(max = 30, message = "El nombre de la categoría no debe exceder los 30 caracteres")
	private String nombre;
}