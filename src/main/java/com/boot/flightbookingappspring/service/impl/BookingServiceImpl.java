package com.boot.flightbookingappspring.service.impl;

import com.boot.flightbookingappspring.dao.entity.BookingEntity;
import com.boot.flightbookingappspring.dao.entity.FlightEntity;
import com.boot.flightbookingappspring.dao.repository.BookingRepository;
import com.boot.flightbookingappspring.dao.repository.FlightRepository;
import com.boot.flightbookingappspring.exceptions.NoAvailableSeatException;
import com.boot.flightbookingappspring.exceptions.RecordNotFoundException;
import com.boot.flightbookingappspring.mapper.BookingMapper;
import com.boot.flightbookingappspring.mapper.FlightMapper;
import com.boot.flightbookingappspring.model.dto.BookingDto;
import com.boot.flightbookingappspring.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.boot.flightbookingappspring.model.enums.Error.ERR_01;
import static com.boot.flightbookingappspring.model.enums.Error.ERR_02;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final FlightMapper flightMapper;
    private final BookingMapper bookingMapper;
    private final FlightRepository flightRepository;


    @Override
    public void bookFlight(BookingDto bookingDto) {
        FlightEntity flightEntity = flightRepository.findById(bookingDto.getFlight().getId()).orElseThrow(() -> new RecordNotFoundException(ERR_01.getErrorCode(), ERR_01.getErrorDescription()));
        if (bookingDto.getPassengers().size() > flightEntity.getAvailableSeats())
            throw new NoAvailableSeatException(ERR_02.getErrorCode(), ERR_02.getErrorDescription());
        flightEntity.setAvailableSeats(flightEntity.getAvailableSeats() - bookingDto.getPassengers().size());
        bookingRepository.save(bookingMapper.mapToEntity(bookingDto));
    }

    @Override
    public BookingDto retrieveBooking(Long id) {
        return bookingMapper.mapToDto(bookingRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(ERR_01.getErrorCode(), ERR_01.getErrorDescription())));
    }

    @Override
    public List<BookingDto> retrieveAllBookings() {
        return bookingMapper.mapToDtoList(bookingRepository.findAll());
    }

    @Override
    public void cancelBooking(Long id) {
        BookingEntity bookingEntity = bookingRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(ERR_01.getErrorCode(), ERR_01.getErrorDescription()));
        bookingEntity.getFlightEntity().setAvailableSeats(bookingEntity.getFlightEntity().getAvailableSeats() + bookingEntity.getPassengers().size());
        bookingRepository.delete(bookingEntity);

    }

    @Override
    public void updateBooking(Long id, BookingDto bookingDto) {
        BookingEntity bookingEntity = bookingRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(ERR_01.getErrorCode(), ERR_01.getErrorDescription()));
        bookingEntity.setFlightEntity(flightMapper.mapToEntity(bookingDto.getFlight()));
        bookingEntity.setPassengers(bookingDto.getPassengers());
        bookingRepository.save(bookingEntity);
    }

    @Override
    public List<BookingDto> retrieveAllBookingsByName(String passengerName) {
        return bookingMapper.mapToDtoList(bookingRepository.findAll().
                stream().
                filter(booking -> booking.getPassengers().
                        stream().
                        anyMatch(passenger -> passenger.getFullName().equalsIgnoreCase(passengerName))).
                toList());

    }
}
