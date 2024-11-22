package br.com.mslogistica.ms_logistica.application.usecases.deliveryPerson;

import br.com.mslogistica.ms_logistica.application.gateway.DeliveryPersonRepositoryGateway;
import br.com.mslogistica.ms_logistica.application.validators.deliveryPerson.IsValidDeliveryPersonValidator;
import br.com.mslogistica.ms_logistica.domain.entities.DeliveryPerson;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateDeliveryPersonUseCase {

    private final DeliveryPersonRepositoryGateway deliveryPersonRepositoryGateway;
    private final IsValidDeliveryPersonValidator isValidDeliveryPersonValidator;

    public DeliveryPerson updateDeliveryPerson(DeliveryPerson deliveryPerson) {

        isValidDeliveryPersonValidator.validate(deliveryPerson);
        return deliveryPersonRepositoryGateway.updatePersonDeliveryByCode(deliveryPerson);
    }
}
