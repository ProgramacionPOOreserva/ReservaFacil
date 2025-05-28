package umanizales.edu.reservafacil.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umanizales.edu.reservafacil.dto.request.NegocioRequestDTO;
import umanizales.edu.reservafacil.dto.response.NegocioResponseDTO;
import umanizales.edu.reservafacil.exception.ResourceNotFoundException;
import umanizales.edu.reservafacil.exception.ValidationException;
import umanizales.edu.reservafacil.mapper.NegocioMapper;
import umanizales.edu.reservafacil.model.Categoria;
import umanizales.edu.reservafacil.model.Negocio;
import umanizales.edu.reservafacil.model.Usuario;
import umanizales.edu.reservafacil.repository.CategoriaRepository;
import umanizales.edu.reservafacil.repository.NegocioRepository;
import umanizales.edu.reservafacil.repository.UsuarioRepository;
import umanizales.edu.reservafacil.service.NegocioService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NegocioServiceImpl implements NegocioService {

	@Autowired
	private NegocioRepository negocioRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private NegocioMapper negocioMapper;

	@Override
	public List<NegocioResponseDTO> getAllNegocios() {
		return negocioRepository.findAll().stream()
				.map(negocioMapper::toResponseDTO)
				.collect(Collectors.toList());
	}

	@Override
	public NegocioResponseDTO createNegocio(NegocioRequestDTO negocioRequestDTO) {
		validateAdminRole(negocioRequestDTO.getAdministradorId());
		validateUniqueNegocioName(negocioRequestDTO.getNombre(), negocioRequestDTO.getAdministradorId());
		validateCategoriaExists(negocioRequestDTO.getCategoriaId());
		Negocio negocio = negocioMapper.toEntity(negocioRequestDTO);
		Negocio savedNegocio = negocioRepository.save(negocio);
		return negocioMapper.toResponseDTO(savedNegocio);
	}

	@Override
	public NegocioResponseDTO updateNegocio(Long id, NegocioRequestDTO negocioRequestDTO) {
		Negocio existingNegocio = negocioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Negocio no encontrado con id: " + id));
		validateAdminRole(negocioRequestDTO.getAdministradorId());
		if (!existingNegocio.getNombre().equals(negocioRequestDTO.getNombre()) ||
				!existingNegocio.getAdministradorId().equals(negocioRequestDTO.getAdministradorId())) {
			validateUniqueNegocioName(negocioRequestDTO.getNombre(), negocioRequestDTO.getAdministradorId());
		}
		validateCategoriaExists(negocioRequestDTO.getCategoriaId());
		existingNegocio.setNombre(negocioRequestDTO.getNombre());
		existingNegocio.setDescripcion(negocioRequestDTO.getDescripcion());
		existingNegocio.setAdministradorId(negocioRequestDTO.getAdministradorId());
		existingNegocio.setCategoria(negocioMapper.toEntity(negocioRequestDTO).getCategoria());
		existingNegocio.setDireccion(negocioMapper.toEntity(negocioRequestDTO).getDireccion());
		Negocio updatedNegocio = negocioRepository.save(existingNegocio);
		return negocioMapper.toResponseDTO(updatedNegocio);
	}

	@Override
	public void deleteNegocio(Long id) {
		negocioRepository.deleteById(id);
	}

	@Override
	public NegocioResponseDTO getNegocioById(Long id) {
		Negocio negocio = negocioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Negocio no encontrado con id: " + id));
		return negocioMapper.toResponseDTO(negocio);
	}

	private void validateAdminRole(Long administradorId) {
		usuarioRepository.findById(administradorId)
				.filter(u -> "ADMIN".equals(u.getRol()))
				.orElseThrow(() -> new ValidationException("Solo un administrador puede crear o actualizar un negocio"));
	}

	private void validateUniqueNegocioName(String nombre, Long administradorId) {
		negocioRepository.findByNombreAndAdministradorId(nombre, administradorId)
				.ifPresent(n -> { throw new ValidationException("Ya existe un negocio con este nombre para este administrador"); });
	}

	private void validateCategoriaExists(Long categoriaId) {
		Categoria categoria = categoriaRepository.findById(categoriaId)
				.orElseThrow(() -> new ValidationException("La categoría con id " + categoriaId + " no existe"));
	}
}