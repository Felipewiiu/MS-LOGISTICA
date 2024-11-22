package br.com.mslogistica.ms_logistica.infrastructure.persistence.repository;

import br.com.mslogistica.ms_logistica.infrastructure.persistence.entityJpa.deliverytraking.DeliveryTrakingJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryTrakingRepository extends JpaRepository<DeliveryTrakingJpa, Long> {
    boolean existsByDeliveryCode(Long deliveryTrakingCode);

    Optional<DeliveryTrakingJpa> findByDeliveryCode(Long code);


    void deleteByDeliveryCode(Long id);
}
