package br.com.mslogistica.ms_logistica.application.gateway;


import br.com.mslogistica.ms_logistica.domain.entities.DeliveryTraking;
import br.com.mslogistica.ms_logistica.domain.enums.DeliveryStatus;
import br.com.mslogistica.ms_logistica.domain.valueobjects.CurrentLocation;

import java.time.LocalDate;
import java.util.List;

public interface DeliveryTrakingRepositoryGateway {

    DeliveryTraking create(DeliveryTraking deliveryTraking, Long deliveryTrakingCode);

    boolean existsByDeliveryTraking(Long deliveryTrakingCode);

    List<DeliveryTraking> findAllDeliveryTraking();

    DeliveryTraking findDeliveryTrakingByCode(Long code);

    void deleteDeliveryTrakingByCode(Long id);

    DeliveryTraking updateCurrentLocation(Long deliveryTrakingCode, CurrentLocation deliveryPerson);

    DeliveryTraking updateDeliveryStatus(Long deliveryTrakingCode, String deliveryStatus);

    DeliveryTraking updateEstimativeDeliveryTime(Long deliveryTrakingCode, LocalDate date);

}
