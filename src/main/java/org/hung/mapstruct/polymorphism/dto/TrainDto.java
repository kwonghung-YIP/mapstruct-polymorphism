package org.hung.mapstruct.polymorphism.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrainDto extends VehicleDto {

    private String type;
    private String model;

}
