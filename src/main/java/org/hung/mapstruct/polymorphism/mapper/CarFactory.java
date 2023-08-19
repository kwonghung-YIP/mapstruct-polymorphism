package org.hung.mapstruct.polymorphism.mapper;

import org.hung.mapstruct.polymorphism.dto.CarDto;
import org.hung.mapstruct.polymorphism.model.car.Car;
import org.mapstruct.ObjectFactory;
import org.springframework.stereotype.Component;

@Component
public class CarFactory {

    @ObjectFactory
    public Car newInstance(CarDto carDto) {
        Car car = Car.CarType.newInstance(carDto.getType());
        return car;
    }
}
