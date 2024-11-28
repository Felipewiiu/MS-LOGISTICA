package integration_test;

import br.com.mslogistica.ms_logistica.MsLogisticaApplication;
import br.com.mslogistica.ms_logistica.application.usecases.deliveryTraking.*;
import br.com.mslogistica.ms_logistica.domain.entities.DeliveryTraking;
import br.com.mslogistica.ms_logistica.domain.valueobjects.CurrentLocation;
import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.CurrentLocationDto;
import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.DeliveryTrakingAllDataDto;
import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.DeliveryTrakingDto;
import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.EstimativeDto;
import br.com.mslogistica.ms_logistica.presentation.mapper.deliverytraking.CurrentLocationMapperDto;
import br.com.mslogistica.ms_logistica.presentation.mapper.deliverytraking.DeliveryTrakingAllDataMapperDto;
import br.com.mslogistica.ms_logistica.presentation.mapper.deliverytraking.DeliveryTrakingMapperDto;
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
import templateDto.CurrentLocationTemplateDto;
import templateDto.DeliveryTrakingTemplate;
import templateDto.DeliveryTrakingTemplateDto;
import templateDto.EstimativeTemplateDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = MsLogisticaApplication.class)
@AutoConfigureMockMvc
class DeliveryTrakingControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateDeliveryTrakingUseCase createDeliveryTrakingUseCase;

    @MockBean
    private DeliveryTrakingMapperDto deliveryTrakingMapperDto;

    @MockBean
    private FindAllDeliveryTrakingUseCase findAllDeliveryTrakingUseCase;

    @MockBean
    private DeliveryTrakingAllDataMapperDto deliveryTrakingAllDataMapperDto;

    @MockBean
    private FindDeliveryTrakingByCodeUseCase findDeliveryTrakingByCodeUseCase;

    @MockBean
    private DeleteDeliveryTrakingUseCase deleteDeliveryTrakingUseCase;

    @MockBean
    private UpdateCurrentLocation updateCurrentLocationUseCase;

    @MockBean
    private CurrentLocationMapperDto currentLocationMapperDto;

    @MockBean
    private UpdateDeliveryStatusUseCase updateDeliveryStatusUseCase;

    @MockBean
    private UpdateEstimativeDeliveryTime updateEstimativeDeliveryTimeUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateDeliveryTraking() throws Exception {
        DeliveryTrakingDto deliveryTrakingDto = DeliveryTrakingTemplateDto.deliveryTrakingTemplate(1L);
        DeliveryTraking deliveryTraking = DeliveryTrakingTemplate.deliveryTrakingTemplate(1L);

        when(createDeliveryTrakingUseCase.create(any(), any())).thenReturn(deliveryTraking);
        when(deliveryTrakingMapperDto.toDomain(any())).thenReturn(deliveryTraking);
        when(deliveryTrakingMapperDto.toDto(any())).thenReturn(deliveryTrakingDto);

        mockMvc.perform(post("/delivery-tracking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deliveryTrakingDto)))
                .andExpect(status().isCreated());

    }

    @Test
    void testGetAllDeliveryTrakings() throws Exception {
        List<DeliveryTraking> deliveryTrakingList = List.of(DeliveryTrakingTemplate.deliveryTrakingTemplate(1L));
        DeliveryTrakingAllDataDto expectedDto = DeliveryTrakingTemplateDto.deliveryTrakingAllDataTemplate(1L);

        when(findAllDeliveryTrakingUseCase.findAllDeliveryTraking()).thenReturn(deliveryTrakingList);
        when(deliveryTrakingAllDataMapperDto.toDto(any())).thenReturn(expectedDto);

        String responseContent = mockMvc.perform(get("/delivery-tracking/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        DeliveryTrakingAllDataDto[] actualDtos = objectMapper.readValue(responseContent, DeliveryTrakingAllDataDto[].class);
        assertEquals(expectedDto.deliveryCode(), actualDtos[0].deliveryCode());
    }

    @Test
    void testFindDeliveryTrakingByCode() throws Exception {
        Long code = 1L;
        DeliveryTraking deliveryTraking = DeliveryTrakingTemplate.deliveryTrakingTemplate(code);
        DeliveryTrakingAllDataDto expectedDto = DeliveryTrakingTemplateDto.deliveryTrakingAllDataTemplate(code);

        when(findDeliveryTrakingByCodeUseCase.findDeliveryTrakingByCode(code)).thenReturn(deliveryTraking);
        when(deliveryTrakingAllDataMapperDto.toDto(any())).thenReturn(expectedDto);

        String responseContent = mockMvc.perform(get("/delivery-tracking/{code}", code))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        DeliveryTrakingAllDataDto actualDto = objectMapper.readValue(responseContent, DeliveryTrakingAllDataDto.class);
        assertEquals(expectedDto.deliveryCode(), actualDto.deliveryCode());
    }

    @Test
    void testDeleteDeliveryTraking() throws Exception {
        Long code = 1L;

        mockMvc.perform(delete("/delivery-tracking/{code}", code))
                .andExpect(status().isNoContent());
    }

    @Test
    void testUpdateCurrentLocation() throws Exception {
        Long code = 1L;
        CurrentLocationDto currentLocationDto = CurrentLocationTemplateDto.currentLocationTemplate();
        DeliveryTraking deliveryTraking = DeliveryTrakingTemplate.deliveryTrakingTemplate(code);
        DeliveryTrakingAllDataDto expectedDto = DeliveryTrakingTemplateDto.deliveryTrakingAllDataTemplate(code);

        when(updateCurrentLocationUseCase.updateCurrentLocation(any(), any())).thenReturn(deliveryTraking);
        when(currentLocationMapperDto.toDomain(any())).thenReturn(new CurrentLocation());
        when(deliveryTrakingAllDataMapperDto.toDto(any())).thenReturn(expectedDto);

        String responseContent = mockMvc.perform(put("/delivery-tracking/{code}", code)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(currentLocationDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        DeliveryTrakingAllDataDto actualDto = objectMapper.readValue(responseContent, DeliveryTrakingAllDataDto.class);
        assertEquals(expectedDto.deliveryCode(), actualDto.deliveryCode());
    }

    @Test
    void testUpdateCurrentLocationStatus() throws Exception {
        Long code = 1L;
        String status = "Delivered";
        DeliveryTraking deliveryTraking = DeliveryTrakingTemplate.deliveryTrakingTemplate(code);
        DeliveryTrakingAllDataDto expectedDto = DeliveryTrakingTemplateDto.deliveryTrakingAllDataTemplate(code);

        when(updateDeliveryStatusUseCase.updateDeliveryStatus(any(), any())).thenReturn(deliveryTraking);
        when(deliveryTrakingAllDataMapperDto.toDto(any())).thenReturn(expectedDto);

        String responseContent = mockMvc.perform(put("/delivery-tracking/update-status/{deliveryCode}/{status}", code, status))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        DeliveryTrakingAllDataDto actualDto = objectMapper.readValue(responseContent, DeliveryTrakingAllDataDto.class);
        assertEquals(expectedDto.deliveryCode(), actualDto.deliveryCode());
    }

    @Test
    void testUpdateEstimatedLocationStatus() throws Exception {
        Long code = 1L;
        EstimativeDto estimativeDto = EstimativeTemplateDto.estimativeTemplate();
        DeliveryTraking deliveryTraking = DeliveryTrakingTemplate.deliveryTrakingTemplate(code);
        DeliveryTrakingAllDataDto expectedDto = DeliveryTrakingTemplateDto.deliveryTrakingAllDataTemplate(code);

        when(updateEstimativeDeliveryTimeUseCase.updateEstimativeDeliveryTime(any(), any())).thenReturn(deliveryTraking);
        when(deliveryTrakingAllDataMapperDto.toDto(any())).thenReturn(expectedDto);

        String responseContent = mockMvc.perform(put("/delivery-tracking/update-estimative/{code}", code)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(estimativeDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        DeliveryTrakingAllDataDto actualDto = objectMapper.readValue(responseContent, DeliveryTrakingAllDataDto.class);
        assertEquals(expectedDto.deliveryCode(), actualDto.deliveryCode());
    }
}