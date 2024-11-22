package br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record EstimativeDto(
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate estimatedDeliveryTime
) {
}
