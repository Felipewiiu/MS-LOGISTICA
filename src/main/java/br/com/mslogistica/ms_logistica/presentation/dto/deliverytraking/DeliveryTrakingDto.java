package br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking;


public record DeliveryTrakingDto(

        Long deliveryPersonCode,
        Long routeCode,
        DestinationDto destination

) {
}
