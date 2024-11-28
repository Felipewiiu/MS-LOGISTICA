package br.com.mslogistica.ms_logistica.presentation.mapper.deliverytraking;

import br.com.mslogistica.ms_logistica.domain.entities.DeliveryTraking;
import br.com.mslogistica.ms_logistica.domain.enums.DeliveryStatus;
import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.CurrentLocationDto;
import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.DeliveryTrakingAllDataDto;
import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.DestinationDto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-23T21:45:25-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class DeliveryTrakingAllDataMapperDtoImpl implements DeliveryTrakingAllDataMapperDto {

    @Autowired
    private DestinationMapper destinationMapper;
    @Autowired
    private CurrentLocationMapperDto currentLocationMapperDto;

    @Override
    public DeliveryTraking toDomainDto(DeliveryTrakingAllDataDto deliveryTrakingAllDataDto) {
        if ( deliveryTrakingAllDataDto == null ) {
            return null;
        }

        DeliveryTraking deliveryTraking = new DeliveryTraking();

        deliveryTraking.setDestination( destinationMapper.toDomain( deliveryTrakingAllDataDto.destination() ) );
        deliveryTraking.setDeliveryCode( deliveryTrakingAllDataDto.deliveryCode() );
        deliveryTraking.setDeliveryPersonCode( deliveryTrakingAllDataDto.deliveryPersonCode() );
        deliveryTraking.setOrderCode( deliveryTrakingAllDataDto.orderCode() );
        deliveryTraking.setCreatedAt( deliveryTrakingAllDataDto.createdAt() );
        deliveryTraking.setDeliveryStatus( deliveryTrakingAllDataDto.deliveryStatus() );
        deliveryTraking.setEstimatedDeliveryTime( deliveryTrakingAllDataDto.estimatedDeliveryTime() );
        deliveryTraking.setCurrentLocation( currentLocationMapperDto.toDomain( deliveryTrakingAllDataDto.currentLocation() ) );
        deliveryTraking.setRouteCode( deliveryTrakingAllDataDto.routeCode() );

        return deliveryTraking;
    }

    @Override
    public DeliveryTrakingAllDataDto toDto(DeliveryTraking deliveryTraking) {
        if ( deliveryTraking == null ) {
            return null;
        }

        DestinationDto destination = null;
        Long deliveryCode = null;
        Long deliveryPersonCode = null;
        Long orderCode = null;
        LocalDateTime createdAt = null;
        DeliveryStatus deliveryStatus = null;
        LocalDate estimatedDeliveryTime = null;
        CurrentLocationDto currentLocation = null;
        Long routeCode = null;

        destination = destinationMapper.toDto( deliveryTraking.getDestination() );
        deliveryCode = deliveryTraking.getDeliveryCode();
        deliveryPersonCode = deliveryTraking.getDeliveryPersonCode();
        orderCode = deliveryTraking.getOrderCode();
        createdAt = deliveryTraking.getCreatedAt();
        deliveryStatus = deliveryTraking.getDeliveryStatus();
        estimatedDeliveryTime = deliveryTraking.getEstimatedDeliveryTime();
        currentLocation = currentLocationMapperDto.toDto( deliveryTraking.getCurrentLocation() );
        routeCode = deliveryTraking.getRouteCode();

        DeliveryTrakingAllDataDto deliveryTrakingAllDataDto = new DeliveryTrakingAllDataDto( deliveryCode, deliveryPersonCode, orderCode, createdAt, deliveryStatus, estimatedDeliveryTime, currentLocation, routeCode, destination );

        return deliveryTrakingAllDataDto;
    }
}
