package org.hung.mapstruct.polymorphism.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PageResponse {

    private int pageNo;
    private int pageSize;

    private List<Vehicle> results;
}
