package br.com.mslogistica.ms_logistica.application.usecases.deliveryPerson;

import br.com.mslogistica.ms_logistica.application.gateway.DeliveryPersonRepositoryGateway;
import br.com.mslogistica.ms_logistica.domain.entities.DeliveryPerson;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindPersonDeliveryByCodeUseCase {

    private final DeliveryPersonRepositoryGateway deliveryPersonRepositoryGateway;

    public DeliveryPerson findDeliveryPersonByCode(Long code) {

        return deliveryPersonRepositoryGateway.findPersonDeliveryByCode(code);
    }

}
