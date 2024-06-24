package com.boot.flightbookingappspring.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlightDto {
    Long id;

    @NotBlank
    String origin;

    @NotBlank
    String destination;

    LocalDateTime departureTime;

    @NonNull
    @Min(value = 0)
    Integer availableSeats;
}
