package com.boot.flightbookingappspring.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "PASSENGERS")
public class PassengerEntity {
    @Id
    @Column(name = "ID")
    Long id;

    @Column(name = "FULL_NAME")
    String fullName;
}
