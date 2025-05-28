package umanizales.edu.reservafacil.dto.response;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservaResponseDTO {
	private Long id;
	private LocalDate fecha;
	private LocalTime hora;
	private Long negocioId;
	private Long clienteId;
}