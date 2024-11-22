package br.com.mslogistica.ms_logistica.domain.enums;

public enum DeliveryStatus {
    AGUARDANDO_ROTA(0),
    AGUARDANDO_COLETA(1),
    SAIU_PARA_ENTRAGA(3),
    ENTREGUE(3);

    private final int value;

    DeliveryStatus(int value) {
        this.value = value;
    }
}
