package org.hung.mapstruct.polymorphism.mapper;

import org.hung.mapstruct.polymorphism.dto.TrainDto;
import org.hung.mapstruct.polymorphism.model.train.Train;
import org.mapstruct.ObjectFactory;
import org.springframework.stereotype.Component;

@Component
public class TrainFactory {

    @ObjectFactory
    public Train newInstance(TrainDto trainDto) {
        Train train = Train.TrainType.newInstance(trainDto.getType());
        return train;
    }
}
