package umanizales.edu.reservafacil.service;

import umanizales.edu.reservafacil.dto.request.ReservaRequestDTO;
import umanizales.edu.reservafacil.dto.response.ReservaResponseDTO;

import java.util.List;

public interface ReservaService {
	List<ReservaResponseDTO> getAllReservas();

	ReservaResponseDTO createReserva(ReservaRequestDTO reservaRequestDTO);

	ReservaResponseDTO updateReserva(Long id, ReservaRequestDTO reservaRequestDTO);

	void deleteReserva(Long id);

	ReservaResponseDTO getReservaById(Long id);
}