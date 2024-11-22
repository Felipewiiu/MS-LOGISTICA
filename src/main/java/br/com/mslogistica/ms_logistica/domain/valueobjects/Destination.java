package br.com.mslogistica.ms_logistica.domain.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Destination {

    private String cep;

    private Integer number;

    private String complement;

    private String city;

    private String UF;
}
