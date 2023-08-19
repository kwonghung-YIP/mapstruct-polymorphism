package org.hung.mapstruct.polymorphism.mapper;

import org.hung.mapstruct.polymorphism.dto.CarDto;
import org.hung.mapstruct.polymorphism.dto.PageResponseDto;
import org.hung.mapstruct.polymorphism.dto.TrainDto;
import org.hung.mapstruct.polymorphism.dto.VehicleDto;
import org.hung.mapstruct.polymorphism.model.PageResponse;
import org.hung.mapstruct.polymorphism.model.Vehicle;
import org.hung.mapstruct.polymorphism.model.car.Car;
import org.hung.mapstruct.polymorphism.model.train.Train;
import org.mapstruct.*;

@Mapper(uses = { CarMapper.class, TrainMapper.class })
public interface VehicleMapper {

    @BeanMapping( subclassExhaustiveStrategy = SubclassExhaustiveStrategy.RUNTIME_EXCEPTION )
    @SubclassMapping( target = Train.class, source = TrainDto.class )
    @SubclassMapping( target = Car.class, source = CarDto.class )
    Vehicle map(VehicleDto vehicleDto, @Context VehicleMapperContext context);

    @Mapping(target = "results")
    PageResponse map(PageResponseDto response, @Context VehicleMapperContext context);
}
