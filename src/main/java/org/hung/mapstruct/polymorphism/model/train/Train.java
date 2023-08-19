package org.hung.mapstruct.polymorphism.model.train;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hung.mapstruct.polymorphism.model.Vehicle;
import org.springframework.beans.BeanUtils;

@Data
public abstract class Train extends Vehicle {

    @RequiredArgsConstructor
    @Getter
    public enum TrainType {
        NATIONAL_RAILWAY(NationalRailway.class),
        TRAM(Tram.class),
        TUBE(Tube.class);

        private final Class<? extends Train> trainClass;

        public static Train newInstance(String trainType) {
            TrainType type = TrainType.valueOf(trainType);
            Train train = BeanUtils.instantiateClass(type.trainClass);
            train.setType(type);
            return train;
        }
    }

    private TrainType type;
    private String model;
}
