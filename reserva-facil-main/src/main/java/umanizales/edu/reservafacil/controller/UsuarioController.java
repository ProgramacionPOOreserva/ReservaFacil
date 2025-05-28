package umanizales.edu.reservafacil.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import umanizales.edu.reservafacil.dto.request.UsuarioRequestDTO;
import umanizales.edu.reservafacil.dto.response.UsuarioResponseDTO;
import umanizales.edu.reservafacil.exception.ValidationException;
import umanizales.edu.reservafacil.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@Operation(summary = "Obtener todos los usuarios", description = "Retorna una lista de todos los usuarios registrados")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de usuarios retornada exitosamente"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@Transactional(readOnly = true)
	@GetMapping("/usuarios")
	public ResponseEntity<List<UsuarioResponseDTO>> getAllUsuarios() {
		return new ResponseEntity<>(usuarioService.getAllUsuarios(), HttpStatus.OK);
	}

	@Operation(summary = "Crear un nuevo usuario", description = "Crea un nuevo usuario en el sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
			@ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@Transactional
	@PostMapping("/usuarios")
	public ResponseEntity<UsuarioResponseDTO> createUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
		validateUsuario(usuarioRequestDTO);
		return new ResponseEntity<>(usuarioService.createUsuario(usuarioRequestDTO), HttpStatus.CREATED);
	}

	@Operation(summary = "Actualizar un usuario existente", description = "Actualiza los datos de un usuario basado en su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente"),
			@ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
			@ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@Transactional
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<UsuarioResponseDTO> updateUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
		validateUsuario(usuarioRequestDTO);
		return new ResponseEntity<>(usuarioService.updateUsuario(id, usuarioRequestDTO), HttpStatus.OK);
	}

	@Operation(summary = "Eliminar un usuario", description = "Elimina un usuario basado en su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Usuario eliminado exitosamente"),
			@ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@Transactional
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
		usuarioService.deleteUsuario(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Operation(summary = "Obtener un usuario por ID", description = "Retorna un usuario específico basado en su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuario encontrado"),
			@ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@Transactional(readOnly = true)
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<UsuarioResponseDTO> getUsuarioById(@PathVariable Long id) {
		return new ResponseEntity<>(usuarioService.getUsuarioById(id), HttpStatus.OK);
	}

	private void validateUsuario(UsuarioRequestDTO usuarioRequestDTO) {
		if (usuarioRequestDTO.getNombre() == null || usuarioRequestDTO.getNombre().isEmpty()) {
			throw new ValidationException("El nombre del usuario debe ser obligatorio");
		}
		if (usuarioRequestDTO.getNombre().length() > 50) {
			throw new ValidationException("El nombre del usuario no debe exceder los 50 caracteres");
		}
		if (usuarioRequestDTO.getCorreo() == null || usuarioRequestDTO.getCorreo().isEmpty()) {
			throw new ValidationException("El correo del usuario debe ser obligatorio");
		}
		if (usuarioRequestDTO.getRol() == null || usuarioRequestDTO.getRol().isEmpty()) {
			throw new ValidationException("El rol del usuario debe ser obligatorio");
		}
	}
}