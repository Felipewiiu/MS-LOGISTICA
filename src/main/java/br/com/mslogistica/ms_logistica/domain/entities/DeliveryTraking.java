package br.com.mslogistica.ms_logistica.domain.entities;

import br.com.mslogistica.ms_logistica.domain.enums.DeliveryStatus;
import br.com.mslogistica.ms_logistica.domain.valueobjects.CurrentLocation;

import br.com.mslogistica.ms_logistica.domain.valueobjects.Destination;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class DeliveryTraking {

    private Long deliveryCode;

    private Long deliveryPersonCode;

    private Long orderCode;

    private LocalDateTime createdAt = LocalDateTime.now();

    private DeliveryStatus deliveryStatus = DeliveryStatus.AGUARDANDO_ROTA;

    private LocalDate estimatedDeliveryTime;

    private CurrentLocation currentLocation;

    private Long routeCode;

    private Destination destination;


}
