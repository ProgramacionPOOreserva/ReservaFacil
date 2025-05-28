package umanizales.edu.reservafacil.service;

import umanizales.edu.reservafacil.dto.request.NegocioRequestDTO;
import umanizales.edu.reservafacil.dto.response.NegocioResponseDTO;

import java.util.List;

public interface NegocioService {
	List<NegocioResponseDTO> getAllNegocios();

	NegocioResponseDTO createNegocio(NegocioRequestDTO negocioRequestDTO);

	NegocioResponseDTO updateNegocio(Long id, NegocioRequestDTO negocioRequestDTO);

	void deleteNegocio(Long id);

	NegocioResponseDTO getNegocioById(Long id);

}