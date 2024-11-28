package endToEnd_test;

import br.com.mslogistica.ms_logistica.MsLogisticaApplication;
import br.com.mslogistica.ms_logistica.presentation.dto.deliveryperson.DeliveryPersonDto;
import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.CurrentLocationDto;
import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.DeliveryTrakingAllDataDto;
import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.DeliveryTrakingDto;
import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.EstimativeDto;
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
import templateDto.CurrentLocationTemplateDto;
import templateDto.DeliveryTemplateDto;
import templateDto.DeliveryTrakingTemplateDto;
import templateDto.EstimativeTemplateDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = MsLogisticaApplication.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DeliveryTrakingEndtoEndTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private DeliveryTrakingDto deliveryTrakingDto;
    private DeliveryTrakingAllDataDto deliveryTrakingAllDataDto;


    @BeforeEach
    void setUp() throws Exception {
        DeliveryPersonDto deliveryPersonDto = DeliveryTemplateDto.deliveryPersonTemplate(1L);
        String deliveryPersonJson = objectMapper.writeValueAsString(deliveryPersonDto);

        mockMvc.perform(post("/delivery-person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deliveryPersonJson))
                .andExpect(status().isCreated());

        deliveryTrakingDto = DeliveryTrakingTemplateDto.deliveryTrakingTemplate(1L);
        deliveryTrakingAllDataDto = DeliveryTrakingTemplateDto.deliveryTrakingAllDataTemplate(1L);
    }

    @Test
    void testCreateDeliveryTraking() throws Exception {
        String responseContent = mockMvc.perform(post("/delivery-tracking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deliveryTrakingDto)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        DeliveryTrakingDto actualDto = objectMapper.readValue(responseContent, DeliveryTrakingDto.class);
        assertEquals(deliveryTrakingDto.deliveryPersonCode(), actualDto.deliveryPersonCode());
    }

    @Test
    void testGetAllDeliveryTrakings() throws Exception {
        mockMvc.perform(post("/delivery-tracking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deliveryTrakingDto)))
                .andExpect(status().isCreated());

        String responseContent = mockMvc.perform(get("/delivery-tracking/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        DeliveryTrakingAllDataDto[] actualDtos = objectMapper.readValue(responseContent, DeliveryTrakingAllDataDto[].class);
        assertEquals(deliveryTrakingAllDataDto.deliveryPersonCode(), actualDtos[0].deliveryPersonCode());
    }

    @Test
    void testFindDeliveryTrakingByCode() throws Exception {
        mockMvc.perform(post("/delivery-tracking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deliveryTrakingDto)))
                .andExpect(status().isCreated());

        String responseContent = mockMvc.perform(get("/delivery-tracking/{code}", deliveryTrakingDto.deliveryPersonCode()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        DeliveryTrakingAllDataDto actualDto = objectMapper.readValue(responseContent, DeliveryTrakingAllDataDto.class);
        assertEquals(deliveryTrakingAllDataDto.deliveryPersonCode(), actualDto.deliveryPersonCode());
    }

    @Test
    void testDeleteDeliveryTraking() throws Exception {
        mockMvc.perform(post("/delivery-tracking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deliveryTrakingDto)))
                .andExpect(status().isCreated());

        mockMvc.perform(delete("/delivery-tracking/{code}", deliveryTrakingDto.deliveryPersonCode()))
                .andExpect(status().isNoContent());
    }

    @Test
    void testUpdateCurrentLocation() throws Exception {
        mockMvc.perform(post("/delivery-tracking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deliveryTrakingDto)))
                .andExpect(status().isCreated());

        CurrentLocationDto currentLocationDto = CurrentLocationTemplateDto.currentLocationTemplate();

        String responseContent = mockMvc.perform(put("/delivery-tracking/{code}", deliveryTrakingDto.deliveryPersonCode())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(currentLocationDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        DeliveryTrakingAllDataDto actualDto = objectMapper.readValue(responseContent, DeliveryTrakingAllDataDto.class);
        assertEquals(deliveryTrakingAllDataDto.deliveryPersonCode(), actualDto.deliveryPersonCode());
    }

    @Test
    void testUpdateCurrentLocationStatus() throws Exception {
        mockMvc.perform(post("/delivery-tracking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deliveryTrakingDto)))
                .andExpect(status().isCreated());

        String status = "ENTREGUE";

        String responseContent = mockMvc.perform(put("/delivery-tracking/update-status/{deliveryCode}/{status}", deliveryTrakingDto.deliveryPersonCode(), status))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        DeliveryTrakingAllDataDto actualDto = objectMapper.readValue(responseContent, DeliveryTrakingAllDataDto.class);
        assertEquals(deliveryTrakingAllDataDto.deliveryPersonCode(), actualDto.deliveryPersonCode());
    }

    @Test
    void testUpdateEstimatedLocationStatus() throws Exception {
        mockMvc.perform(post("/delivery-tracking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deliveryTrakingDto)))
                .andExpect(status().isCreated());

        EstimativeDto estimativeDto = EstimativeTemplateDto.estimativeTemplate();

        String responseContent = mockMvc.perform(put("/delivery-tracking/update-estimative/{code}", deliveryTrakingDto.deliveryPersonCode())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(estimativeDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        DeliveryTrakingAllDataDto actualDto = objectMapper.readValue(responseContent, DeliveryTrakingAllDataDto.class);
        assertEquals(deliveryTrakingAllDataDto.deliveryPersonCode(), actualDto.deliveryPersonCode());
    }
}

