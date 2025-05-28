package umanizales.edu.reservafacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umanizales.edu.reservafacil.model.Reserva;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
	Optional<Reserva> findByClienteIdAndNegocioIdAndFechaAndHora(
			Long clienteId, Long negocioId, LocalDate fecha, LocalTime hora);
}