package integration_test;

import br.com.mslogistica.ms_logistica.MsLogisticaApplication;
import br.com.mslogistica.ms_logistica.application.usecases.deliveryPerson.CreateDeliveryPersonUseCase;
import br.com.mslogistica.ms_logistica.application.usecases.deliveryPerson.FindPersonDeliveryByCodeUseCase;
import br.com.mslogistica.ms_logistica.domain.entities.DeliveryPerson;
import br.com.mslogistica.ms_logistica.presentation.dto.deliveryperson.DeliveryPersonDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import templateDto.DeliveryPersonTemplate;
import templateDto.DeliveryTemplateDto;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = MsLogisticaApplication.class)
@AutoConfigureMockMvc
class DeliveryPersonControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateDeliveryPersonUseCase createDeliveryPersonUseCase;

    @MockBean
    private FindPersonDeliveryByCodeUseCase findPersonDeliveryByCodeUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateDeliveryPerson() throws Exception {
        DeliveryPersonDto deliveryPersonDto = DeliveryTemplateDto.deliveryPersonTemplate(1L);

        when(createDeliveryPersonUseCase.create(any())).thenReturn(DeliveryPersonTemplate.deliveryPersonTemplate(1L));

        mockMvc.perform(post("/delivery-person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deliveryPersonDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Joao Silva"));
    }

    @Test
    void testGetDeliveryPersonById() throws Exception {
        Long id = 1L;
        DeliveryPerson deliveryPerson = DeliveryPersonTemplate.deliveryPersonTemplate(id);

        when(findPersonDeliveryByCodeUseCase.findDeliveryPersonByCode(id)).thenReturn(deliveryPerson);

        mockMvc.perform(get("/delivery-person/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Joao Silva"));
    }

    @Test
    void testDeleteDeliveryPerson() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/delivery-person/{id}", id))
                .andExpect(status().isNoContent());
    }

}