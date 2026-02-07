package com.bfranklin.eventos_api.dto.mapper;

import org.modelmapper.ModelMapper;

import com.bfranklin.eventos_api.dto.PerfilCreateDto;
import com.bfranklin.eventos_api.dto.PerfilResponseDto;
import com.bfranklin.eventos_api.entity.Perfil;

public class PerfilMapper {

	private PerfilMapper() {}
	
	public static Perfil toPerfil(PerfilCreateDto dto) {
		return new ModelMapper().map(dto, Perfil.class);
	}
	
	public static PerfilResponseDto toDto(Perfil perfil) {
		return new ModelMapper().map(perfil, PerfilResponseDto.class);
	}
}
