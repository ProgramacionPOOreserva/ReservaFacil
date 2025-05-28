package umanizales.edu.reservafacil.dto.response;

import lombok.Data;

@Data
public class UsuarioResponseDTO {
	private Long id;
	private String nombre;
	private String correo;
	private String telefono;
	private String rol;
}