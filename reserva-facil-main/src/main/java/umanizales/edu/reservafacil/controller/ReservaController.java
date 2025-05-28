package umanizales.edu.reservafacil.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import umanizales.edu.reservafacil.dto.request.ReservaRequestDTO;
import umanizales.edu.reservafacil.dto.response.ReservaResponseDTO;
import umanizales.edu.reservafacil.service.ReservaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ReservaController {

	private final ReservaService reservaService;

	public ReservaController(ReservaService reservaService) {
		this.reservaService = reservaService;
	}

	@Operation(summary = "Obtener todas las reservas", description = "Retorna una lista de todas las reservas registradas")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de reservas retornada exitosamente"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@Transactional(readOnly = true)
	@GetMapping("/reservas")
	public ResponseEntity<List<ReservaResponseDTO>> getAllReservas() {
		return new ResponseEntity<>(reservaService.getAllReservas(), HttpStatus.OK);
	}

	@Operation(summary = "Crear una nueva reserva", description = "Crea una nueva reserva en el sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Reserva creada exitosamente"),
			@ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@Transactional
	@PostMapping("/reservas")
	public ResponseEntity<ReservaResponseDTO> createReserva(@Valid @RequestBody ReservaRequestDTO reservaRequestDTO) {
		return new ResponseEntity<>(reservaService.createReserva(reservaRequestDTO), HttpStatus.CREATED);
	}

	@Operation(summary = "Actualizar una reserva existente", description = "Actualiza los datos de una reserva basada en su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Reserva actualizada exitosamente"),
			@ApiResponse(responseCode = "404", description = "Reserva no encontrada"),
			@ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@Transactional
	@PutMapping("/reservas/{id}")
	public ResponseEntity<ReservaResponseDTO> updateReserva(@PathVariable Long id, @Valid @RequestBody ReservaRequestDTO reservaRequestDTO) {
		return new ResponseEntity<>(reservaService.updateReserva(id, reservaRequestDTO), HttpStatus.OK);
	}

	@Operation(summary = "Eliminar una reserva", description = "Elimina una reserva basada en su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Reserva eliminada exitosamente"),
			@ApiResponse(responseCode = "404", description = "Reserva no encontrada"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@Transactional
	@DeleteMapping("/reservas/{id}")
	public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
		reservaService.deleteReserva(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Operation(summary = "Obtener una reserva por ID", description = "Retorna una reserva específica basada en su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Reserva encontrada"),
			@ApiResponse(responseCode = "404", description = "Reserva no encontrada"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@Transactional(readOnly = true)
	@GetMapping("/reservas/{id}")
	public ResponseEntity<ReservaResponseDTO> getReservaById(@PathVariable Long id) {
		return new ResponseEntity<>(reservaService.getReservaById(id), HttpStatus.OK);
	}
}