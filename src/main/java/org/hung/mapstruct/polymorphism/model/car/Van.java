package org.hung.mapstruct.polymorphism.model.car;

import lombok.Data;

@Data
public class Van extends Car {

    private double loadSpace;
    private double maxLoadLength;
    private double maxPayload;
}
