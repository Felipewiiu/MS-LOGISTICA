package templateDto;

import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.EstimativeDto;

import java.time.LocalDate;

public class EstimativeTemplateDto {
    public static EstimativeDto estimativeTemplate() {
        return new EstimativeDto(LocalDate.now().plusDays(5));
    }
}