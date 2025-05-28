package umanizales.edu.reservafacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umanizales.edu.reservafacil.model.Categoria;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	Optional<Categoria> findByNombre(String nombre);
}