package com.boot.flightbookingappspring.service;

import com.boot.flightbookingappspring.model.dto.BookingDto;

import java.util.List;

public interface BookingService {
    void bookFlight(BookingDto bookingDto);

    BookingDto retrieveBooking(Long id);

    List<BookingDto> retrieveAllBookings();

    void cancelBooking(Long id);

    void updateBooking(Long id, BookingDto bookingDto);

    List<BookingDto> retrieveAllBookingsByName(String passengerName);
}
