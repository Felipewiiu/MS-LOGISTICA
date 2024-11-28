package unit_test.deliveryPersonTest;

import br.com.mslogistica.ms_logistica.application.usecases.deliveryPerson.*;
import br.com.mslogistica.ms_logistica.domain.entities.DeliveryPerson;
import br.com.mslogistica.ms_logistica.presentation.controller.DeliveryPersonController;
import br.com.mslogistica.ms_logistica.presentation.dto.deliveryperson.DeliveryPersonDto;
import br.com.mslogistica.ms_logistica.presentation.mapper.deliveryperson.DeliveryPersonMapperDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import templateDto.DeliveryTemplateDto;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DeliveryPersonControllerTest {

    @Mock
    private CreateDeliveryPersonUseCase createDeliveryPersonUseCase;
    @Mock
    private DeliveryPersonMapperDto deliveryPersonMapperDto;
    @Mock
    private FindAllDeliveryPersonUseCase findAllDeliveryPersonUseCase;
    @Mock
    private FindPersonDeliveryByCodeUseCase findPersonDeliveryByCodeUseCase;
    @Mock
    private DeleteDeliveryPersonByCodeUseCase deleteDeliveryPersonByCodeUseCase;
    @Mock
    private UpdateDeliveryPersonUseCase updateDeliveryPersonUseCase;

    @InjectMocks
    private DeliveryPersonController deliveryPersonController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePersonDelivery() {
        DeliveryPersonDto deliveryPersonDto = DeliveryTemplateDto.deliveryPersonTemplate(1L);
        DeliveryPerson deliveryPerson = new DeliveryPerson();
        when(deliveryPersonMapperDto.toDomain(any(DeliveryPersonDto.class))).thenReturn(deliveryPerson);
        when(createDeliveryPersonUseCase.create(any(DeliveryPerson.class))).thenReturn(deliveryPerson);
        when(deliveryPersonMapperDto.toDto(any(DeliveryPerson.class))).thenReturn(deliveryPersonDto);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
        ResponseEntity<DeliveryPersonDto> response = deliveryPersonController.createPersonDelivery(deliveryPersonDto, uriBuilder);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(createDeliveryPersonUseCase, times(1)).create(any(DeliveryPerson.class));
    }

    @Test
    void testGetAllDeliveryPerson() {
        DeliveryPerson deliveryPerson = new DeliveryPerson();
        when(findAllDeliveryPersonUseCase.findAll()).thenReturn(Collections.singletonList(deliveryPerson));
        when(deliveryPersonMapperDto.toDto(any(DeliveryPerson.class))).thenReturn(DeliveryTemplateDto.deliveryPersonTemplate(1L));

        ResponseEntity<List<DeliveryPersonDto>> response = deliveryPersonController.getAllDeliveryPerson();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(findAllDeliveryPersonUseCase, times(1)).findAll();
    }

    @Test
    void testGetDeliveryPersonByCode() {
        DeliveryPerson deliveryPerson = new DeliveryPerson();
        when(findPersonDeliveryByCodeUseCase.findDeliveryPersonByCode(anyLong())).thenReturn(deliveryPerson);
        when(deliveryPersonMapperDto.toDto(any(DeliveryPerson.class))).thenReturn(DeliveryTemplateDto.deliveryPersonTemplate(1L));

        ResponseEntity<DeliveryPersonDto> response = deliveryPersonController.getDeliveryPersonByCode(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(findPersonDeliveryByCodeUseCase, times(1)).findDeliveryPersonByCode(anyLong());
    }

    @Test
    void testDeleteDeliveryPerson() {
        doNothing().when(deleteDeliveryPersonByCodeUseCase).deleteDeliveryPersonByCode(anyLong());

        ResponseEntity<Void> response = deliveryPersonController.deleteDeliveryPerson(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(deleteDeliveryPersonByCodeUseCase, times(1)).deleteDeliveryPersonByCode(anyLong());
    }

    @Test
    void testUpdateDeliveryPerson() {
        DeliveryPersonDto deliveryPersonDto = DeliveryTemplateDto.deliveryPersonTemplate(1L);
        DeliveryPerson deliveryPerson = new DeliveryPerson();
        when(deliveryPersonMapperDto.toDomain(any(DeliveryPersonDto.class))).thenReturn(deliveryPerson);
        when(updateDeliveryPersonUseCase.updateDeliveryPerson(any(DeliveryPerson.class))).thenReturn(deliveryPerson);
        when(deliveryPersonMapperDto.toDto(any(DeliveryPerson.class))).thenReturn(deliveryPersonDto);

        ResponseEntity<DeliveryPersonDto> response = deliveryPersonController.updateDeliveryPerson(deliveryPersonDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(updateDeliveryPersonUseCase, times(1)).updateDeliveryPerson(any(DeliveryPerson.class));
    }
}
