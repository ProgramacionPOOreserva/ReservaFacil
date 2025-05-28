package umanizales.edu.reservafacil.mapper;

import org.mapstruct.Mapper;
import umanizales.edu.reservafacil.dto.request.UsuarioRequestDTO;
import umanizales.edu.reservafacil.dto.response.UsuarioResponseDTO;
import umanizales.edu.reservafacil.model.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
	Usuario toEntity(UsuarioRequestDTO usuarioRequestDTO);
	UsuarioResponseDTO toResponseDTO(Usuario usuario);
}