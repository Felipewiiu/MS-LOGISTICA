package br.com.mslogistica.ms_logistica.infrastructure.persistence.repository;

import br.com.mslogistica.ms_logistica.domain.entities.DeliveryPerson;
import br.com.mslogistica.ms_logistica.infrastructure.persistence.entityJpa.deliveryperson.DeliveryPersonJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryPersonRepository extends JpaRepository<DeliveryPersonJpa, Long> {
    boolean existsByPersonCode(Long deliveryPersonCode);

    Optional<DeliveryPersonJpa> findByPersonCode(Long code);

    void deleteByPersonCode(Long code);
}
