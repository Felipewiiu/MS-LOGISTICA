package br.com.mslogistica.ms_logistica.application.usecases.deliveryTraking;

import br.com.mslogistica.ms_logistica.application.gateway.DeliveryTrakingRepositoryGateway;
import br.com.mslogistica.ms_logistica.domain.entities.DeliveryTraking;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FindAllDeliveryTrakingUseCase {

    private final DeliveryTrakingRepositoryGateway deliveryTrakingRepositoryGateway;

    public List<DeliveryTraking> findAllDeliveryTraking() {

        return deliveryTrakingRepositoryGateway.findAllDeliveryTraking();
    }
}
