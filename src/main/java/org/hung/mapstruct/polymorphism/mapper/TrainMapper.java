package org.hung.mapstruct.polymorphism.mapper;

import org.hung.mapstruct.polymorphism.dto.Attribute;
import org.hung.mapstruct.polymorphism.dto.CarDto;
import org.hung.mapstruct.polymorphism.dto.TrainDto;
import org.hung.mapstruct.polymorphism.model.car.Car;
import org.hung.mapstruct.polymorphism.model.car.Micro;
import org.hung.mapstruct.polymorphism.model.car.SUV;
import org.hung.mapstruct.polymorphism.model.car.Truck;
import org.hung.mapstruct.polymorphism.model.train.NationalRailway;
import org.hung.mapstruct.polymorphism.model.train.Train;
import org.hung.mapstruct.polymorphism.model.train.Tram;
import org.hung.mapstruct.polymorphism.model.train.Tube;
import org.mapstruct.*;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Mapper(uses = { TrainFactory.class, AttributeMapper.class })
public interface TrainMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "type")
    @Mapping(target = "model")
    Train map(TrainDto trainDto, @Context VehicleMapperContext context);

    @BeforeMapping
    default void beforeMap(TrainDto trainDto, @Context VehicleMapperContext context) {
        if (trainDto!=null) {
            Map<String, Attribute> mapAttributes = trainDto.getAttributes().stream()
                    .collect(Collectors.toMap(Attribute::getKey, Function.identity()));
            context.setAttributesMap(mapAttributes);
        }
    }

    @AfterMapping
    default void afterMap(TrainDto trainDto, @MappingTarget Train train, @Context VehicleMapperContext context) {
        Map<String,Attribute> mapAttributes = context.getAttributesMap();
        if (train instanceof NationalRailway nationalRailway) {
            this.mapAttributes(mapAttributes, nationalRailway);
        } else if (train instanceof Tram tram) {
            this.mapAttributes(mapAttributes, tram);
        } else if (train instanceof Tube tube) {
            this.mapAttributes(mapAttributes, tube);
        }
    }

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "engineType")
    @Mapping(target = "noOfCoaches")
    @Mapping(target = "withToilet")
    void mapAttributes(Map<String, Attribute> map, @MappingTarget NationalRailway nationalRailway);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "lowerPlatform", source = "lowerPlatform")
    @Mapping(target = "allowCycle", source = "allowCycle")
    void mapAttributes(Map<String, Attribute> map, @MappingTarget Tram tram);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "airCondition")
    void mapAttributes(Map<String, Attribute> map, @MappingTarget Tube tube);
}
