package br.com.mslogistica.ms_logistica.application.usecases.deliveryTraking;


import br.com.mslogistica.ms_logistica.application.exeptions.NotFoundDeliveryTrakingExeption;
import br.com.mslogistica.ms_logistica.application.gateway.DeliveryTrakingRepositoryGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteDeliveryTrakingUseCase {

    private final DeliveryTrakingRepositoryGateway deliveryTrakingRepositoryGateway;

    public void deleteDeliveryTraking(Long deliveryTrakingCode) {

        if(!deliveryTrakingRepositoryGateway.existsByDeliveryTraking(deliveryTrakingCode)) {
            throw new NotFoundDeliveryTrakingExeption(deliveryTrakingCode);
        }

        deliveryTrakingRepositoryGateway.deleteDeliveryTrakingByCode(deliveryTrakingCode);

    }
}
