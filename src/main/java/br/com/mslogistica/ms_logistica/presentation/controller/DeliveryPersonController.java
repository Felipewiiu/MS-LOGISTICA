package br.com.mslogistica.ms_logistica.presentation.controller;

import br.com.mslogistica.ms_logistica.application.usecases.deliveryPerson.*;
import br.com.mslogistica.ms_logistica.domain.entities.DeliveryPerson;
import br.com.mslogistica.ms_logistica.presentation.dto.deliveryperson.DeliveryPersonDto;
import br.com.mslogistica.ms_logistica.presentation.mapper.deliveryperson.DeliveryPersonMapperDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/delivery-person")
@RequiredArgsConstructor
public class DeliveryPersonController {

    private final CreateDeliveryPersonUseCase createDeliveryPersonUseCase;
    private final DeliveryPersonMapperDto deliveryPersonMapperDto;
    private final FindAllDeliveryPersonUseCase findAllDeliveryPersonUseCase;
    private final FindPersonDeliveryByCodeUseCase findPersonDeliveryByCodeUseCase;
    private final DeleteDeliveryPersonByCodeUseCase deleteDeliveryPersonByCodeUseCase;
    private final UpdateDeliveryPersonUseCase updateDeliveryPersonUseCase;

    @PostMapping
    @Operation(summary = "Cadastra entregador")
    public ResponseEntity<DeliveryPersonDto> createPersonDelivery(@RequestBody DeliveryPersonDto deliveryPersonDto,
                                                                  UriComponentsBuilder uriBuilder) {
        DeliveryPerson deliveryPerson = createDeliveryPersonUseCase
                .create(deliveryPersonMapperDto.toDomain(deliveryPersonDto));

        URI address = uriBuilder.path("/delivery-person/{id}").buildAndExpand(deliveryPerson.getPersonCode()).toUri();

        return ResponseEntity.created(address).body(deliveryPersonMapperDto.toDto(deliveryPerson));
    }

    @GetMapping("all")
    @Operation(summary = "Busca todos os entregadores cadastrados")
    public ResponseEntity<List<DeliveryPersonDto>> getAllDeliveryPerson() {

        List<DeliveryPerson> deliveryPersonList = findAllDeliveryPersonUseCase.findAll();

        List<DeliveryPersonDto> deliveryPersonDtosList = deliveryPersonList
                .stream()
                .map(deliveryPersonMapperDto::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(deliveryPersonDtosList);
    }

    @GetMapping("/{code}")
    @Operation(summary = "Busca entregador por código")
    public ResponseEntity<DeliveryPersonDto> getDeliveryPersonByCode(@PathVariable Long code) {
        DeliveryPerson deliveryPerson = findPersonDeliveryByCodeUseCase.findDeliveryPersonByCode(code);

        DeliveryPersonDto deliveryPersonDto = deliveryPersonMapperDto.toDto(deliveryPerson);

        return ResponseEntity.status(HttpStatus.OK).body(deliveryPersonDto);
    }

    @DeleteMapping("/{code}")
    @Operation(summary = "Exclui entregador por código")
    public ResponseEntity<Void> deleteDeliveryPerson(@PathVariable Long code) {
        deleteDeliveryPersonByCodeUseCase.deleteDeliveryPersonByCode(code);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    @Operation(summary = "Atualiza dados do entregador")
    public ResponseEntity<DeliveryPersonDto> updateDeliveryPerson(@RequestBody DeliveryPersonDto deliveryPersonDto){
        DeliveryPerson deliveryPerson = updateDeliveryPersonUseCase
                .updateDeliveryPerson(deliveryPersonMapperDto.toDomain(deliveryPersonDto));

        return ResponseEntity.status(HttpStatus.OK).body(deliveryPersonMapperDto.toDto(deliveryPerson));
    }


}
