package umanizales.edu.reservafacil.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import umanizales.edu.reservafacil.dto.request.CategoriaRequestDTO;
import umanizales.edu.reservafacil.dto.response.CategoriaResponseDTO;
import umanizales.edu.reservafacil.service.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CategoriaController {

	private final CategoriaService categoriaService;

	public CategoriaController(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	@Operation(summary = "Obtener todas las categorías", description = "Retorna una lista de todas las categorías registradas")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de categorías retornada exitosamente"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@Transactional(readOnly = true)
	@GetMapping("/categorias")
	public ResponseEntity<List<CategoriaResponseDTO>> getAllCategorias() {
		return new ResponseEntity<>(categoriaService.getAllCategorias(), HttpStatus.OK);
	}

	@Operation(summary = "Crear una nueva categoría", description = "Crea una nueva categoría en el sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Categoría creada exitosamente"),
			@ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@Transactional
	@PostMapping("/categorias")
	public ResponseEntity<CategoriaResponseDTO> createCategoria(@Valid @RequestBody CategoriaRequestDTO categoriaRequestDTO) {
		return new ResponseEntity<>(categoriaService.createCategoria(categoriaRequestDTO), HttpStatus.CREATED);
	}

	@Operation(summary = "Actualizar una categoría existente", description = "Actualiza los datos de una categoría basada en su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Categoría actualizada exitosamente"),
			@ApiResponse(responseCode = "404", description = "Categoría no encontrada"),
			@ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@Transactional
	@PutMapping("/categorias/{id}")
	public ResponseEntity<CategoriaResponseDTO> updateCategoria(@PathVariable Long id, @Valid @RequestBody CategoriaRequestDTO categoriaRequestDTO) {
		return new ResponseEntity<>(categoriaService.updateCategoria(id, categoriaRequestDTO), HttpStatus.OK);
	}

	@Operation(summary = "Eliminar una categoría", description = "Elimina una categoría basada en su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Categoría eliminada exitosamente"),
			@ApiResponse(responseCode = "404", description = "Categoría no encontrada"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@Transactional
	@DeleteMapping("/categorias/{id}")
	public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
		categoriaService.deleteCategoria(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Operation(summary = "Obtener una categoría por ID", description = "Retorna una categoría específica basada en su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Categoría encontrada"),
			@ApiResponse(responseCode = "404", description = "Categoría no encontrada"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@Transactional(readOnly = true)
	@GetMapping("/categorias/{id}")
	public ResponseEntity<CategoriaResponseDTO> getCategoriaById(@PathVariable Long id) {
		return new ResponseEntity<>(categoriaService.getCategoriaById(id), HttpStatus.OK);
	}
}