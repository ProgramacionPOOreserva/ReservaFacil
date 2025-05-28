package umanizales.edu.reservafacil.service;

import umanizales.edu.reservafacil.dto.request.CategoriaRequestDTO;
import umanizales.edu.reservafacil.dto.response.CategoriaResponseDTO;

import java.util.List;

public interface CategoriaService {
	List<CategoriaResponseDTO> getAllCategorias();

	CategoriaResponseDTO createCategoria(CategoriaRequestDTO categoriaRequestDTO);

	CategoriaResponseDTO updateCategoria(Long id, CategoriaRequestDTO categoriaRequestDTO);

	void deleteCategoria(Long id);

	CategoriaResponseDTO getCategoriaById(Long id);
}