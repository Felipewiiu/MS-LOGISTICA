package templateDto;

import br.com.mslogistica.ms_logistica.presentation.dto.deliveryperson.DeliveryPersonDto;

public class DeliveryTemplateDto {

    public static DeliveryPersonDto deliveryPersonTemplate(Long personCode) {
        return DeliveryPersonDto.builder()
                .personCode(personCode)
                .name("Joao Silva")
                .CPF("333.333.333-33")
                .phoneNumber("(33) 33333-33333")
                .build();
    }
}
