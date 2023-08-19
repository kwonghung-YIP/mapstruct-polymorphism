package org.hung.mapstruct.polymorphism.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Attribute {
    private final String key;
    private final String value;
}
