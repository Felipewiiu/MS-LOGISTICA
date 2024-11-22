package br.com.mslogistica.ms_logistica.application.validators.deliveryPerson;

import br.com.mslogistica.ms_logistica.application.exeptions.NotFoundDeliveryPersonExeption;
import br.com.mslogistica.ms_logistica.application.gateway.DeliveryPersonRepositoryGateway;
import br.com.mslogistica.ms_logistica.domain.entities.DeliveryPerson;
import br.com.mslogistica.ms_logistica.domain.entities.DeliveryTraking;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IsValidDeliveryPersonValidator {

    private final DeliveryPersonRepositoryGateway deliveryPersonRepositoryGateway;

    public void validate(DeliveryPerson deliveryTraking) {
        if(!deliveryPersonRepositoryGateway.existsByDeliveryPerson(deliveryTraking.getPersonCode())){
            throw new NotFoundDeliveryPersonExeption(deliveryTraking.getPersonCode());
        }
    }

}
