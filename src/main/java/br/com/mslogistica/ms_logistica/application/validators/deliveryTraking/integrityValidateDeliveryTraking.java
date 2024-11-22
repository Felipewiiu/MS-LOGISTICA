package br.com.mslogistica.ms_logistica.application.validators.deliveryTraking;

import br.com.mslogistica.ms_logistica.domain.entities.DeliveryTraking;
import jakarta.validation.ValidationException;

public class integrityValidateDeliveryTraking implements DeliveryTrakingValidator {
    @Override
    public void validate(DeliveryTraking deliveryTraking) {

        if( deliveryTraking.getDeliveryPersonCode() == null ) {
            throw new ValidationException("Delivery person code is required");
        }

        if( deliveryTraking.getDestination().getCep() == null || deliveryTraking.getDestination().getCep().isBlank() ) {
            throw new ValidationException("Cep is required");
        }

        if( deliveryTraking.getDestination().getUF() == null || deliveryTraking.getDestination().getUF().isBlank() ) {
            throw new ValidationException("UF is required");
        }

        if( deliveryTraking.getDestination().getCity() == null || deliveryTraking.getDestination().getCity().isBlank() ) {
            throw new ValidationException("City is required");
        }

        if( deliveryTraking.getDestination().getNumber() == null ) {
            throw new ValidationException("Number is required");
        }

        if( deliveryTraking.getDestination().getComplement() == null || deliveryTraking.getDestination().getComplement().isBlank() ) {
            throw new ValidationException("Complement is required");
        }

    }
}
