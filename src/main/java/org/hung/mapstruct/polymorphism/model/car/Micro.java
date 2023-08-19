package org.hung.mapstruct.polymorphism.model.car;

import lombok.Data;

@Data
public class Micro extends Car {

    private int noOfSeats;
    private int noOfDoors;
    private int maxLoadingWeight;

}
