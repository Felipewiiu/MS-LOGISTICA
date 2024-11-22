package br.com.mslogistica.ms_logistica.infrastructure.persistence.entityJpa.deliverytraking;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@ToString
public class CurrentLocationJpa {

    private double latitude;
    private double longitude;
}
