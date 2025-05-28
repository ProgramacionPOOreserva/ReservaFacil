package umanizales.edu.reservafacil.dto.response;

import lombok.Data;

@Data
public class NegocioResponseDTO {
	private Long id;
	private String nombre;
	private String descripcion;
	private Long administradorId;
	private CategoriaResponseDTO categoria;
	private DireccionResponseDTO direccion;
}