package unit_test.deliveryTrankingTest;

import br.com.mslogistica.ms_logistica.application.exeptions.NotFoundDeliveryTrakingExeption;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotFoundDeliveryTrakingExeptionTest {


    @Test
    void testExceptionMessage() {
        Long deliveryTrakingCode = 1L;
        NotFoundDeliveryTrakingExeption exception = new NotFoundDeliveryTrakingExeption(deliveryTrakingCode);

        assertEquals("Delivery Traking not found with code " + deliveryTrakingCode, exception.getMessage());
    }
}
