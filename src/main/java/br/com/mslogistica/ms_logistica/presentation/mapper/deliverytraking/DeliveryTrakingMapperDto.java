package br.com.mslogistica.ms_logistica.presentation.mapper.deliverytraking;

import br.com.mslogistica.ms_logistica.domain.entities.DeliveryTraking;
import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.DeliveryTrakingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DestinationMapper.class, CurrentLocationMapperDto.class})
public interface DeliveryTrakingMapperDto {

    @Mapping(source = "destination", target = "destination")
    DeliveryTrakingDto toDto(DeliveryTraking deliveryTraking);

    @Mapping(source = "destination", target = "destination")
    DeliveryTraking toDomain(DeliveryTrakingDto deliveryTrakingDto);


}
