package br.com.mslogistica.ms_logistica.application.usecases.deliveryTraking;

import br.com.mslogistica.ms_logistica.application.exeptions.NotFoundDeliveryTrakingExeption;
import br.com.mslogistica.ms_logistica.application.gateway.DeliveryTrakingRepositoryGateway;
import br.com.mslogistica.ms_logistica.domain.entities.DeliveryTraking;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class UpdateEstimativeDeliveryTime {

    private final DeliveryTrakingRepositoryGateway deliveryTrakingRepositoryGateway;

    public DeliveryTraking updateEstimativeDeliveryTime(Long deliveryTrakingCode, LocalDate date) {

        if(!deliveryTrakingRepositoryGateway.existsByDeliveryTraking(deliveryTrakingCode)) {
            throw new NotFoundDeliveryTrakingExeption(deliveryTrakingCode);
        }

        return deliveryTrakingRepositoryGateway.updateEstimativeDeliveryTime(deliveryTrakingCode, date);
    }
}
