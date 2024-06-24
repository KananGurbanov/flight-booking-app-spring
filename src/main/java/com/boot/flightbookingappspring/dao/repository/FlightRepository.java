package com.boot.flightbookingappspring.dao.repository;

import com.boot.flightbookingappspring.dao.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, Long> {
    Optional<FlightEntity> findByDestinationAndAndDepartureTime(String destination, LocalDateTime departureTime);
}
