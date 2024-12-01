package br.com.mslogistica.ms_logistica.presentation.mapper.deliverytraking;

import br.com.mslogistica.ms_logistica.domain.valueobjects.Destination;
import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.DestinationDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-01T14:48:53-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.4.1 (Eclipse Adoptium)"
)
@Component
public class DestinationMapperImpl implements DestinationMapper {

    @Override
    public DestinationDto toDto(Destination destination) {
        if ( destination == null ) {
            return null;
        }

        String cep = null;
        Integer number = null;
        String complement = null;
        String city = null;
        String uF = null;

        cep = destination.getCep();
        number = destination.getNumber();
        complement = destination.getComplement();
        city = destination.getCity();
        uF = destination.getUF();

        DestinationDto destinationDto = new DestinationDto( cep, number, complement, city, uF );

        return destinationDto;
    }

    @Override
    public Destination toDomain(DestinationDto destinationDto) {
        if ( destinationDto == null ) {
            return null;
        }

        Destination destination = new Destination();

        destination.setCep( destinationDto.cep() );
        destination.setNumber( destinationDto.number() );
        destination.setComplement( destinationDto.complement() );
        destination.setCity( destinationDto.city() );
        destination.setUF( destinationDto.UF() );

        return destination;
    }
}
