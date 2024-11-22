package br.com.mslogistica.ms_logistica.application.usecases.deliveryTraking;


import br.com.mslogistica.ms_logistica.application.exeptions.NotFoundDeliveryTrakingExeption;
import br.com.mslogistica.ms_logistica.application.gateway.DeliveryTrakingRepositoryGateway;
import br.com.mslogistica.ms_logistica.domain.entities.DeliveryTraking;
import br.com.mslogistica.ms_logistica.domain.valueobjects.CurrentLocation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateCurrentLocation {

    private final DeliveryTrakingRepositoryGateway deliveryTrakingRepositoryGateway;

    public DeliveryTraking updateCurrentLocation(Long deliveryTrakingCode , CurrentLocation currentLocation) {

        if(!deliveryTrakingRepositoryGateway.existsByDeliveryTraking(deliveryTrakingCode)) {
            throw new NotFoundDeliveryTrakingExeption(deliveryTrakingCode);
        }

        return deliveryTrakingRepositoryGateway
                .updateCurrentLocation(deliveryTrakingCode, currentLocation);
    }
}
