package org.hung.mapstruct.polymorphism.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarDto extends VehicleDto {

    private String type;
    private String brand;
    private String model;
    private long mileage;
    private String fuelType;
}
