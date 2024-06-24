package com.boot.flightbookingappspring.dao.repository;

import com.boot.flightbookingappspring.dao.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
}
