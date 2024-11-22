package br.com.mslogistica.ms_logistica.domain.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CurrentLocation {

    private double latitude;
    private double longitude;

}
