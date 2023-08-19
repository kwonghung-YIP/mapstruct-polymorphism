package org.hung.mapstruct.polymorphism.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PageResponseDto {

    private int pageNo;
    private int pageSize;
    private long start;
    private long end;

    private List<VehicleDto> results;
}
