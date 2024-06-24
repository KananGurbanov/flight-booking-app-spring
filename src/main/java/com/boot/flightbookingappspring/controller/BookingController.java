package com.boot.flightbookingappspring.controller;

import com.boot.flightbookingappspring.model.dto.BookingDto;
import com.boot.flightbookingappspring.model.dto.RestResponse;
import com.boot.flightbookingappspring.service.BookingService;
import com.boot.flightbookingappspring.service.FlightService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("aviasales")
@RequiredArgsConstructor
@Tag(name = "Booking Controller API", description = "booking controller")
public class BookingController {
    private final BookingService bookingService;


    @PostMapping("/bookings")
    ResponseEntity<Void> bookFlight(@RequestBody @NonNull BookingDto bookingDto){
        bookingService.bookFlight(bookingDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/bookings/{id}")
    ResponseEntity<RestResponse<BookingDto>> retrieveBooking(@PathVariable @NonNull Long id){
        BookingDto bookingDto = bookingService.retrieveBooking(id);
        RestResponse<BookingDto> restResponse = RestResponse.<BookingDto>builder()
                .data(bookingDto)
                .status("SUCCESS")
                .build();

        return ResponseEntity.ok(restResponse);
    }

    @GetMapping("/bookings")
    ResponseEntity<RestResponse<List<BookingDto>>> retrieveAllBookings(){
        List<BookingDto> bookingDtos = bookingService.retrieveAllBookings();
        RestResponse<List<BookingDto>> restResponse = RestResponse.<List<BookingDto>>builder()
                .status("SUCCESS")
                .data(bookingDtos)
                .build();
        return ResponseEntity.ok(restResponse);
    }

    @DeleteMapping("bookings/{id}")
    ResponseEntity<Void> cancelBooking(@PathVariable @NonNull Long id){
        bookingService.cancelBooking(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("bookings/{id}")
    ResponseEntity<Void> updateBooking(@PathVariable @NonNull Long id, @RequestBody @NonNull BookingDto bookingDto){
        bookingService.updateBooking(id, bookingDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/bookings/search")
    ResponseEntity<RestResponse<List<BookingDto>>> retrieveBookingsByName(@RequestParam @NonNull String fullName){
        List<BookingDto> bookingDtos = bookingService.retrieveAllBookingsByName(fullName);

        RestResponse<List<BookingDto>> restResponse = RestResponse.<List<BookingDto>>builder()
                .data(bookingDtos)
                .status("SUCCESS")
                .build();

        return ResponseEntity.ok(restResponse);
    }

}
