package br.com.mslogistica.ms_logistica.presentation.mapper.deliverytraking;

import br.com.mslogistica.ms_logistica.domain.valueobjects.CurrentLocation;
import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.CurrentLocationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CurrentLocationMapperDto {

    CurrentLocationDto toDto(CurrentLocation currentLocation);

    CurrentLocation toDomain(CurrentLocationDto currentLocationDto);
}
