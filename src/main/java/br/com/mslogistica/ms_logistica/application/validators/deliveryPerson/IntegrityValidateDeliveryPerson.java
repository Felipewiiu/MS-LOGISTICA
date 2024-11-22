package br.com.mslogistica.ms_logistica.application.validators.deliveryPerson;

import br.com.mslogistica.ms_logistica.domain.entities.DeliveryPerson;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

public class IntegrityValidateDeliveryPerson implements DeliveryPersonValidator{

    @Override
    public void validate(DeliveryPerson deliveryPerson) {

        if(!isValidCpf(deliveryPerson.getCPF())){
            throw new ValidationException("O campo CPF precisa seguir o padrão xxx.xxx.xxx-xx");
        }

        if(deliveryPerson.getCPF() == null || deliveryPerson.getCPF().isBlank()){
            throw new ValidationException("O campo CPF precisa não pode ser nulo");
        }


        if(deliveryPerson.getName() == null || deliveryPerson.getName().isBlank()){
            throw  new ValidationException("O campo name não pode ser nulo ou em branco");
        }

        if(deliveryPerson.getPhoneNumber() == null || deliveryPerson.getPhoneNumber().isBlank()){
            throw new ValidationException("O campo phoneNumber não pode ser nulo ou em branco");
        }

    }

    private boolean isValidCpf(String cpf){
        return cpf.matches( "^(\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2})$");

    }
}
