package com.boot.flightbookingappspring.model.dto;

import com.boot.flightbookingappspring.dao.entity.PassengerEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingDto {
    Long id;

    @NonNull
    FlightDto flight;

    @NonNull
    List<PassengerEntity> passengers;
}
