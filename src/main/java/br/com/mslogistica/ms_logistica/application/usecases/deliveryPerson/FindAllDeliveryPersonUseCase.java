package br.com.mslogistica.ms_logistica.application.usecases.deliveryPerson;

import br.com.mslogistica.ms_logistica.application.gateway.DeliveryPersonRepositoryGateway;
import br.com.mslogistica.ms_logistica.domain.entities.DeliveryPerson;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FindAllDeliveryPersonUseCase {

    private final DeliveryPersonRepositoryGateway deliveryPersonRepositoryGateway;

    public List<DeliveryPerson> findAll() {
        return deliveryPersonRepositoryGateway.findAllDeliveryPersons();
    }
}
