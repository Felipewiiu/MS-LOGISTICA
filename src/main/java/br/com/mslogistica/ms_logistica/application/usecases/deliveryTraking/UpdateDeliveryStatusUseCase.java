package br.com.mslogistica.ms_logistica.application.usecases.deliveryTraking;

import br.com.mslogistica.ms_logistica.application.exeptions.NotFoundDeliveryTrakingExeption;
import br.com.mslogistica.ms_logistica.application.gateway.DeliveryTrakingRepositoryGateway;
import br.com.mslogistica.ms_logistica.domain.entities.DeliveryTraking;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateDeliveryStatusUseCase {

    private final DeliveryTrakingRepositoryGateway deliveryTrakingRepositoryGateway;

    public DeliveryTraking updateDeliveryStatus(Long code, String status) {

        if(!deliveryTrakingRepositoryGateway.existsByDeliveryTraking(code)) {
            throw new NotFoundDeliveryTrakingExeption(code);
        }

        return deliveryTrakingRepositoryGateway
                .updateDeliveryStatus(code, status);
    }
}
