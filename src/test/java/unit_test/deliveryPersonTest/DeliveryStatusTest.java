package unit_test.deliveryPersonTest;

import br.com.mslogistica.ms_logistica.domain.enums.DeliveryStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeliveryStatusTest {

    @Test
    void testDeliveryStatusValues() {
        assertEquals(1, DeliveryStatus.AGUARDANDO_ROTA.ordinal() + 1);
        assertEquals(2, DeliveryStatus.AGUARDANDO_COLETA.ordinal() + 1);
        assertEquals(3, DeliveryStatus.SAIU_PARA_ENTREGA.ordinal() + 1);
        assertEquals(4, DeliveryStatus.ENTREGUE.ordinal() + 1);
    }

    @Test
    void testDeliveryStatusNames() {
        assertEquals("AGUARDANDO_ROTA", DeliveryStatus.AGUARDANDO_ROTA.name());
        assertEquals("AGUARDANDO_COLETA", DeliveryStatus.AGUARDANDO_COLETA.name());
        assertEquals("SAIU_PARA_ENTREGA", DeliveryStatus.SAIU_PARA_ENTREGA.name());
        assertEquals("ENTREGUE", DeliveryStatus.ENTREGUE.name());
    }
}
