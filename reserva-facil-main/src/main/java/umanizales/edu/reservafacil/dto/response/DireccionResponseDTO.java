package umanizales.edu.reservafacil.dto.response;

import lombok.Data;

@Data
public class DireccionResponseDTO {
	private Long id;
	private String ciudad;
	private String barrio;
	private String calle;
}