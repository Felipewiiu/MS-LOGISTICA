package br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking;

public record DestinationDto(
        String cep,

        Integer number,

        String complement,

        String city,

        String UF

) {
}
