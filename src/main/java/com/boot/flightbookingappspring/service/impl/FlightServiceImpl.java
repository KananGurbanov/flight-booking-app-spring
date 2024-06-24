package com.boot.flightbookingappspring.service.impl;

import com.boot.flightbookingappspring.dao.entity.FlightEntity;
import com.boot.flightbookingappspring.dao.repository.FlightRepository;
import com.boot.flightbookingappspring.exceptions.RecordNotFoundException;
import com.boot.flightbookingappspring.model.dto.FlightDto;
import com.boot.flightbookingappspring.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.boot.flightbookingappspring.mapper.FlightMapper.flightMapper;
import static com.boot.flightbookingappspring.model.enums.Error.ERR_01;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;

    @Override
    public void createFlight(FlightDto flightDto) {
        flightRepository.save(flightMapper.mapToEntity(flightDto));
    }

    @Override
    public FlightDto retrieveFlight(Long id) {
        return flightMapper.mapToDto(flightRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(ERR_01.getErrorCode(), ERR_01.getErrorDescription())));
    }

    @Override
    public List<FlightDto> retrieveAllFlights() {
        return flightMapper.mapToDtoList(flightRepository.findAll());
    }

    @Override
    public void removeFlight(Long id) {
        FlightEntity flightEntity = flightRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(ERR_01.getErrorCode(), ERR_01.getErrorDescription()));
        flightRepository.delete(flightEntity);
    }

    @Override
    public void updateFlight(Long id, FlightDto flightDto) {
        FlightEntity flightEntity = flightRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(ERR_01.getErrorCode(), ERR_01.getErrorDescription()));
        flightEntity.setOrigin(flightDto.getOrigin());
        flightEntity.setDestination(flightDto.getDestination());
        flightEntity.setDepartureTime(flightDto.getDepartureTime());
        flightEntity.setAvailableSeats(flightDto.getAvailableSeats());
        flightRepository.save(flightEntity);
    }

    @Override
    public List<FlightDto> displayOnlineBoard() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next24Hours = now.plusHours(24);

        return flightMapper.mapToDtoList(flightRepository
                .findAll()
                .stream()
                .filter(
                        flightEntity -> flightEntity.getDepartureTime().isAfter(now) &&
                                flightEntity.getDepartureTime().isBefore(next24Hours)).toList());
    }

    @Override
    public FlightDto searchFlight(String destination, LocalDateTime departureTime) {
        FlightEntity flightEntity = flightRepository.findByDestinationAndAndDepartureTime(destination, departureTime).orElseThrow(() -> new RecordNotFoundException(ERR_01.getErrorCode(), ERR_01.getErrorDescription()));
        return flightMapper.mapToDto(flightEntity);
    }


}
