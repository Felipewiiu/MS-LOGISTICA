package unit_test.deliveryTrankingTest;

import br.com.mslogistica.ms_logistica.application.usecases.deliveryTraking.*;
import br.com.mslogistica.ms_logistica.domain.entities.DeliveryTraking;
import br.com.mslogistica.ms_logistica.domain.valueobjects.CurrentLocation;
import br.com.mslogistica.ms_logistica.presentation.controller.DeliveryTrakingController;
import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.CurrentLocationDto;
import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.DeliveryTrakingAllDataDto;
import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.DeliveryTrakingDto;
import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.EstimativeDto;
import br.com.mslogistica.ms_logistica.presentation.mapper.deliverytraking.CurrentLocationMapperDto;
import br.com.mslogistica.ms_logistica.presentation.mapper.deliverytraking.DeliveryTrakingAllDataMapperDto;
import br.com.mslogistica.ms_logistica.presentation.mapper.deliverytraking.DeliveryTrakingMapperDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import templateDto.CurrentLocationTemplateDto;
import templateDto.DeliveryTrakingTemplateDto;
import templateDto.EstimativeTemplateDto;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DeliveryTrakingControllerTest {

    @Mock
    private CreateDeliveryTrakingUseCase createDeliveryTrakingUseCase;
    @Mock
    private DeliveryTrakingMapperDto deliveryTrakingMapperDto;
    @Mock
    private FindAllDeliveryTrakingUseCase findAllDeliveryTrakingUseCase;
    @Mock
    private DeliveryTrakingAllDataMapperDto deliveryTrakingAllDataMapperDto;
    @Mock
    private FindDeliveryTrakingByCodeUseCase findDeliveryTrakingByCodeUseCase;
    @Mock
    private DeleteDeliveryTrakingUseCase deleteDeliveryTrakingUseCase;
    @Mock
    private UpdateCurrentLocation updateCurrentLocationUseCase;
    @Mock
    private CurrentLocationMapperDto currentLocationMapperDto;
    @Mock
    private UpdateDeliveryStatusUseCase updateDeliveryStatusUseCase;
    @Mock
    private UpdateEstimativeDeliveryTime updateEstimativeDeliveryTimeUseCase;

    @InjectMocks
    private DeliveryTrakingController deliveryTrakingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateDeliveryTraking() {
        DeliveryTrakingDto deliveryTrakingDto = DeliveryTrakingTemplateDto.deliveryTrakingTemplate(1L);
        DeliveryTraking deliveryTraking = new DeliveryTraking();
        when(deliveryTrakingMapperDto.toDomain(any(DeliveryTrakingDto.class))).thenReturn(deliveryTraking);
        when(createDeliveryTrakingUseCase.create(any(DeliveryTraking.class), anyLong())).thenReturn(deliveryTraking);
        when(deliveryTrakingMapperDto.toDto(any(DeliveryTraking.class))).thenReturn(deliveryTrakingDto);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
        ResponseEntity<DeliveryTrakingDto> response = deliveryTrakingController.createDeliveryTraking(deliveryTrakingDto, uriBuilder);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(createDeliveryTrakingUseCase, times(1)).create(any(DeliveryTraking.class), anyLong());
    }

    @Test
    void testGetAllDeliveryTrakings() {
        DeliveryTraking deliveryTraking = new DeliveryTraking();
        when(findAllDeliveryTrakingUseCase.findAllDeliveryTraking()).thenReturn(Collections.singletonList(deliveryTraking));
        when(deliveryTrakingAllDataMapperDto.toDto(any(DeliveryTraking.class))).thenReturn(DeliveryTrakingTemplateDto.deliveryTrakingAllDataTemplate(1L));

        ResponseEntity<List<DeliveryTrakingAllDataDto>> response = deliveryTrakingController.getAllDeliveryTrakings();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(findAllDeliveryTrakingUseCase, times(1)).findAllDeliveryTraking();
    }

    @Test
    void testFindDeliveryTrakingByCode() {
        DeliveryTraking deliveryTraking = new DeliveryTraking();
        when(findDeliveryTrakingByCodeUseCase.findDeliveryTrakingByCode(anyLong())).thenReturn(deliveryTraking);
        when(deliveryTrakingAllDataMapperDto.toDto(any(DeliveryTraking.class))).thenReturn(DeliveryTrakingTemplateDto.deliveryTrakingAllDataTemplate(1L));

        ResponseEntity<DeliveryTrakingAllDataDto> response = deliveryTrakingController.findDeliveryTrakingByCode(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(findDeliveryTrakingByCodeUseCase, times(1)).findDeliveryTrakingByCode(anyLong());
    }

    @Test
    void testDeleteDeliveryTraking() {
        doNothing().when(deleteDeliveryTrakingUseCase).deleteDeliveryTraking(anyLong());

        ResponseEntity<Void> response = deliveryTrakingController.deleteDeliveryTraking(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(deleteDeliveryTrakingUseCase, times(1)).deleteDeliveryTraking(anyLong());
    }

    @Test
    void testUpdateCurrentLocation() {
        CurrentLocationDto currentLocationDto = CurrentLocationTemplateDto.currentLocationTemplate();
        DeliveryTraking deliveryTraking = new DeliveryTraking();
        when(currentLocationMapperDto.toDomain(any(CurrentLocationDto.class))).thenReturn(new CurrentLocation());
        when(updateCurrentLocationUseCase.updateCurrentLocation(anyLong(), any(CurrentLocation.class))).thenReturn(deliveryTraking);
        when(deliveryTrakingAllDataMapperDto.toDto(any(DeliveryTraking.class))).thenReturn(DeliveryTrakingTemplateDto.deliveryTrakingAllDataTemplate(1L));

        ResponseEntity<DeliveryTrakingAllDataDto> response = deliveryTrakingController.updateCurrentLocation(1L, currentLocationDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(updateCurrentLocationUseCase, times(1)).updateCurrentLocation(anyLong(), any(CurrentLocation.class));
    }

    @Test
    void testUpdateCurrentLocationStatus() {
        DeliveryTraking deliveryTraking = new DeliveryTraking();
        when(updateDeliveryStatusUseCase.updateDeliveryStatus(anyLong(), anyString())).thenReturn(deliveryTraking);
        when(deliveryTrakingAllDataMapperDto.toDto(any(DeliveryTraking.class))).thenReturn(DeliveryTrakingTemplateDto.deliveryTrakingAllDataTemplate(1L));

        ResponseEntity<DeliveryTrakingAllDataDto> response = deliveryTrakingController.updateCurrentLocationStatus(1L, "Delivered");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(updateDeliveryStatusUseCase, times(1)).updateDeliveryStatus(anyLong(), anyString());
    }

    @Test
    void testUpdateEstimatedLocationStatus() {
        EstimativeDto estimativeDto = EstimativeTemplateDto.estimativeTemplate();
        DeliveryTraking deliveryTraking = new DeliveryTraking();
        when(updateEstimativeDeliveryTimeUseCase.updateEstimativeDeliveryTime(anyLong(), any(LocalDate.class))).thenReturn(deliveryTraking);
        when(deliveryTrakingAllDataMapperDto.toDto(any(DeliveryTraking.class))).thenReturn(DeliveryTrakingTemplateDto.deliveryTrakingAllDataTemplate(1L));

        ResponseEntity<DeliveryTrakingAllDataDto> response = deliveryTrakingController.updateEstimatedLocationStatus(1L, estimativeDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(updateEstimativeDeliveryTimeUseCase, times(1)).updateEstimativeDeliveryTime(anyLong(), any(LocalDate.class));
    }


}