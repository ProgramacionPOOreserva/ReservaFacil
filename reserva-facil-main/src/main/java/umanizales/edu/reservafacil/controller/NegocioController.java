package umanizales.edu.reservafacil.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import umanizales.edu.reservafacil.dto.request.NegocioRequestDTO;
import umanizales.edu.reservafacil.dto.response.NegocioResponseDTO;
import umanizales.edu.reservafacil.service.NegocioService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class NegocioController {

	private final NegocioService negocioService;

	public NegocioController(NegocioService negocioService) {
		this.negocioService = negocioService;
	}

	@Operation(summary = "Obtener todos los negocios", description = "Retorna una lista de todos los negocios registrados")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de negocios retornada exitosamente"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@Transactional(readOnly = true)
	@GetMapping("/negocios")
	public ResponseEntity<List<NegocioResponseDTO>> getAllNegocios() {
		return new ResponseEntity<>(negocioService.getAllNegocios(), HttpStatus.OK);
	}

	@Operation(summary = "Crear un nuevo negocio", description = "Crea un nuevo negocio en el sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Negocio creado exitosamente"),
			@ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@Transactional
	@PostMapping("/negocios")
	public ResponseEntity<NegocioResponseDTO> createNegocio(@Valid @RequestBody NegocioRequestDTO negocioRequestDTO) {
		return new ResponseEntity<>(negocioService.createNegocio(negocioRequestDTO), HttpStatus.CREATED);
	}

	@Operation(summary = "Actualizar un negocio existente", description = "Actualiza los datos de un negocio basado en su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Negocio actualizado exitosamente"),
			@ApiResponse(responseCode = "404", description = "Negocio no encontrado"),
			@ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@Transactional
	@PutMapping("/negocios/{id}")
	public ResponseEntity<NegocioResponseDTO> updateNegocio(@PathVariable Long id, @Valid @RequestBody NegocioRequestDTO negocioRequestDTO) {
		return new ResponseEntity<>(negocioService.updateNegocio(id, negocioRequestDTO), HttpStatus.OK);
	}

	@Operation(summary = "Eliminar un negocio", description = "Elimina un negocio basado en su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Negocio eliminado exitosamente"),
			@ApiResponse(responseCode = "404", description = "Negocio no encontrado"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@Transactional
	@DeleteMapping("/negocios/{id}")
	public ResponseEntity<Void> deleteNegocio(@PathVariable Long id) {
		negocioService.deleteNegocio(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Operation(summary = "Obtener un negocio por ID", description = "Retorna un negocio específico basado en su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Negocio encontrado"),
			@ApiResponse(responseCode = "404", description = "Negocio no encontrado"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@Transactional(readOnly = true)
	@GetMapping("/negocios/{id}")
	public ResponseEntity<NegocioResponseDTO> getNegocioById(@PathVariable Long id) {
		return new ResponseEntity<>(negocioService.getNegocioById(id), HttpStatus.OK);
	}
}