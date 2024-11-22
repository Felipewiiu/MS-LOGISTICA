package br.com.mslogistica.ms_logistica.infrastructure.config;


import br.com.mslogistica.ms_logistica.application.gateway.DeliveryPersonRepositoryGateway;
import br.com.mslogistica.ms_logistica.application.gateway.DeliveryTrakingRepositoryGateway;
import br.com.mslogistica.ms_logistica.application.usecases.deliveryTraking.*;
import br.com.mslogistica.ms_logistica.application.validators.deliveryPerson.ExistDeliveryPeson;
import br.com.mslogistica.ms_logistica.application.validators.deliveryTraking.DeliveryTrakingValidator;
import br.com.mslogistica.ms_logistica.application.validators.deliveryTraking.integrityValidateDeliveryTraking;
import br.com.mslogistica.ms_logistica.infrastructure.gateway.DeliveryTrakingRepositoryGatewayImpl;
import br.com.mslogistica.ms_logistica.infrastructure.mapper.deliverytraking.CurrentLocationMapper;
import br.com.mslogistica.ms_logistica.infrastructure.mapper.deliverytraking.DeliveryTrakingMapper;
import br.com.mslogistica.ms_logistica.infrastructure.persistence.repository.DeliveryTrakingRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DeliveryTrackingConfig {

    @Bean
    public DeliveryTrakingRepositoryGatewayImpl getDeliveryTrakingRepositortGateway(
            DeliveryTrakingRepository deliveryTrakingRepository, DeliveryTrakingMapper deliveryTrakingMapper,
            CurrentLocationMapper currentLocationMapper) {
        return new DeliveryTrakingRepositoryGatewayImpl(deliveryTrakingRepository,
                deliveryTrakingMapper, currentLocationMapper);
    }

    @Bean
    public CreateDeliveryTrakingUseCase getCreateDeliveryTrakingUseCase(
            DeliveryTrakingRepositoryGateway deliveryTrakingRepositoryGateway, List<DeliveryTrakingValidator> validators) {
        return new CreateDeliveryTrakingUseCase(deliveryTrakingRepositoryGateway, validators);
    }

    @Bean
    public ExistDeliveryPeson existDeliveryPeson(DeliveryPersonRepositoryGateway deliveryPersonRepositoryGateway) {
        return new ExistDeliveryPeson(deliveryPersonRepositoryGateway);
    }

    @Bean
    public integrityValidateDeliveryTraking integrityValidateDeliveryTraking(){
        return new integrityValidateDeliveryTraking();
    }

    @Bean
    public FindAllDeliveryTrakingUseCase findAllDeliveryTrakingUseCase(DeliveryTrakingRepositoryGateway deliveryTrakingRepositoryGateway){
        return new FindAllDeliveryTrakingUseCase(deliveryTrakingRepositoryGateway);
    }

    @Bean
    public FindDeliveryTrakingByCodeUseCase findDeliveryTrakingByCodeUseCases(
            DeliveryTrakingRepositoryGateway deliveryTrakingRepositoryGateway){
        return new FindDeliveryTrakingByCodeUseCase(deliveryTrakingRepositoryGateway);
    }

    @Bean
    public DeleteDeliveryTrakingUseCase deleteDeliveryTrakingUseCases(
            DeliveryTrakingRepositoryGateway deliveryTrakingRepositoryGateway){
        return new DeleteDeliveryTrakingUseCase(deliveryTrakingRepositoryGateway);
    }

    @Bean
    public UpdateCurrentLocation updateCurrentLocations(DeliveryTrakingRepositoryGateway deliveryTrakingRepositoryGateway){
        return new UpdateCurrentLocation(deliveryTrakingRepositoryGateway);
    }

    @Bean
    public UpdateDeliveryStatusUseCase updateDeliveryStatusUseCase(DeliveryTrakingRepositoryGateway deliveryTrakingRepositoryGateway){
        return new UpdateDeliveryStatusUseCase(deliveryTrakingRepositoryGateway);
    }

    @Bean
    public UpdateEstimativeDeliveryTime updateEstimativeDeliveryTime( DeliveryTrakingRepositoryGateway deliveryTrakingRepositoryGateway){
        return new UpdateEstimativeDeliveryTime(deliveryTrakingRepositoryGateway);
    }
}
