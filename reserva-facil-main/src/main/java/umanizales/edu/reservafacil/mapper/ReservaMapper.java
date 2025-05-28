package umanizales.edu.reservafacil.mapper;

import org.mapstruct.Mapper;
import umanizales.edu.reservafacil.dto.request.ReservaRequestDTO;
import umanizales.edu.reservafacil.dto.response.ReservaResponseDTO;
import umanizales.edu.reservafacil.model.Reserva;

@Mapper(componentModel = "spring")
public interface ReservaMapper {
	Reserva toEntity(ReservaRequestDTO reservaRequestDTO);

	ReservaResponseDTO toResponseDTO(Reserva reserva);
}