package org.hung.mapstruct.polymorphism.model.car;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.hung.mapstruct.polymorphism.model.Vehicle;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;

@Data
public abstract class Car extends Vehicle {
    @RequiredArgsConstructor
    @Getter
    public enum CarType {
        MICRO(Micro.class),
        SUV(SUV.class),
        TRUCK(Truck.class),
        VAN(Van.class);

        private final Class<? extends Car> carClass;
        public static Car newInstance(String carType) {
            CarType type = CarType.valueOf(carType);
            Car car = BeanUtils.instantiateClass(type.carClass);
            car.setType(type);
            return car;
        }
    }

    public enum FuelType {
        PETROL,
        DIESEL,
        LPG,
        ELECTRIC,
        HYBRID;
    }

    private CarType type;
    private String make;
    private String model;
    private long mileage;
    private FuelType fuelType;
}
