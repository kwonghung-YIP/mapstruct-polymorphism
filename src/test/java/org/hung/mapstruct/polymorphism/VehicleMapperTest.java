package org.hung.mapstruct.polymorphism;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hung.mapstruct.polymorphism.dto.Attribute;
import org.hung.mapstruct.polymorphism.dto.CarDto;
import org.hung.mapstruct.polymorphism.dto.PageResponseDto;
import org.hung.mapstruct.polymorphism.dto.TrainDto;
import org.hung.mapstruct.polymorphism.mapper.VehicleMapper;
import org.hung.mapstruct.polymorphism.mapper.VehicleMapperContext;
import org.hung.mapstruct.polymorphism.model.PageResponse;
import org.hung.mapstruct.polymorphism.model.Vehicle;
import org.hung.mapstruct.polymorphism.model.car.Car;
import org.hung.mapstruct.polymorphism.model.car.Micro;
import org.hung.mapstruct.polymorphism.model.car.SUV;
import org.hung.mapstruct.polymorphism.model.train.NationalRailway;
import org.hung.mapstruct.polymorphism.model.train.Train;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class VehicleMapperTest {

    @Autowired
    private VehicleMapper mapper;

    @Test
    void convertCarDtoToSUV() {
        CarDto carDto = CarDto.builder()
                .type("SUV")
                .brand("Toyota")
                .model("RAV4")
                .mileage(5340l)
                .fuelType("PETROL")
                .build();
        carDto.setAttributes(List.of(new Attribute("drivetrain", "FWD")));

        Vehicle vehicle = mapper.map(carDto, new VehicleMapperContext());

        assertThat(vehicle, instanceOf(SUV.class));
        SUV suv = (SUV)vehicle;
        assertThat(suv.getType(), is(Car.CarType.SUV));
        assertThat(suv.getMake(), is(carDto.getBrand()));
        assertThat(suv.getModel(), is(carDto.getModel()));
        assertThat(suv.getMileage(), is(carDto.getMileage()));
        assertThat(suv.getFuelType(), is(Car.FuelType.PETROL));
        assertThat(suv.getDrivetrain(), is("FWD"));
    }

    @Test
    void convertCarDtoToMicro() {
        CarDto carDto = CarDto.builder()
                .type("MICRO")
                .brand("m-ero")
                .model("Microlino")
                .mileage(333)
                .fuelType("ELECTRIC")
                .build();
        carDto.setAttributes(List.of(
                new Attribute("noOfSeats", "2"),
                new Attribute("noOfDoors","1"),
                new Attribute("maxLoadingWeight","330")));

        Vehicle vehicle = mapper.map(carDto, new VehicleMapperContext());

        assertThat(vehicle, instanceOf(Micro.class));
        Micro micro = (Micro)vehicle;
        assertThat(micro.getType(), is(Car.CarType.MICRO));
        assertThat(micro.getMake(), is(carDto.getBrand()));
        assertThat(micro.getModel(), is(carDto.getModel()));
        assertThat(micro.getMileage(), is(carDto.getMileage()));
        assertThat(micro.getFuelType(), is(Car.FuelType.ELECTRIC));
        assertThat(micro.getNoOfSeats(), is(2));
        assertThat(micro.getNoOfDoors(), is(1));
        assertThat(micro.getMaxLoadingWeight(), is(330));
    }

    @Test
    void convertTrainDtoToNationalRailway() {
        TrainDto trainDto = TrainDto.builder()
                .type("NATIONAL_RAILWAY")
                .model("9 Car Class 802 Hitachi Intercity Express Train")
                .build();
        trainDto.setAttributes(List.of(
                new Attribute("engineType","Electric"),
                new Attribute("noOfCoaches","9"),
                new Attribute("withToilet","true")));

        Vehicle vehicle = mapper.map(trainDto, new VehicleMapperContext());

        assertThat(vehicle, instanceOf(NationalRailway.class));
        NationalRailway nationalRailway = (NationalRailway)vehicle;
        assertThat(nationalRailway.getType(), is(Train.TrainType.NATIONAL_RAILWAY));
        assertThat(nationalRailway.getModel(), is(trainDto.getModel()));
        assertThat(nationalRailway.getEngineType(), is("Electric"));
        assertThat(nationalRailway.getNoOfCoaches(), is(9));
        assertThat(nationalRailway.isWithToilet(), is(true));
    }

    @Test
    void convertPageResponse() {
        CarDto carDto = CarDto.builder()
                .type("MICRO")
                .brand("m-ero")
                .model("Microlino")
                .mileage(333)
                .fuelType("ELECTRIC")
                .build();
        carDto.setAttributes(List.of(
                new Attribute("noOfSeats", "2"),
                new Attribute("noOfDoors","1"),
                new Attribute("maxLoadingWeight","330")));

        TrainDto trainDto = TrainDto.builder()
                .type("NATIONAL_RAILWAY")
                .model("9 Car Class 802 Hitachi Intercity Express Train")
                .build();
        trainDto.setAttributes(List.of(
                new Attribute("engineType","Electric"),
                new Attribute("noOfCoaches","9"),
                new Attribute("withToilet","true")));

        PageResponseDto dto = PageResponseDto.builder()
                .pageNo(99)
                .pageSize(20)
                .start(9999)
                .end(10019)
                .results(List.of(carDto,trainDto))
                .build();

        PageResponse resp = mapper.map(dto,new VehicleMapperContext());

        assertThat(resp.getResults(), hasSize(2));
        assertThat(resp.getResults().get(0), instanceOf(Micro.class));
        assertThat(resp.getResults().get(1), instanceOf(NationalRailway.class));
    }
}
