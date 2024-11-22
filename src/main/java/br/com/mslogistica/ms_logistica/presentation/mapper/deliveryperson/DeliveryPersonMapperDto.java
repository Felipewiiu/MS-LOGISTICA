package br.com.mslogistica.ms_logistica.presentation.mapper.deliveryperson;

import br.com.mslogistica.ms_logistica.domain.entities.DeliveryPerson;
import br.com.mslogistica.ms_logistica.presentation.dto.deliveryperson.DeliveryPersonDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeliveryPersonMapperDto {

    DeliveryPerson toDomain(DeliveryPersonDto deliveryPersonDto);

    DeliveryPersonDto toDto(DeliveryPerson deliveryPerson);
}
