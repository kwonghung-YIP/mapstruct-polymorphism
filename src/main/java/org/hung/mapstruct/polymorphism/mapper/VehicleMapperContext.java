package org.hung.mapstruct.polymorphism.mapper;

import lombok.Data;
import org.hung.mapstruct.polymorphism.dto.Attribute;

import java.util.Map;

@Data
public class VehicleMapperContext {
    private Map<String, Attribute> attributesMap;
}
