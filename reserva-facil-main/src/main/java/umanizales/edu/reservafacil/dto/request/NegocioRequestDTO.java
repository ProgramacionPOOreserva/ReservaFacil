package umanizales.edu.reservafacil.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class NegocioRequestDTO {
	@NotBlank(message = "El nombre del negocio debe ser obligatorio")
	@Size(max = 50, message = "El nombre del negocio no debe exceder los 50 caracteres")
	private String nombre;

	@NotBlank(message = "La descripción del negocio debe ser obligatoria")
	private String descripcion;

	@NotNull(message = "El ID del administrador debe ser obligatorio")
	private Long administradorId;

	@NotNull(message = "El ID de la categoría debe ser obligatorio")
	private Long categoriaId;

	@NotNull(message = "La dirección debe ser obligatoria")
	private DireccionRequestDTO direccion;
}