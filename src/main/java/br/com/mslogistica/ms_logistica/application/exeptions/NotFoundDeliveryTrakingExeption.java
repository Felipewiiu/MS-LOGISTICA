package br.com.mslogistica.ms_logistica.application.exeptions;

public class NotFoundDeliveryTrakingExeption extends RuntimeException {
    public NotFoundDeliveryTrakingExeption(Long deliveryTrakingCode) {
        super("Delivery Traking not found with code " + deliveryTrakingCode);
    }
}
