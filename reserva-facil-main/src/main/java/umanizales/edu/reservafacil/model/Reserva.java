package umanizales.edu.reservafacil.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "reservas")
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDate fecha;

	@Column(nullable = false)
	private LocalTime hora;

	@Column(nullable = false)
	private Long negocioId;

	@Column(nullable = false)
	private Long clienteId;
}