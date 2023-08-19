package org.hung.mapstruct.polymorphism.mapper;

import org.hung.mapstruct.polymorphism.dto.Attribute;
import org.hung.mapstruct.polymorphism.dto.CarDto;
import org.hung.mapstruct.polymorphism.model.car.*;
import org.mapstruct.*;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Mapper(uses = { CarFactory.class, AttributeMapper.class })
public interface CarMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "type")
    @Mapping(target = "model")
    @Mapping(target = "mileage")
    @Mapping(target = "make", source = "brand")
    @Mapping(target = "fuelType", source = "fuelType")
    Car map(CarDto carDto, @Context VehicleMapperContext context);

    @BeforeMapping
    default void beforeMap(CarDto carDto,  @Context VehicleMapperContext context) {
        if (carDto!=null) {
            Map<String,Attribute> mapAttributes = carDto.getAttributes().stream()
                    .collect(Collectors.toMap(Attribute::getKey, Function.identity()));
            context.setAttributesMap(mapAttributes);
        }
    }

    @AfterMapping
    default void afterMap(CarDto cartDto, @MappingTarget Car car, @Context VehicleMapperContext context) {
        Map<String,Attribute> mapAttributes = context.getAttributesMap();
        if (car instanceof SUV suv) {
            this.mapAttributes(mapAttributes, suv);
        } else if (car instanceof Truck truck) {
            this.mapAttributes(mapAttributes, truck);
        } else if (car instanceof Micro micro) {
            this.mapAttributes(mapAttributes, micro);
        }
    }

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "drivetrain", source = "map.drivetrain")
    @Mapping(target = "cargoCapacity", source = "map.cargoCapacity")
    void mapAttributes(Map<String, Attribute> map, @MappingTarget SUV suv);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "horsepower", source = "map.horsepower")
    @Mapping(target = "seatCapacity", source = "map.seatCapacity")
    void mapAttributes(Map<String, Attribute> map, @MappingTarget Truck truck);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "noOfSeats", source = "map.noOfSeats")
    @Mapping(target = "noOfDoors", source = "map.noOfDoors")
    @Mapping(target = "maxLoadingWeight", source = "map.maxLoadingWeight")
    void mapAttributes(Map<String, Attribute> map, @MappingTarget Micro micro);

}
