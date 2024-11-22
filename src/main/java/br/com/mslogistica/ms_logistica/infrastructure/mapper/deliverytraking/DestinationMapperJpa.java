package br.com.mslogistica.ms_logistica.infrastructure.mapper.deliverytraking;

import br.com.mslogistica.ms_logistica.domain.valueobjects.Destination;
import br.com.mslogistica.ms_logistica.infrastructure.persistence.entityJpa.deliverytraking.DestinationJpa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DestinationMapperJpa {

    Destination toDestination(DestinationJpa destinationJpa);

    DestinationJpa toDestinationJpa(Destination destination);
}
