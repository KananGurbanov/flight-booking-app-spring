package com.boot.flightbookingappspring.mapper;

import com.boot.flightbookingappspring.dao.entity.FlightEntity;
import com.boot.flightbookingappspring.model.dto.FlightDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FlightMapper {

    FlightMapper flightMapper = Mappers.getMapper(FlightMapper.class);

    FlightDto mapToDto(FlightEntity flightEntity);

    FlightEntity mapToEntity(FlightDto flightDto);

    List<FlightDto> mapToDtoList(List<FlightEntity> flightEntities);
}
