package umanizales.edu.reservafacil.mapper;

import org.mapstruct.Mapper;
import umanizales.edu.reservafacil.dto.request.CategoriaRequestDTO;
import umanizales.edu.reservafacil.dto.response.CategoriaResponseDTO;
import umanizales.edu.reservafacil.model.Categoria;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
	Categoria toEntity(CategoriaRequestDTO categoriaRequestDTO);

	CategoriaResponseDTO toResponseDTO(Categoria categoria);
}