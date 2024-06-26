package com.boot.flightbookingappspring.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "BOOKINGS")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    FlightEntity flightEntity;

    @OneToMany(cascade = CascadeType.ALL)
    List<PassengerEntity> passengers;

}
