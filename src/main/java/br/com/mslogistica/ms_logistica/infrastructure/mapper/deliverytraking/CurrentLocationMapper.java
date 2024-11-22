package br.com.mslogistica.ms_logistica.infrastructure.mapper.deliverytraking;

import br.com.mslogistica.ms_logistica.domain.valueobjects.CurrentLocation;
import br.com.mslogistica.ms_logistica.infrastructure.persistence.entityJpa.deliverytraking.CurrentLocationJpa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CurrentLocationMapper {

    CurrentLocationJpa toJpa(CurrentLocation currentLocation);

    CurrentLocation toDomain(CurrentLocationJpa currentLocation);
}
