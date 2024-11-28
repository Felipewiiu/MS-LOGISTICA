package templateDto;

import br.com.mslogistica.ms_logistica.domain.enums.DeliveryStatus;
import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.CurrentLocationDto;
import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.DeliveryTrakingAllDataDto;
import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.DeliveryTrakingDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DeliveryTrakingTemplateDto {

    public static DeliveryTrakingDto deliveryTrakingTemplate(Long deliveryCode) {
        return new DeliveryTrakingDto(
                1L,
                deliveryCode,
                DestinationTemplateDto.destinationTemplate()
        );
    }

    public static DeliveryTrakingAllDataDto deliveryTrakingAllDataTemplate(Long deliveryCode) {
        return new DeliveryTrakingAllDataDto(
                1L,
                deliveryCode,
                1L,
                LocalDateTime.now(),
                DeliveryStatus.AGUARDANDO_ROTA,
                LocalDate.now(),
                new CurrentLocationDto(-23.550520, -46.633308),
                null,
                DestinationTemplateDto.destinationTemplate()
        );
    }
}