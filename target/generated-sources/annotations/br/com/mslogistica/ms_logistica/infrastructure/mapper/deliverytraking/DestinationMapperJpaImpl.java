package br.com.mslogistica.ms_logistica.infrastructure.mapper.deliverytraking;

import br.com.mslogistica.ms_logistica.domain.valueobjects.Destination;
import br.com.mslogistica.ms_logistica.infrastructure.persistence.entityJpa.deliverytraking.DestinationJpa;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-01T14:48:53-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.4.1 (Eclipse Adoptium)"
)
@Component
public class DestinationMapperJpaImpl implements DestinationMapperJpa {

    @Override
    public Destination toDestination(DestinationJpa destinationJpa) {
        if ( destinationJpa == null ) {
            return null;
        }

        Destination destination = new Destination();

        destination.setCep( destinationJpa.getCep() );
        destination.setNumber( destinationJpa.getNumber() );
        destination.setComplement( destinationJpa.getComplement() );
        destination.setCity( destinationJpa.getCity() );
        destination.setUF( destinationJpa.getUF() );

        return destination;
    }

    @Override
    public DestinationJpa toDestinationJpa(Destination destination) {
        if ( destination == null ) {
            return null;
        }

        DestinationJpa destinationJpa = new DestinationJpa();

        destinationJpa.setCep( destination.getCep() );
        destinationJpa.setNumber( destination.getNumber() );
        destinationJpa.setComplement( destination.getComplement() );
        destinationJpa.setCity( destination.getCity() );
        destinationJpa.setUF( destination.getUF() );

        return destinationJpa;
    }
}
