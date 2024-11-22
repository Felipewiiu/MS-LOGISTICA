package br.com.mslogistica.ms_logistica.infrastructure.gateway;


import br.com.mslogistica.ms_logistica.application.exeptions.NotFoundDeliveryPersonExeption;
import br.com.mslogistica.ms_logistica.application.gateway.DeliveryPersonRepositoryGateway;
import br.com.mslogistica.ms_logistica.domain.entities.DeliveryPerson;
import br.com.mslogistica.ms_logistica.infrastructure.mapper.deliveryperson.DeliveryPersonMapper;
import br.com.mslogistica.ms_logistica.infrastructure.persistence.entityJpa.deliveryperson.DeliveryPersonJpa;
import br.com.mslogistica.ms_logistica.infrastructure.persistence.repository.DeliveryPersonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DeliveryPersonRepositoryGatewayImpl implements DeliveryPersonRepositoryGateway {

    private final DeliveryPersonMapper deliveryPersonMapper;
    private final DeliveryPersonRepository deliveryPersonRepository;


    @Override
    public DeliveryPerson create(DeliveryPerson deliveryPerson) {

        DeliveryPersonJpa deliveryPersonJpa = deliveryPersonMapper.toJpa(deliveryPerson);
        deliveryPersonRepository.save(deliveryPersonJpa);
        return deliveryPersonMapper.toDomain(deliveryPersonJpa);

    }

    @Override
    public boolean existsByDeliveryPerson(Long deliveryPersonCode) {
        return deliveryPersonRepository.existsByPersonCode(deliveryPersonCode);
    }

    @Override
    public List<DeliveryPerson> findAllDeliveryPersons() {
        List<DeliveryPersonJpa> deliveryPersonJpaList = deliveryPersonRepository.findAll();

        return deliveryPersonJpaList.stream()
                .map(deliveryPersonMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public DeliveryPerson findPersonDeliveryByCode(Long code) {
        DeliveryPersonJpa deliveryPerson = deliveryPersonRepository
                .findByPersonCode(code)
                .orElseThrow(() -> new NotFoundDeliveryPersonExeption(code));

        return deliveryPersonMapper.toDomain(deliveryPerson);
    }

    @Override
    @Transactional
    public void deletePersonDeliveryByCode(Long code) {
        deliveryPersonRepository.deleteByPersonCode(code);
    }

    @Override
    public DeliveryPerson updatePersonDeliveryByCode(DeliveryPerson deliveryPerson) {
        DeliveryPersonJpa deliveryPersonJpa = deliveryPersonRepository
                .findByPersonCode(deliveryPerson.getPersonCode())
                .orElseThrow(() -> new NotFoundDeliveryPersonExeption(deliveryPerson.getPersonCode()));

       deliveryPersonJpa.updateDataDeliveryPerson(deliveryPerson);

       deliveryPersonRepository.save(deliveryPersonJpa);

       return deliveryPersonMapper.toDomain(deliveryPersonJpa);
    }
}
