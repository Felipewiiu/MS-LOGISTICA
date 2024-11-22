package br.com.mslogistica.ms_logistica.application.usecases.deliveryPerson;

import br.com.mslogistica.ms_logistica.application.gateway.DeliveryPersonRepositoryGateway;
import br.com.mslogistica.ms_logistica.application.validators.deliveryPerson.DeliveryPersonValidator;
import br.com.mslogistica.ms_logistica.domain.entities.DeliveryPerson;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CreateDeliveryPersonUseCase {

    private final DeliveryPersonRepositoryGateway deliveryPersonRepositoryGateway;
    private final List<DeliveryPersonValidator> validators;

    public DeliveryPerson create(DeliveryPerson deliveryPerson) {

        validators.forEach(validator -> validator.validate(deliveryPerson));

        return deliveryPersonRepositoryGateway.create(deliveryPerson);
    }
}
