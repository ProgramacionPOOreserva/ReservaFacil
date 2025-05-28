package umanizales.edu.reservafacil.service;

import umanizales.edu.reservafacil.dto.request.UsuarioRequestDTO;
import umanizales.edu.reservafacil.dto.response.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {
	List<UsuarioResponseDTO> getAllUsuarios();

	UsuarioResponseDTO createUsuario(UsuarioRequestDTO usuarioRequestDTO);

	UsuarioResponseDTO updateUsuario(Long id, UsuarioRequestDTO usuarioRequestDTO);

	void deleteUsuario(Long id);

	UsuarioResponseDTO getUsuarioById(Long id);
}