package umanizales.edu.reservafacil.mapper;

import org.mapstruct.Mapper;
import umanizales.edu.reservafacil.dto.request.DireccionRequestDTO;
import umanizales.edu.reservafacil.dto.response.DireccionResponseDTO;
import umanizales.edu.reservafacil.model.Direccion;

@Mapper(componentModel = "spring")
public interface DireccionMapper {
	Direccion toEntity(DireccionRequestDTO direccionRequestDTO);

	DireccionResponseDTO toResponseDTO(Direccion direccion);
}