package templateDto;

import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.DestinationDto;

public class DestinationTemplateDto {
    public static DestinationDto destinationTemplate() {
        return new DestinationDto("Rua se, 123", 123,
                "Estado Exemplo", "12345-678", "Cidade Exemplo");
    }
}