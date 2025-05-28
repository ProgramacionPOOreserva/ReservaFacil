package umanizales.edu.reservafacil.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umanizales.edu.reservafacil.dto.request.CategoriaRequestDTO;
import umanizales.edu.reservafacil.dto.response.CategoriaResponseDTO;
import umanizales.edu.reservafacil.exception.ResourceNotFoundException;
import umanizales.edu.reservafacil.exception.ValidationException;
import umanizales.edu.reservafacil.mapper.CategoriaMapper;
import umanizales.edu.reservafacil.model.Categoria;
import umanizales.edu.reservafacil.repository.CategoriaRepository;
import umanizales.edu.reservafacil.service.CategoriaService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private CategoriaMapper categoriaMapper;

	@Override
	public List<CategoriaResponseDTO> getAllCategorias() {
		return categoriaRepository.findAll().stream()
				.map(categoriaMapper::toResponseDTO)
				.collect(Collectors.toList());
	}

	@Override
	public CategoriaResponseDTO createCategoria(CategoriaRequestDTO categoriaRequestDTO) {
		validateUniqueCategoriaName(categoriaRequestDTO.getNombre());
		Categoria categoria = categoriaMapper.toEntity(categoriaRequestDTO);
		Categoria savedCategoria = categoriaRepository.save(categoria);
		return categoriaMapper.toResponseDTO(savedCategoria);
	}

	@Override
	public CategoriaResponseDTO updateCategoria(Long id, CategoriaRequestDTO categoriaRequestDTO) {
		Categoria existingCategoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con id: " + id));
		if (!existingCategoria.getNombre().equals(categoriaRequestDTO.getNombre())) {
			validateUniqueCategoriaName(categoriaRequestDTO.getNombre());
		}
		existingCategoria.setNombre(categoriaRequestDTO.getNombre());
		Categoria updatedCategoria = categoriaRepository.save(existingCategoria);
		return categoriaMapper.toResponseDTO(updatedCategoria);
	}

	@Override
	public void deleteCategoria(Long id) {
		categoriaRepository.deleteById(id);
	}

	@Override
	public CategoriaResponseDTO getCategoriaById(Long id) {
		Categoria categoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con id: " + id));
		return categoriaMapper.toResponseDTO(categoria);
	}

	private void validateUniqueCategoriaName(String nombre) {
		categoriaRepository.findByNombre(nombre)
				.ifPresent(c -> {
					throw new ValidationException("Ya existe una categoría con este nombre");
				});
	}
}