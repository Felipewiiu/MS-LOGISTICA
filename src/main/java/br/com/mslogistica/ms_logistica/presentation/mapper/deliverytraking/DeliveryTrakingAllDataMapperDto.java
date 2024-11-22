package br.com.mslogistica.ms_logistica.presentation.mapper.deliverytraking;

import br.com.mslogistica.ms_logistica.domain.entities.DeliveryTraking;
import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.DeliveryTrakingAllDataDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DestinationMapper.class, CurrentLocationMapperDto.class})
public interface DeliveryTrakingAllDataMapperDto {

    @Mapping(source = "destination", target = "destination")
    DeliveryTraking toDomainDto(DeliveryTrakingAllDataDto deliveryTrakingAllDataDto);

    @Mapping(source = "destination", target = "destination")
    DeliveryTrakingAllDataDto toDto(DeliveryTraking deliveryTraking);
}
