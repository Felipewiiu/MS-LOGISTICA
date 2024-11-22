package br.com.mslogistica.ms_logistica.presentation.controller;


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
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/delivery-tracking")
@RequiredArgsConstructor
public class DeliveryTrakingController {

    private final CreateDeliveryTrakingUseCase createDeliveryTrakingUseCase;
    private final DeliveryTrakingMapperDto deliveryTrakingMapperDto;
    private final FindAllDeliveryTrakingUseCase findAllDeliveryTrakingUseCase;
    private final DeliveryTrakingAllDataMapperDto deliveryTrakingAllDataMapperDto;
    private final FindDeliveryTrakingByCodeUseCase findDeliveryTrakingByCodeUseCase;
    private final DeleteDeliveryTrakingUseCase deleteDeliveryTrakingUseCase;
    private final UpdateCurrentLocation updateCurrentLocationUseCase;
    private final CurrentLocationMapperDto currentLocationMapperDto;
    private final UpdateDeliveryStatusUseCase updateDeliveryStatusUseCase;
    private final UpdateEstimativeDeliveryTime updateEstimativeDeliveryTimeUseCase;

    @PostMapping
    @Operation(summary = "Cadastra entrega de pedidos")
    public ResponseEntity<DeliveryTrakingDto> createDeliveryTraking(@RequestBody DeliveryTrakingDto deliveryTrakingDto, UriComponentsBuilder uriBuilder) {
        DeliveryTraking deliveryTraking = createDeliveryTrakingUseCase
                .create(deliveryTrakingMapperDto.toDomain(deliveryTrakingDto), deliveryTrakingDto.deliveryPersonCode());

        URI adress = uriBuilder.path("/delivery-tracking/{id}").buildAndExpand(deliveryTraking.getDeliveryCode()).toUri();

        return ResponseEntity.created(adress).body(deliveryTrakingMapperDto.toDto(deliveryTraking));
    }

    @GetMapping("/all")
    @Operation(summary = "Busca todas as entregas cadastradas")
    public ResponseEntity<List<DeliveryTrakingAllDataDto>> getAllDeliveryTrakings() {
        List<DeliveryTraking> deliveryTrakingList = findAllDeliveryTrakingUseCase.findAllDeliveryTraking();

        List<DeliveryTrakingAllDataDto> deliveryTrakingDtoList = deliveryTrakingList.stream()
                .map(deliveryTrakingAllDataMapperDto::toDto).toList();

        return ResponseEntity.status(HttpStatus.OK).body(deliveryTrakingDtoList);
    }

    @GetMapping("/{code}")
    @Operation(summary = "Busca entrega por código")
    public ResponseEntity<DeliveryTrakingAllDataDto> findDeliveryTrakingByCode(@PathVariable Long code) {
        DeliveryTraking deliveryTraking = findDeliveryTrakingByCodeUseCase.findDeliveryTrakingByCode(code);

        return ResponseEntity.status(HttpStatus.OK).body(deliveryTrakingAllDataMapperDto.toDto(deliveryTraking));

    }

    @DeleteMapping("/{code}")
    @Operation(summary = "Deleta entrega por código")
    public ResponseEntity<Void> deleteDeliveryTraking(@PathVariable Long code){
        deleteDeliveryTrakingUseCase.deleteDeliveryTraking(code);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{code}")
    @Operation(summary = "Atualiza a localização atual da entrega")
    public ResponseEntity<DeliveryTrakingAllDataDto> updateCurrentLocation(
            @PathVariable Long code, @RequestBody CurrentLocationDto currentLocation){

        DeliveryTraking deliveryTraking = updateCurrentLocationUseCase
                .updateCurrentLocation(code, currentLocationMapperDto.toDomain(currentLocation));

        return ResponseEntity.status(HttpStatus.OK).body(deliveryTrakingAllDataMapperDto.toDto(deliveryTraking));

    }

    @PutMapping("/update-status/{deliveryCode}/{status}")
    @Operation(summary = "Atualiza o status da entrega")
    public ResponseEntity<DeliveryTrakingAllDataDto> updateCurrentLocationStatus(
            @PathVariable Long deliveryCode, @PathVariable String status){

        DeliveryTraking deliveryTraking = updateDeliveryStatusUseCase.updateDeliveryStatus(deliveryCode, status);

        return ResponseEntity.ok(deliveryTrakingAllDataMapperDto.toDto(deliveryTraking));
    }

    @PutMapping("/update-estimative/{code}")
    @Operation(summary = "Atualiza o tempo estimado da entrega")
    public ResponseEntity<DeliveryTrakingAllDataDto> updateEstimatedLocationStatus(
            @PathVariable Long code, @RequestBody EstimativeDto dto){
        DeliveryTraking deliveryTraking = updateEstimativeDeliveryTimeUseCase
                .updateEstimativeDeliveryTime(code, dto.estimatedDeliveryTime());

        return ResponseEntity.ok(deliveryTrakingAllDataMapperDto.toDto(deliveryTraking));
    }

}
