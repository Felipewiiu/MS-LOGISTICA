package endToEnd_test;

import br.com.mslogistica.ms_logistica.MsLogisticaApplication;
import br.com.mslogistica.ms_logistica.presentation.dto.deliveryperson.DeliveryPersonDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import templateDto.DeliveryTemplateDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MsLogisticaApplication.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DeliveryPersonEndtoEndTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private DeliveryPersonDto deliveryPersonDto;

    @BeforeEach
    void setUp() throws Exception {
        deliveryPersonDto = DeliveryTemplateDto.deliveryPersonTemplate(1L);
    }

    @Test
    void testCreateDeliveryPerson() throws Exception {
        String deliveryPersonJson = objectMapper.writeValueAsString(deliveryPersonDto);

        String responseContent = mockMvc.perform(post("/delivery-person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deliveryPersonJson))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        DeliveryPersonDto actualDto = objectMapper.readValue(responseContent, DeliveryPersonDto.class);
        assertEquals(deliveryPersonDto.personCode(), actualDto.personCode());
    }

    @Test
    void testGetDeliveryPersonById() throws Exception {
        String deliveryPersonJson = objectMapper.writeValueAsString(deliveryPersonDto);

        mockMvc.perform(post("/delivery-person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deliveryPersonJson))
                .andExpect(status().isCreated());

        String responseContent = mockMvc.perform(get("/delivery-person/{id}", deliveryPersonDto.personCode()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        DeliveryPersonDto actualDto = objectMapper.readValue(responseContent, DeliveryPersonDto.class);
        assertEquals(deliveryPersonDto.personCode(), actualDto.personCode());
    }

    @Test
    void testDeleteDeliveryPerson() throws Exception {
        String deliveryPersonJson = objectMapper.writeValueAsString(deliveryPersonDto);

        mockMvc.perform(post("/delivery-person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deliveryPersonJson))
                .andExpect(status().isCreated());

        mockMvc.perform(delete("/delivery-person/{id}", deliveryPersonDto.personCode()))
                .andExpect(status().isNoContent());
    }
}