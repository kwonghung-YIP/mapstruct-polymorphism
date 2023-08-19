package org.hung.mapstruct.polymorphism.model.car;

import lombok.Data;

@Data
public class Truck extends Car {

    private String powertrain;
    private String horsepower;
    private String torque;
    private int seatCapacity;
    private long maxTowingCapacity;
    private long maxPayloadCapacity;

}
