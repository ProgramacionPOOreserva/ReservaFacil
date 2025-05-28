package umanizales.edu.reservafacil.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "negocios")
public class Negocio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String descripcion;

	@Column(nullable = false)
	private Long administradorId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoria_id", nullable = false)
	private Categoria categoria;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "direccion_id", nullable = false)
	private Direccion direccion;
}