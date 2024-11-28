package br.com.mslogistica.ms_logistica.infrastructure.mapper.deliveryperson;

import br.com.mslogistica.ms_logistica.domain.entities.DeliveryPerson;
import br.com.mslogistica.ms_logistica.domain.entities.DeliveryTraking;
import br.com.mslogistica.ms_logistica.infrastructure.mapper.deliverytraking.DeliveryTrakingMapper;
import br.com.mslogistica.ms_logistica.infrastructure.persistence.entityJpa.deliveryperson.DeliveryPersonJpa;
import br.com.mslogistica.ms_logistica.infrastructure.persistence.entityJpa.deliverytraking.DeliveryTrakingJpa;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-23T21:45:25-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class DeliveryPersonMapperImpl implements DeliveryPersonMapper {

    @Autowired
    private DeliveryTrakingMapper deliveryTrakingMapper;

    @Override
    public DeliveryPerson toDomain(DeliveryPersonJpa deliveryPerson) {
        if ( deliveryPerson == null ) {
            return null;
        }

        DeliveryPerson deliveryPerson1 = new DeliveryPerson();

        deliveryPerson1.setDeliveryTrakings( deliveryTrakingJpaListToDeliveryTrakingList( deliveryPerson.getDeliveryTrakings() ) );
        deliveryPerson1.setPersonCode( deliveryPerson.getPersonCode() );
        deliveryPerson1.setName( deliveryPerson.getName() );
        deliveryPerson1.setCPF( deliveryPerson.getCPF() );
        deliveryPerson1.setPhoneNumber( deliveryPerson.getPhoneNumber() );

        return deliveryPerson1;
    }

    @Override
    public DeliveryPersonJpa toJpa(DeliveryPerson deliveryPerson) {
        if ( deliveryPerson == null ) {
            return null;
        }

        DeliveryPersonJpa deliveryPersonJpa = new DeliveryPersonJpa();

        deliveryPersonJpa.setDeliveryTrakings( deliveryTrakingListToDeliveryTrakingJpaList( deliveryPerson.getDeliveryTrakings() ) );
        deliveryPersonJpa.setPersonCode( deliveryPerson.getPersonCode() );
        deliveryPersonJpa.setName( deliveryPerson.getName() );
        deliveryPersonJpa.setCPF( deliveryPerson.getCPF() );
        deliveryPersonJpa.setPhoneNumber( deliveryPerson.getPhoneNumber() );

        return deliveryPersonJpa;
    }

    protected List<DeliveryTraking> deliveryTrakingJpaListToDeliveryTrakingList(List<DeliveryTrakingJpa> list) {
        if ( list == null ) {
            return null;
        }

        List<DeliveryTraking> list1 = new ArrayList<DeliveryTraking>( list.size() );
        for ( DeliveryTrakingJpa deliveryTrakingJpa : list ) {
            list1.add( deliveryTrakingMapper.toEntityDomain( deliveryTrakingJpa ) );
        }

        return list1;
    }

    protected List<DeliveryTrakingJpa> deliveryTrakingListToDeliveryTrakingJpaList(List<DeliveryTraking> list) {
        if ( list == null ) {
            return null;
        }

        List<DeliveryTrakingJpa> list1 = new ArrayList<DeliveryTrakingJpa>( list.size() );
        for ( DeliveryTraking deliveryTraking : list ) {
            list1.add( deliveryTrakingMapper.toEntityJpa( deliveryTraking ) );
        }

        return list1;
    }
}
