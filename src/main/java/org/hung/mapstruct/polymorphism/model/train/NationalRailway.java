package org.hung.mapstruct.polymorphism.model.train;

import lombok.Data;

@Data
public class NationalRailway extends Train {

    private String engineType;
    private int noOfCoaches;
    private boolean withToilet;
}
