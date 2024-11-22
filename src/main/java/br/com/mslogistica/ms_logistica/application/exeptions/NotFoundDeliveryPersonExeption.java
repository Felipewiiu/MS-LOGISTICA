package br.com.mslogistica.ms_logistica.application.exeptions;

public class NotFoundDeliveryPersonExeption extends RuntimeException {
    public NotFoundDeliveryPersonExeption(Long deliveryPersonCode) {
        super("Delivery person not found with code " + deliveryPersonCode);
    }
}
