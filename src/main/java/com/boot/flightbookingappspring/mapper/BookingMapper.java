package com.boot.flightbookingappspring.mapper;

import com.boot.flightbookingappspring.dao.entity.BookingEntity;
import com.boot.flightbookingappspring.dao.entity.FlightEntity;
import com.boot.flightbookingappspring.model.dto.BookingDto;
import com.boot.flightbookingappspring.model.dto.FlightDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookingMapper {
    BookingMapper bookingMapper = Mappers.getMapper(BookingMapper.class);

    BookingDto mapToDto(BookingEntity bookingEntity);

    BookingEntity mapToEntity(BookingDto bookingDto);

    List<BookingDto> mapToDtoList(List<BookingEntity> bookingEntities);
}
