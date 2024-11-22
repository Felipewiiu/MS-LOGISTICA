package br.com.mslogistica.ms_logistica.application.validators.deliveryPerson;

import br.com.mslogistica.ms_logistica.application.exeptions.NotFoundDeliveryPersonExeption;
import br.com.mslogistica.ms_logistica.application.gateway.DeliveryPersonRepositoryGateway;
import br.com.mslogistica.ms_logistica.application.validators.deliveryTraking.DeliveryTrakingValidator;
import br.com.mslogistica.ms_logistica.domain.entities.DeliveryTraking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
public class ExistDeliveryPeson implements DeliveryTrakingValidator {

    private final DeliveryPersonRepositoryGateway deliveryPersonRepositoryGateway;

    @Override
    public void validate(DeliveryTraking deliveryTraking) {
        if(!deliveryPersonRepositoryGateway.existsByDeliveryPerson(deliveryTraking.getDeliveryPersonCode())){
            throw new NotFoundDeliveryPersonExeption(deliveryTraking.getDeliveryPersonCode());
        }
    }
}
