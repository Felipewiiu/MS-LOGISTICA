package br.com.mslogistica.ms_logistica.application.usecases.deliveryTraking;

import br.com.mslogistica.ms_logistica.application.gateway.DeliveryTrakingRepositoryGateway;
import br.com.mslogistica.ms_logistica.application.validators.deliveryPerson.DeliveryPersonValidator;
import br.com.mslogistica.ms_logistica.application.validators.deliveryTraking.DeliveryTrakingValidator;
import br.com.mslogistica.ms_logistica.domain.entities.DeliveryTraking;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CreateDeliveryTrakingUseCase {

    private final DeliveryTrakingRepositoryGateway deliveryTrakingRepositoryGateway;
    private final List<DeliveryTrakingValidator> validators;

    public DeliveryTraking create(DeliveryTraking deliveryTraking, Long deliveryPersonCode) {

        validators.forEach(validator -> validator.validate(deliveryTraking));

        return deliveryTrakingRepositoryGateway.create(deliveryTraking, deliveryPersonCode);
    }
}
