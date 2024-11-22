package br.com.mslogistica.ms_logistica.presentation.dto.deliveryperson;

public record DeliveryPersonDto(

        Long personCode,

        String name,

        String CPF,

        String phoneNumber
) {

}
