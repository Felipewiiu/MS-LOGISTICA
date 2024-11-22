package br.com.mslogistica.ms_logistica.application.usecases.deliveryTraking;

import br.com.mslogistica.ms_logistica.application.exeptions.NotFoundDeliveryTrakingExeption;
import br.com.mslogistica.ms_logistica.application.gateway.DeliveryTrakingRepositoryGateway;
import br.com.mslogistica.ms_logistica.domain.entities.DeliveryTraking;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindDeliveryTrakingByCodeUseCase {

    private final DeliveryTrakingRepositoryGateway deliveryTrakingRepositoryGateway;

    public DeliveryTraking findDeliveryTrakingByCode(Long code) {

        if(!deliveryTrakingRepositoryGateway.existsByDeliveryTraking(code)) {
            throw new NotFoundDeliveryTrakingExeption(code);
        }

        return deliveryTrakingRepositoryGateway.findDeliveryTrakingByCode(code);

    }
}
