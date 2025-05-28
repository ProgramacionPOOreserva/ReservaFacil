package umanizales.edu.reservafacil.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "direcciones")
public class Direccion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String ciudad;

	@Column(nullable = false)
	private String barrio;

	@Column(nullable = false)
	private String calle;
}