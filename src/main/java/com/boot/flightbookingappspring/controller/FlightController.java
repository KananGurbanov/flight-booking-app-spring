package com.boot.flightbookingappspring.controller;


import com.boot.flightbookingappspring.model.dto.FlightDto;
import com.boot.flightbookingappspring.model.dto.RestResponse;
import com.boot.flightbookingappspring.service.FlightService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("aviasales")
@Validated
@RequiredArgsConstructor
@Tag(name = "Flight Controller API", description = "flight controller")
public class FlightController {

    private final FlightService flightService;

    @GetMapping("/flights/{id}")
    ResponseEntity<RestResponse<FlightDto>> retrieveFlight(@PathVariable @NonNull Long id) {
        FlightDto flightDto = flightService.retrieveFlight(id);
        RestResponse<FlightDto> restResponse = RestResponse.<FlightDto>builder()
                .data(flightDto)
                .status("SUCCESS")
                .build();

        return ResponseEntity.ok(restResponse);
    }

    @PostMapping("/flights")
    ResponseEntity<Void> createFlight(@RequestBody @Valid @NonNull FlightDto flightDto) {
        flightService.createFlight(flightDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/flights")
    ResponseEntity<RestResponse<List<FlightDto>>> retrieveFlights() {
        List<FlightDto> flightDtos = flightService.retrieveAllFlights();
        RestResponse<List<FlightDto>> restResponse = RestResponse.<List<FlightDto>>builder()
                .data(flightDtos)
                .status("SUCCESS")
                .build();

        return ResponseEntity.ok(restResponse);
    }


    @DeleteMapping("/flights/{id}")
    ResponseEntity<Void> deleteFlight(@PathVariable @NonNull Long id) {
        flightService.removeFlight(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/flights/{id}")
    ResponseEntity<Void> updateFlight(@PathVariable @NonNull Long id, @RequestBody @Valid @NonNull FlightDto flightDto) {
        flightService.updateFlight(id, flightDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/flights/search")
    ResponseEntity<RestResponse<FlightDto>> searchFlight(@RequestParam @NonNull String destination, @RequestParam @NonNull LocalDateTime departureTime) {
        FlightDto flightDto = flightService.searchFlight(destination, departureTime);
        RestResponse<FlightDto> restResponse = RestResponse.<FlightDto>builder()
                .data(flightDto)
                .status("SUCCESS")
                .build();
        return ResponseEntity.ok(restResponse);
    }

    @GetMapping("/flights/online-board")
    ResponseEntity<RestResponse<List<FlightDto>>> displayOnlineBoard() {
        List<FlightDto> flightDtos = flightService.displayOnlineBoard();
        RestResponse<List<FlightDto>> restResponse = RestResponse.<List<FlightDto>>builder()
                .data(flightDtos)
                .status("SUCCESS")
                .build();

        return ResponseEntity.ok(restResponse);
    }
}
