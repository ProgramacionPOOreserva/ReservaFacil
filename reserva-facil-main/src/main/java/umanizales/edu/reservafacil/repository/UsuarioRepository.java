package umanizales.edu.reservafacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umanizales.edu.reservafacil.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	List<Usuario> findAll();

	Optional<Usuario> findById(Long id);

	Usuario save(Usuario usuario);

	void deleteById(Long id);
}