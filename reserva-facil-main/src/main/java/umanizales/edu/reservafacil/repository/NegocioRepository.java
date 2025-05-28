package umanizales.edu.reservafacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umanizales.edu.reservafacil.model.Negocio;

import java.util.Optional;

public interface NegocioRepository extends JpaRepository<Negocio, Long> {
	Optional<Negocio> findByNombreAndAdministradorId(String nombre, Long administradorId);
}