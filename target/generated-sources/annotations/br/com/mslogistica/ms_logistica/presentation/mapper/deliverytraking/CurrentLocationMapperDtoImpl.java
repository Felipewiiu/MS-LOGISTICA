package br.com.mslogistica.ms_logistica.presentation.mapper.deliverytraking;

import br.com.mslogistica.ms_logistica.domain.valueobjects.CurrentLocation;
import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.CurrentLocationDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-01T14:48:53-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.4.1 (Eclipse Adoptium)"
)
@Component
public class CurrentLocationMapperDtoImpl implements CurrentLocationMapperDto {

    @Override
    public CurrentLocationDto toDto(CurrentLocation currentLocation) {
        if ( currentLocation == null ) {
            return null;
        }

        double latitude = 0.0d;
        double longitude = 0.0d;

        latitude = currentLocation.getLatitude();
        longitude = currentLocation.getLongitude();

        CurrentLocationDto currentLocationDto = new CurrentLocationDto( latitude, longitude );

        return currentLocationDto;
    }

    @Override
    public CurrentLocation toDomain(CurrentLocationDto currentLocationDto) {
        if ( currentLocationDto == null ) {
            return null;
        }

        CurrentLocation currentLocation = new CurrentLocation();

        currentLocation.setLatitude( currentLocationDto.latitude() );
        currentLocation.setLongitude( currentLocationDto.longitude() );

        return currentLocation;
    }
}
