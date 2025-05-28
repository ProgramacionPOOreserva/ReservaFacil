package umanizales.edu.reservafacil.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ReservaRequestDTO {
	@NotNull(message = "El ID del negocio debe ser obligatorio")
	private Long negocioId;

	@NotNull(message = "El ID del cliente debe ser obligatorio")
	private Long clienteId;
}