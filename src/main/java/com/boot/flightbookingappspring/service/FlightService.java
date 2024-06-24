package com.boot.flightbookingappspring.service;

import com.boot.flightbookingappspring.model.dto.FlightDto;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {
    void createFlight(FlightDto flightDto);

    FlightDto retrieveFlight(Long id);

    List<FlightDto> retrieveAllFlights();

    void removeFlight(Long id);

    void updateFlight(Long id, FlightDto flightDto);

    List<FlightDto> displayOnlineBoard();

    FlightDto searchFlight(String destination, LocalDateTime departureTime);
}
