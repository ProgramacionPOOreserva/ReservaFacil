package umanizales.edu.reservafacil.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umanizales.edu.reservafacil.dto.request.ReservaRequestDTO;
import umanizales.edu.reservafacil.dto.response.ReservaResponseDTO;
import umanizales.edu.reservafacil.exception.ResourceNotFoundException;
import umanizales.edu.reservafacil.exception.ValidationException;
import umanizales.edu.reservafacil.mapper.ReservaMapper;
import umanizales.edu.reservafacil.model.Reserva;
import umanizales.edu.reservafacil.repository.ReservaRepository;
import umanizales.edu.reservafacil.service.ReservaService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaServiceImpl implements ReservaService {

	@Autowired
	private ReservaRepository reservaRepository;

	@Autowired
	private ReservaMapper reservaMapper;

	@Override
	public List<ReservaResponseDTO> getAllReservas() {
		return reservaRepository.findAll().stream()
				.map(reservaMapper::toResponseDTO)
				.collect(Collectors.toList());
	}

	@Override
	public ReservaResponseDTO createReserva(ReservaRequestDTO reservaRequestDTO) {
		LocalDate fechaActual = LocalDate.now();
		LocalTime horaActual = LocalTime.now();
		validateNoDuplicateReservation(
				reservaRequestDTO.getClienteId(),
				reservaRequestDTO.getNegocioId(),
				fechaActual,
				horaActual
		);
		Reserva reserva = reservaMapper.toEntity(reservaRequestDTO);
		reserva.setFecha(fechaActual);
		reserva.setHora(horaActual);
		Reserva savedReserva = reservaRepository.save(reserva);
		return reservaMapper.toResponseDTO(savedReserva);
	}

	@Override
	public ReservaResponseDTO updateReserva(Long id, ReservaRequestDTO reservaRequestDTO) {
		Reserva existingReserva = reservaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con id: " + id));
		if (!existingReserva.getClienteId().equals(reservaRequestDTO.getClienteId()) ||
				!existingReserva.getNegocioId().equals(reservaRequestDTO.getNegocioId())) {
			validateNoDuplicateReservation(
					reservaRequestDTO.getClienteId(),
					reservaRequestDTO.getNegocioId(),
					existingReserva.getFecha(),
					existingReserva.getHora()
			);
		}
		existingReserva.setNegocioId(reservaRequestDTO.getNegocioId());
		existingReserva.setClienteId(reservaRequestDTO.getClienteId());
		Reserva updatedReserva = reservaRepository.save(existingReserva);
		return reservaMapper.toResponseDTO(updatedReserva);
	}

	@Override
	public void deleteReserva(Long id) {
		reservaRepository.deleteById(id);
	}

	@Override
	public ReservaResponseDTO getReservaById(Long id) {
		Reserva reserva = reservaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con id: " + id));
		return reservaMapper.toResponseDTO(reserva);
	}

	private void validateFutureDate(LocalDate fecha) {
		if (fecha.isBefore(LocalDate.now())) {
			throw new ValidationException("La fecha de la reserva debe ser futura");
		}
	}

	private void validateNoDuplicateReservation(Long clienteId, Long negocioId, LocalDate fecha, LocalTime hora) {
		reservaRepository.findByClienteIdAndNegocioIdAndFechaAndHora(clienteId, negocioId, fecha, hora)
				.ifPresent(r -> {
					throw new ValidationException("El cliente ya tiene una reserva en este negocio a la misma hora");
				});
	}
}