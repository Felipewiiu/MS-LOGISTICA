package br.com.mslogistica.ms_logistica.domain.enums;

public enum DeliveryStatus {
    AGUARDANDO_ROTA(1),
    AGUARDANDO_COLETA(2),
    SAIU_PARA_ENTREGA(3),
    ENTREGUE(4);

    private final int value;

    DeliveryStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
