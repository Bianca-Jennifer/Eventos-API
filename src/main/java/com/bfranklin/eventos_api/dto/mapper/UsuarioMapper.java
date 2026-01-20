package com.bfranklin.eventos_api.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.bfranklin.eventos_api.dto.UsuarioCreateDto;
import com.bfranklin.eventos_api.dto.UsuarioResponseDto;
import com.bfranklin.eventos_api.entity.Usuario;

public class UsuarioMapper {
	
	public static Usuario toUsuario(UsuarioCreateDto usuarioCreateDto) {
		return new ModelMapper().map(usuarioCreateDto, Usuario.class);
	}
	
	public static UsuarioResponseDto toUsuarioResponseDto(Usuario usuario) {
		String role = usuario.getRole().name().substring("ROLE_".length());
		
		PropertyMap<Usuario, UsuarioResponseDto> props = new PropertyMap<Usuario, UsuarioResponseDto>(){
			@Override
			protected void configure() {
				map().setRole(role);
			}
		};	
		
		ModelMapper mapper = new ModelMapper();
		mapper.addMappings(props);
		return mapper.map(usuario, UsuarioResponseDto.class);
		
	}
	
	public static List<UsuarioResponseDto> toListDto(List<Usuario> lista_usuarios){
		return lista_usuarios.stream().map(usuario -> toUsuarioResponseDto(usuario)).collect(Collectors.toList());
	}
}
