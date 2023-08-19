package org.hung.mapstruct.polymorphism.mapper;


import org.hung.mapstruct.polymorphism.dto.Attribute;
import org.mapstruct.Mapper;

@Mapper
public interface AttributeMapper {
    default String mapString(Attribute attribute) {
        return attribute.getValue();
    }

    default Integer mapInteger(Attribute attribute) {
        return Integer.parseInt(attribute.getValue());
    }

    default Double mapDouble(Attribute attribute) {
        return Double.parseDouble(attribute.getValue());
    }
}
