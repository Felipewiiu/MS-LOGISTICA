package br.com.mslogistica.ms_logistica.application.usecases.deliveryPerson;

import br.com.mslogistica.ms_logistica.application.gateway.DeliveryPersonRepositoryGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteDeliveryPersonByCodeUseCase {

    private final DeliveryPersonRepositoryGateway deliveryPersonRepositoryGateway;

    public void deleteDeliveryPersonByCode(Long code) {

        deliveryPersonRepositoryGateway.deletePersonDeliveryByCode(code);
    }
}
