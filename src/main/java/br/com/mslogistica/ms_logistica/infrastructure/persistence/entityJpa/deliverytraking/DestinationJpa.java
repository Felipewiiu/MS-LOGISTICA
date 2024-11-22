package br.com.mslogistica.ms_logistica.infrastructure.persistence.entityJpa.deliverytraking;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Embeddable
@ToString
public class DestinationJpa {

    private String cep;

    private Integer number;

    private String complement;

    private String city;

    private String UF;
}
