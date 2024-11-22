package br.com.mslogistica.ms_logistica.infrastructure.mapper.deliveryperson;

import br.com.mslogistica.ms_logistica.domain.entities.DeliveryPerson;
import br.com.mslogistica.ms_logistica.infrastructure.mapper.deliverytraking.DeliveryTrakingMapper;
import br.com.mslogistica.ms_logistica.infrastructure.persistence.entityJpa.deliveryperson.DeliveryPersonJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = DeliveryTrakingMapper.class)
public interface DeliveryPersonMapper {

    @Mapping(target = "deliveryTrakings", source = "deliveryPerson.deliveryTrakings")
    DeliveryPerson toDomain(DeliveryPersonJpa deliveryPerson);

    @Mapping(target = "deliveryTrakings", source = "deliveryPerson.deliveryTrakings")
    DeliveryPersonJpa toJpa(DeliveryPerson deliveryPerson);
}
