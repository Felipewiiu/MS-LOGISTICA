package br.com.mslogistica.ms_logistica.infrastructure.mapper.deliverytraking;

import br.com.mslogistica.ms_logistica.domain.entities.DeliveryTraking;
import br.com.mslogistica.ms_logistica.domain.valueobjects.Destination;
import br.com.mslogistica.ms_logistica.infrastructure.persistence.entityJpa.deliverytraking.DeliveryTrakingJpa;
import br.com.mslogistica.ms_logistica.infrastructure.persistence.entityJpa.deliverytraking.DestinationJpa;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-24T23:33:03-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class DeliveryTrakingMapperImpl implements DeliveryTrakingMapper {

    @Autowired
    private CurrentLocationMapper currentLocationMapper;

    @Override
    public DeliveryTrakingJpa toEntityJpa(DeliveryTraking entity) {
        if ( entity == null ) {
            return null;
        }

        DeliveryTrakingJpa deliveryTrakingJpa = new DeliveryTrakingJpa();

        deliveryTrakingJpa.setDeliveryPersonCode( toDeliveryPersonJpa( entity.getDeliveryPersonCode() ) );
        deliveryTrakingJpa.setDeliveryCode( entity.getDeliveryCode() );
        deliveryTrakingJpa.setOrderCode( entity.getOrderCode() );
        deliveryTrakingJpa.setCreatedAt( entity.getCreatedAt() );
        deliveryTrakingJpa.setDeliveryStatus( entity.getDeliveryStatus() );
        deliveryTrakingJpa.setEstimatedDeliveryTime( entity.getEstimatedDeliveryTime() );
        deliveryTrakingJpa.setCurrentLocation( currentLocationMapper.toJpa( entity.getCurrentLocation() ) );
        deliveryTrakingJpa.setRouteCode( entity.getRouteCode() );
        deliveryTrakingJpa.setDestination( destinationToDestinationJpa( entity.getDestination() ) );

        return deliveryTrakingJpa;
    }

    @Override
    public DeliveryTraking toEntityDomain(DeliveryTrakingJpa deliveryTrakingJpa) {
        if ( deliveryTrakingJpa == null ) {
            return null;
        }

        DeliveryTraking deliveryTraking = new DeliveryTraking();

        deliveryTraking.setDeliveryPersonCode( toLong( deliveryTrakingJpa.getDeliveryPersonCode() ) );
        deliveryTraking.setDeliveryCode( deliveryTrakingJpa.getDeliveryCode() );
        deliveryTraking.setOrderCode( deliveryTrakingJpa.getOrderCode() );
        deliveryTraking.setCreatedAt( deliveryTrakingJpa.getCreatedAt() );
        deliveryTraking.setDeliveryStatus( deliveryTrakingJpa.getDeliveryStatus() );
        deliveryTraking.setEstimatedDeliveryTime( deliveryTrakingJpa.getEstimatedDeliveryTime() );
        deliveryTraking.setCurrentLocation( currentLocationMapper.toDomain( deliveryTrakingJpa.getCurrentLocation() ) );
        deliveryTraking.setRouteCode( deliveryTrakingJpa.getRouteCode() );
        deliveryTraking.setDestination( destinationJpaToDestination( deliveryTrakingJpa.getDestination() ) );

        return deliveryTraking;
    }

    protected DestinationJpa destinationToDestinationJpa(Destination destination) {
        if ( destination == null ) {
            return null;
        }

        DestinationJpa destinationJpa = new DestinationJpa();

        destinationJpa.setCep( destination.getCep() );
        destinationJpa.setNumber( destination.getNumber() );
        destinationJpa.setComplement( destination.getComplement() );
        destinationJpa.setCity( destination.getCity() );
        destinationJpa.setUF( destination.getUF() );

        return destinationJpa;
    }

    protected Destination destinationJpaToDestination(DestinationJpa destinationJpa) {
        if ( destinationJpa == null ) {
            return null;
        }

        Destination destination = new Destination();

        destination.setCep( destinationJpa.getCep() );
        destination.setNumber( destinationJpa.getNumber() );
        destination.setComplement( destinationJpa.getComplement() );
        destination.setCity( destinationJpa.getCity() );
        destination.setUF( destinationJpa.getUF() );

        return destination;
    }
}
