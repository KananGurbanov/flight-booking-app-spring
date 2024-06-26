package com.boot.flightbookingappspring.mapper;

import com.boot.flightbookingappspring.dao.entity.BookingEntity;
import com.boot.flightbookingappspring.dao.entity.FlightEntity;
import com.boot.flightbookingappspring.model.dto.BookingDto;
import com.boot.flightbookingappspring.model.dto.FlightDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingMapper {

    BookingDto mapToDto(BookingEntity bookingEntity);

    BookingEntity mapToEntity(BookingDto bookingDto);

    List<BookingDto> mapToDtoList(List<BookingEntity> bookingEntities);
}
