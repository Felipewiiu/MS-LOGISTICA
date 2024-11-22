package br.com.mslogistica.ms_logistica.infrastructure.persistence.entityJpa.deliverytraking;

import br.com.mslogistica.ms_logistica.domain.enums.DeliveryStatus;
import br.com.mslogistica.ms_logistica.domain.valueobjects.CurrentLocation;
import br.com.mslogistica.ms_logistica.infrastructure.persistence.entityJpa.deliveryperson.DeliveryPersonJpa;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "deliveryTraking")
@Table(name = "RASTREAMENTO_ENTREGAS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DeliveryTrakingJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_person_code")
    private DeliveryPersonJpa deliveryPersonCode;

    private Long orderCode;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus = DeliveryStatus.AGUARDANDO_ROTA;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate estimatedDeliveryTime;

    @Embedded
    private CurrentLocationJpa currentLocation;

    private Long routeCode;

    @Embedded
    private DestinationJpa destination;


    public void updateCurrentLocation(CurrentLocationJpa currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void updateDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public void estimatedDeliveryTime(LocalDate date) {
        this.estimatedDeliveryTime = date;
    }
}
