package templateDto;

import br.com.mslogistica.ms_logistica.domain.entities.DeliveryTraking;
import br.com.mslogistica.ms_logistica.domain.enums.DeliveryStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeliveryTrakingTemplate {


    public static DeliveryTraking deliveryTrakingTemplate(Long code) {
        DeliveryTraking deliveryTraking = new DeliveryTraking();
        deliveryTraking.setDeliveryCode(code);
        deliveryTraking.setDeliveryStatus(DeliveryStatus.AGUARDANDO_ROTA);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime estimatedDeliveryTime = LocalDateTime.parse("2023-12-31T23:59:59", formatter);
        deliveryTraking.setEstimatedDeliveryTime(LocalDate.from(estimatedDeliveryTime));

        return deliveryTraking;
    }
}
