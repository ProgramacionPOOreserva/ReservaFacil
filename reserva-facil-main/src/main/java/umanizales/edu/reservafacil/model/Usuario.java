package umanizales.edu.reservafacil.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false, unique = true)
	private String correo;

	@Column(nullable = false)
	private String telefono;

	@Column(nullable = false)
	private String contrasenia;

	@Column(nullable = false)
	private String rol; // "ADMIN" o "CLIENTE"
}
