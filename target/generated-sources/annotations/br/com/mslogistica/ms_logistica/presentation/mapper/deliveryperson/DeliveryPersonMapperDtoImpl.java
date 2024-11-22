package br.com.mslogistica.ms_logistica.presentation.mapper.deliveryperson;

import br.com.mslogistica.ms_logistica.domain.entities.DeliveryPerson;
import br.com.mslogistica.ms_logistica.presentation.dto.deliveryperson.DeliveryPersonDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-21T08:19:50-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class DeliveryPersonMapperDtoImpl implements DeliveryPersonMapperDto {

    @Override
    public DeliveryPerson toDomain(DeliveryPersonDto deliveryPersonDto) {
        if ( deliveryPersonDto == null ) {
            return null;
        }

        DeliveryPerson deliveryPerson = new DeliveryPerson();

        deliveryPerson.setPersonCode( deliveryPersonDto.personCode() );
        deliveryPerson.setName( deliveryPersonDto.name() );
        deliveryPerson.setCPF( deliveryPersonDto.CPF() );
        deliveryPerson.setPhoneNumber( deliveryPersonDto.phoneNumber() );

        return deliveryPerson;
    }

    @Override
    public DeliveryPersonDto toDto(DeliveryPerson deliveryPerson) {
        if ( deliveryPerson == null ) {
            return null;
        }

        Long personCode = null;
        String name = null;
        String cPF = null;
        String phoneNumber = null;

        personCode = deliveryPerson.getPersonCode();
        name = deliveryPerson.getName();
        cPF = deliveryPerson.getCPF();
        phoneNumber = deliveryPerson.getPhoneNumber();

        DeliveryPersonDto deliveryPersonDto = new DeliveryPersonDto( personCode, name, cPF, phoneNumber );

        return deliveryPersonDto;
    }
}
