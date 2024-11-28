package br.com.mslogistica.ms_logistica.infrastructure.mapper.deliverytraking;

import br.com.mslogistica.ms_logistica.domain.valueobjects.CurrentLocation;
import br.com.mslogistica.ms_logistica.infrastructure.persistence.entityJpa.deliverytraking.CurrentLocationJpa;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-23T21:45:25-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class CurrentLocationMapperImpl implements CurrentLocationMapper {

    @Override
    public CurrentLocationJpa toJpa(CurrentLocation currentLocation) {
        if ( currentLocation == null ) {
            return null;
        }

        CurrentLocationJpa currentLocationJpa = new CurrentLocationJpa();

        currentLocationJpa.setLatitude( currentLocation.getLatitude() );
        currentLocationJpa.setLongitude( currentLocation.getLongitude() );

        return currentLocationJpa;
    }

    @Override
    public CurrentLocation toDomain(CurrentLocationJpa currentLocation) {
        if ( currentLocation == null ) {
            return null;
        }

        CurrentLocation currentLocation1 = new CurrentLocation();

        currentLocation1.setLatitude( currentLocation.getLatitude() );
        currentLocation1.setLongitude( currentLocation.getLongitude() );

        return currentLocation1;
    }
}
