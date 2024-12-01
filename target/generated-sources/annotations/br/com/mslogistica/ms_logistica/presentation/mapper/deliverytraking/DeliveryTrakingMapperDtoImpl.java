package br.com.mslogistica.ms_logistica.presentation.mapper.deliverytraking;

import br.com.mslogistica.ms_logistica.domain.entities.DeliveryTraking;
import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.DeliveryTrakingDto;
import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.DestinationDto;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-01T14:48:53-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.4.1 (Eclipse Adoptium)"
)
@Component
public class DeliveryTrakingMapperDtoImpl implements DeliveryTrakingMapperDto {

    @Autowired
    private DestinationMapper destinationMapper;

    @Override
    public DeliveryTrakingDto toDto(DeliveryTraking deliveryTraking) {
        if ( deliveryTraking == null ) {
            return null;
        }

        DestinationDto destination = null;
        Long deliveryPersonCode = null;
        Long routeCode = null;

        destination = destinationMapper.toDto( deliveryTraking.getDestination() );
        deliveryPersonCode = deliveryTraking.getDeliveryPersonCode();
        routeCode = deliveryTraking.getRouteCode();

        DeliveryTrakingDto deliveryTrakingDto = new DeliveryTrakingDto( deliveryPersonCode, routeCode, destination );

        return deliveryTrakingDto;
    }

    @Override
    public DeliveryTraking toDomain(DeliveryTrakingDto deliveryTrakingDto) {
        if ( deliveryTrakingDto == null ) {
            return null;
        }

        DeliveryTraking deliveryTraking = new DeliveryTraking();

        deliveryTraking.setDestination( destinationMapper.toDomain( deliveryTrakingDto.destination() ) );
        deliveryTraking.setDeliveryPersonCode( deliveryTrakingDto.deliveryPersonCode() );
        deliveryTraking.setRouteCode( deliveryTrakingDto.routeCode() );

        return deliveryTraking;
    }
}
