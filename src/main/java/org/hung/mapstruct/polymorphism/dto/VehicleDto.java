package org.hung.mapstruct.polymorphism.dto;

import lombok.Data;

import java.util.List;

@Data
public abstract class VehicleDto {

    private List<Attribute> attributes;
}
