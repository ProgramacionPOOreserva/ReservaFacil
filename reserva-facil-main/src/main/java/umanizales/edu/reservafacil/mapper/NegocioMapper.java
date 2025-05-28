package umanizales.edu.reservafacil.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import umanizales.edu.reservafacil.dto.request.NegocioRequestDTO;
import umanizales.edu.reservafacil.dto.response.NegocioResponseDTO;
import umanizales.edu.reservafacil.model.Negocio;

@Mapper(componentModel = "spring", uses = {CategoriaMapper.class, DireccionMapper.class})
public interface NegocioMapper {
	@Mapping(target = "categoria.id", source = "categoriaId")
	Negocio toEntity(NegocioRequestDTO negocioRequestDTO);

	NegocioResponseDTO toResponseDTO(Negocio negocio);
}