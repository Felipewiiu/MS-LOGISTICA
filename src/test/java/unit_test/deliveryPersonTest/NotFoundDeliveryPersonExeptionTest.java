package unit_test.deliveryPersonTest;

import br.com.mslogistica.ms_logistica.application.exeptions.NotFoundDeliveryPersonExeption;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NotFoundDeliveryPersonExeptionTest {


    @Test
    void testExceptionMessage() {
        Long deliveryPersonCode = 1L;
        NotFoundDeliveryPersonExeption exception = assertThrows(NotFoundDeliveryPersonExeption.class, () -> {
            throw new NotFoundDeliveryPersonExeption(deliveryPersonCode);
        });

        assertEquals("Delivery person not found with code " + deliveryPersonCode, exception.getMessage());
    }
}
