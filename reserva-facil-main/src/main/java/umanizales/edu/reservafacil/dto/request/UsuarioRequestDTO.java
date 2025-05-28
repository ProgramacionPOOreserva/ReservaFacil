package umanizales.edu.reservafacil.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UsuarioRequestDTO {
	@NotBlank(message = "El nombre del usuario debe ser obligatorio")
	@Size(max = 50, message = "El nombre del usuario no debe exceder los 50 caracteres")
	private String nombre;

	@NotBlank(message = "El correo del usuario debe ser obligatorio")
	@Email(message = "El correo debe ser válido")
	private String correo;

	@NotBlank(message = "El teléfono del usuario debe ser obligatorio")
	private String telefono;

	@NotBlank(message = "La contraseña del usuario debe ser obligatoria")
	private String contrasenia;

	@NotBlank(message = "El rol del usuario debe ser obligatorio")
	private String rol;
}