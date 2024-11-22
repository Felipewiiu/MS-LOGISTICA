package br.com.mslogistica.ms_logistica.infrastructure.mapper.deliverytraking;

import br.com.mslogistica.ms_logistica.domain.entities.DeliveryTraking;
import br.com.mslogistica.ms_logistica.domain.valueobjects.Destination;
import br.com.mslogistica.ms_logistica.infrastructure.mapper.deliveryperson.DeliveryPersonMapper;
import br.com.mslogistica.ms_logistica.infrastructure.persistence.entityJpa.deliveryperson.DeliveryPersonJpa;
import br.com.mslogistica.ms_logistica.infrastructure.persistence.entityJpa.deliverytraking.DeliveryTrakingJpa;
import br.com.mslogistica.ms_logistica.infrastructure.persistence.entityJpa.deliverytraking.DestinationJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = { CurrentLocationMapper.class, DeliveryPersonMapper.class })
public interface DeliveryTrakingMapper {

    @Mapping(target = "deliveryPersonCode", source = "deliveryPersonCode", qualifiedByName = "toDeliveryPersonJpa")
    DeliveryTrakingJpa toEntityJpa(DeliveryTraking entity);

    @Mapping(target = "deliveryPersonCode", source = "deliveryPersonCode", qualifiedByName = "toLong")
    DeliveryTraking toEntityDomain(DeliveryTrakingJpa deliveryTrakingJpa);

    @Named("toDeliveryPersonJpa")
    default DeliveryPersonJpa toDeliveryPersonJpa(Long deliveryPersonCode) {
        if (deliveryPersonCode == null) {
            return null;
        }
        DeliveryPersonJpa deliveryPersonJpa = new DeliveryPersonJpa();
        deliveryPersonJpa.setPersonCode(deliveryPersonCode);
        return deliveryPersonJpa;
    }

    @Named("toLong")
    default Long toLong(DeliveryPersonJpa deliveryPersonJpa) {
        return deliveryPersonJpa != null ? deliveryPersonJpa.getPersonCode() : null;
    }


}

