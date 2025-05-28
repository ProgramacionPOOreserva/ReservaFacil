package umanizales.edu.reservafacil.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import umanizales.edu.reservafacil.dto.request.UsuarioRequestDTO;
import umanizales.edu.reservafacil.dto.response.UsuarioResponseDTO;
import umanizales.edu.reservafacil.exception.ResourceNotFoundException;
import umanizales.edu.reservafacil.exception.ValidationException;
import umanizales.edu.reservafacil.mapper.UsuarioMapper;
import umanizales.edu.reservafacil.model.Usuario;
import umanizales.edu.reservafacil.repository.UsuarioRepository;
import umanizales.edu.reservafacil.service.UsuarioService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioMapper usuarioMapper;

	@Override
	public List<UsuarioResponseDTO> getAllUsuarios() {
		return usuarioRepository.findAll().stream()
				.map(usuarioMapper::toResponseDTO)
				.collect(Collectors.toList());
	}

	@Override
	public UsuarioResponseDTO createUsuario(UsuarioRequestDTO usuarioRequestDTO) {
		validateUniqueCorreo(usuarioRequestDTO.getCorreo());
		validateRole(usuarioRequestDTO.getRol()); // Nueva validación
		try {
			Usuario usuario = usuarioMapper.toEntity(usuarioRequestDTO);
			Usuario savedUsuario = usuarioRepository.save(usuario);
			return usuarioMapper.toResponseDTO(savedUsuario);
		} catch (DataIntegrityViolationException e) {
			throw new ValidationException("El correo ya está en uso");
		}
	}

	@Override
	public UsuarioResponseDTO updateUsuario(Long id, UsuarioRequestDTO usuarioRequestDTO) {
		Usuario existingUsuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
		existingUsuario.setNombre(usuarioRequestDTO.getNombre());
		existingUsuario.setTelefono(usuarioRequestDTO.getTelefono());
		existingUsuario.setContrasenia(usuarioRequestDTO.getContrasenia());
		existingUsuario.setRol(usuarioRequestDTO.getRol());
		if (!existingUsuario.getCorreo().equals(usuarioRequestDTO.getCorreo())) {
			validateUniqueCorreo(usuarioRequestDTO.getCorreo());
		}
		validateRole(usuarioRequestDTO.getRol());
		existingUsuario.setCorreo(usuarioRequestDTO.getCorreo());
		try {
			Usuario updatedUsuario = usuarioRepository.save(existingUsuario);
			return usuarioMapper.toResponseDTO(updatedUsuario);
		} catch (DataIntegrityViolationException e) {
			throw new ValidationException("El correo ya está en uso");
		}
	}

	@Override
	public void deleteUsuario(Long id) {
		if (!usuarioRepository.existsById(id)) {
			throw new ResourceNotFoundException("Usuario no encontrado con id: " + id);
		}
		usuarioRepository.deleteById(id);
	}

	@Override
	public UsuarioResponseDTO getUsuarioById(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (usuario.isEmpty()) {
			throw new ResourceNotFoundException("Usuario no encontrado con id: " + id);
		}
		return usuarioMapper.toResponseDTO(usuario.get());
	}

	private void validateUniqueCorreo(String correo) {
		List<Usuario> usuarios = usuarioRepository.findAll();
		for (Usuario u : usuarios) {
			if (u.getCorreo().equals(correo)) {
				throw new ValidationException("El correo ya está en uso");
			}
		}
	}

	private void validateRole(String rol) {
		if (!("ADMIN".equals(rol) || "CLIENTE".equals(rol))) {
			throw new ValidationException("El rol debe ser 'ADMIN' o 'CLIENTE'");
		}
	}
}