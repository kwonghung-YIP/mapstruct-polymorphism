package org.hung.mapstruct.polymorphism.model.train;

import lombok.Data;

@Data
public class Tram extends Train {

    private boolean lowerPlatform;
    private boolean allowCycle;
}
