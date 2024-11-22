package br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking;

import br.com.mslogistica.ms_logistica.domain.enums.DeliveryStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DeliveryTrakingAllDataDto(
        Long deliveryCode,

        Long deliveryPersonCode,

        Long orderCode,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime createdAt,

        DeliveryStatus deliveryStatus,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate estimatedDeliveryTime,

        CurrentLocationDto currentLocation,

        Long routeCode,

        DestinationDto destination
) {
}
