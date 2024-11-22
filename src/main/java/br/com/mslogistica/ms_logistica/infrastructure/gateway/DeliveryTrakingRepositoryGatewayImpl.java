package br.com.mslogistica.ms_logistica.infrastructure.gateway;

import br.com.mslogistica.ms_logistica.application.exeptions.NotFoundDeliveryTrakingExeption;
import br.com.mslogistica.ms_logistica.application.gateway.DeliveryTrakingRepositoryGateway;
import br.com.mslogistica.ms_logistica.domain.entities.DeliveryTraking;
import br.com.mslogistica.ms_logistica.domain.enums.DeliveryStatus;
import br.com.mslogistica.ms_logistica.domain.valueobjects.CurrentLocation;
import br.com.mslogistica.ms_logistica.infrastructure.mapper.deliverytraking.CurrentLocationMapper;
import br.com.mslogistica.ms_logistica.infrastructure.mapper.deliverytraking.DeliveryTrakingMapper;
import br.com.mslogistica.ms_logistica.infrastructure.persistence.entityJpa.deliverytraking.CurrentLocationJpa;
import br.com.mslogistica.ms_logistica.infrastructure.persistence.entityJpa.deliverytraking.DeliveryTrakingJpa;
import br.com.mslogistica.ms_logistica.infrastructure.persistence.repository.DeliveryTrakingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class DeliveryTrakingRepositoryGatewayImpl implements DeliveryTrakingRepositoryGateway {

    private final DeliveryTrakingRepository deliveryTrakingRepository;
    private final DeliveryTrakingMapper deliveryTrakingMapper;
    private final CurrentLocationMapper currentLocationMapper;


    @Override
    public DeliveryTraking create(DeliveryTraking deliveryTraking, Long deliveryPersonCode) {
        DeliveryTrakingJpa deliveryTrakingJpa = deliveryTrakingMapper.toEntityJpa(deliveryTraking);

        deliveryTrakingRepository.save(deliveryTrakingJpa);
        return deliveryTrakingMapper.toEntityDomain(deliveryTrakingJpa);
    }

    @Override
    public boolean existsByDeliveryTraking(Long deliveryTrakingCode) {
        return deliveryTrakingRepository.existsByDeliveryCode(deliveryTrakingCode);
    }


    @Override
    public List<DeliveryTraking> findAllDeliveryTraking() {
        List<DeliveryTrakingJpa> deliveryTrakingJpa = deliveryTrakingRepository.findAll();

        return deliveryTrakingJpa
                .stream()
                .map(deliveryTrakingMapper::toEntityDomain)
                .toList();
    }

    @Override
    public DeliveryTraking findDeliveryTrakingByCode(Long code) {
        DeliveryTrakingJpa deliveryTrakingJpa = deliveryTrakingRepository.findByDeliveryCode(code)
                .orElseThrow(() -> new NotFoundDeliveryTrakingExeption(code));

        return deliveryTrakingMapper.toEntityDomain(deliveryTrakingJpa);
    }

    @Override
    @Transactional
    public void deleteDeliveryTrakingByCode(Long id) {
        deliveryTrakingRepository.deleteByDeliveryCode(id);
    }

    @Override
    public DeliveryTraking updateCurrentLocation(Long deliveryTrakingCode, CurrentLocation currentLocation) {
        DeliveryTrakingJpa deliveryTrakingJpa = deliveryTrakingRepository.findByDeliveryCode(deliveryTrakingCode)
                .orElseThrow(() -> new NotFoundDeliveryTrakingExeption(deliveryTrakingCode));

        CurrentLocationJpa currentLocationJpa = currentLocationMapper.toJpa(currentLocation);

        deliveryTrakingJpa.updateCurrentLocation(currentLocationJpa);

        deliveryTrakingRepository.save(deliveryTrakingJpa);

        return deliveryTrakingMapper.toEntityDomain(deliveryTrakingJpa);
    }

    @Override
    public DeliveryTraking updateDeliveryStatus(Long deliveryTrakingCode, String deliveryStatus) {
        DeliveryTrakingJpa deliveryTrakingJpa = deliveryTrakingRepository.findByDeliveryCode(deliveryTrakingCode)
                .orElseThrow(() -> new NotFoundDeliveryTrakingExeption(deliveryTrakingCode));

        deliveryTrakingJpa.updateDeliveryStatus(DeliveryStatus.valueOf(deliveryStatus));

        deliveryTrakingRepository.save(deliveryTrakingJpa);

        return deliveryTrakingMapper.toEntityDomain(deliveryTrakingJpa);
    }

    @Override
    public DeliveryTraking updateEstimativeDeliveryTime(Long deliveryTrakingCode, LocalDate date) {
        DeliveryTrakingJpa deliveryTrakingJpa = deliveryTrakingRepository.findByDeliveryCode(deliveryTrakingCode)
                .orElseThrow(() -> new NotFoundDeliveryTrakingExeption(deliveryTrakingCode));

        deliveryTrakingJpa.estimatedDeliveryTime(date);

        deliveryTrakingRepository.save(deliveryTrakingJpa);

        return deliveryTrakingMapper.toEntityDomain(deliveryTrakingJpa);
    }


}
