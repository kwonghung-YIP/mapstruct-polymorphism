package org.hung.mapstruct.polymorphism.model.car;

import lombok.Data;

@Data
public class SUV extends Car {

    private String motorType;
    private String drivetrain;
    private double batteryCapacity;
    private double cargoCapacity;

}
