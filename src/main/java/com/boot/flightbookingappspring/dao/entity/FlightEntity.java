package com.boot.flightbookingappspring.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "FLIGHT")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlightEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "ORIGIN")
    String origin;

    @Column(name = "DESTINATION")
    String destination;

    @Column(name = "DEPARTURE_TIME")
    LocalDateTime departureTime;

    @Column(name = "AVAILABLE_SEATS")
    Integer availableSeats;
}
