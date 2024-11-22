package br.com.mslogistica.ms_logistica.presentation.mapper.deliverytraking;

import br.com.mslogistica.ms_logistica.domain.valueobjects.Destination;
import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.DestinationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DestinationMapper {

    DestinationDto toDto(Destination destination);

    Destination toDomain(DestinationDto destinationDto);
}