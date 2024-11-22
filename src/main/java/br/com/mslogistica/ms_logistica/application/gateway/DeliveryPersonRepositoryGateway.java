package br.com.mslogistica.ms_logistica.application.gateway;

import br.com.mslogistica.ms_logistica.domain.entities.DeliveryPerson;

import java.util.List;

public interface DeliveryPersonRepositoryGateway {

    DeliveryPerson create(DeliveryPerson deliveryPerson);

    boolean existsByDeliveryPerson(Long deliveryPersonCode);

    List<DeliveryPerson> findAllDeliveryPersons();

    DeliveryPerson findPersonDeliveryByCode(Long code);

    void deletePersonDeliveryByCode(Long id);

    DeliveryPerson updatePersonDeliveryByCode(DeliveryPerson deliveryPerson);
}
