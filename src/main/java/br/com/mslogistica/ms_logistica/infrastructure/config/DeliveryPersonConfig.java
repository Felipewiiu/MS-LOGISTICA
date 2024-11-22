package br.com.mslogistica.ms_logistica.infrastructure.config;

import br.com.mslogistica.ms_logistica.application.gateway.DeliveryPersonRepositoryGateway;
import br.com.mslogistica.ms_logistica.application.usecases.deliveryPerson.*;
import br.com.mslogistica.ms_logistica.application.validators.deliveryPerson.DeliveryPersonValidator;
import br.com.mslogistica.ms_logistica.application.validators.deliveryPerson.IntegrityValidateDeliveryPerson;
import br.com.mslogistica.ms_logistica.application.validators.deliveryPerson.IsValidDeliveryPersonValidator;
import br.com.mslogistica.ms_logistica.infrastructure.gateway.DeliveryPersonRepositoryGatewayImpl;
import br.com.mslogistica.ms_logistica.infrastructure.mapper.deliveryperson.DeliveryPersonMapper;
import br.com.mslogistica.ms_logistica.infrastructure.persistence.repository.DeliveryPersonRepository;
import br.com.mslogistica.ms_logistica.presentation.controller.DeliveryPersonController;
import br.com.mslogistica.ms_logistica.presentation.mapper.deliveryperson.DeliveryPersonMapperDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DeliveryPersonConfig {

    @Bean
    public DeliveryPersonRepositoryGatewayImpl customDeliveryPersonRepositoryGateway(
            DeliveryPersonMapper deliveryPersonMapper, DeliveryPersonRepository deliveryPersonRepository
    ) {

        return new DeliveryPersonRepositoryGatewayImpl(deliveryPersonMapper, deliveryPersonRepository);

    }

    @Bean
    public CreateDeliveryPersonUseCase createDeliveryPersonUseCase(
            DeliveryPersonRepositoryGateway deliveryPersonRepositoryGateway, List<DeliveryPersonValidator> validators) {
        return new CreateDeliveryPersonUseCase(deliveryPersonRepositoryGateway, validators);
    }


    @Bean
    public DeliveryPersonController deliveryPersonController(
            CreateDeliveryPersonUseCase createDeliveryPersonUseCase,
            DeliveryPersonMapperDto deliveryPersonMapperDto,
            FindAllDeliveryPersonUseCase findAllDeliveryPersonUseCase,
            FindPersonDeliveryByCodeUseCase findPersonDeliveryByCodeUseCase,
            DeleteDeliveryPersonByCodeUseCase deleteDeliveryPersonByCodeUseCase,
            UpdateDeliveryPersonUseCase updateDeliveryPersonUseCase) {

        return new DeliveryPersonController(createDeliveryPersonUseCase, deliveryPersonMapperDto,
                findAllDeliveryPersonUseCase, findPersonDeliveryByCodeUseCase,
                deleteDeliveryPersonByCodeUseCase, updateDeliveryPersonUseCase);
    }

    @Bean
    public  UpdateDeliveryPersonUseCase updateDeliveryPersonUseCase(
            DeliveryPersonRepositoryGateway deliveryPersonRepositoryGateway,
            IsValidDeliveryPersonValidator isValidDeliveryPersonValidator){
        return new UpdateDeliveryPersonUseCase(deliveryPersonRepositoryGateway, isValidDeliveryPersonValidator);

    }

    @Bean
    public DeleteDeliveryPersonByCodeUseCase deleteDeliveryPersonByCodeUseCase(DeliveryPersonRepositoryGateway deliveryPersonRepositoryGateway){
        return new DeleteDeliveryPersonByCodeUseCase(deliveryPersonRepositoryGateway);
    }

    @Bean
    public FindAllDeliveryPersonUseCase findAllDeliveryPersonUseCase(DeliveryPersonRepositoryGateway deliveryPersonRepositoryGateway) {
        return new FindAllDeliveryPersonUseCase(deliveryPersonRepositoryGateway);
    }

    @Bean
    public FindPersonDeliveryByCodeUseCase findPersonDeliveryByCodeUseCase(DeliveryPersonRepositoryGateway deliveryPersonRepositoryGateway) {
        return new FindPersonDeliveryByCodeUseCase(deliveryPersonRepositoryGateway);
    }

    @Bean
    public IntegrityValidateDeliveryPerson integrityValidateDeliveryPerson() {
        return new IntegrityValidateDeliveryPerson();
    }

    @Bean
    public IsValidDeliveryPersonValidator isValidDeliveryPersonValidator(
            DeliveryPersonRepositoryGateway deliveryPersonRepositoryGateway) {
        return new IsValidDeliveryPersonValidator(deliveryPersonRepositoryGateway);

    }

}
