package br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking;

import br.com.mslogistica.ms_logistica.domain.enums.DeliveryStatus;
import br.com.mslogistica.ms_logistica.domain.valueobjects.CurrentLocation;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DeliveryTrakingDto(

        Long deliveryPersonCode,
        Long routeCode,
        DestinationDto destination

) {
}
