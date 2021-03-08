package br.com.italosilva.vehicleapi.service;

import br.com.italosilva.vehicleapi.model.VehicleJpaEntity;
import br.com.italosilva.vehicleapi.repository.VehicleRepository;
import br.com.italosilva.vehicleapi.service.exception.VehicleExistentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class CreateVehicleServiceTest {

    private CreateVehicleService createVehicleService;
    private final VehicleRepository vehicleRepository = Mockito.mock(VehicleRepository.class);

    @BeforeEach
    public void setUp() {
        this.createVehicleService = new CreateVehicleService(vehicleRepository);
    }

    @Test
    void givenANewVehicleWhenCreateVehicleInSystemThenCreateSuccess() throws Exception {

        var plate = "DMS4152";
        var model = "206";
        var manufacturer = "Peugeot";
        var color = "White";
        var status = true;

        Mockito.when(vehicleRepository.findByPlate(Mockito.anyString())).thenReturn(Optional.empty());

        createVehicleService.create(plate, model, manufacturer, color, status);

        Mockito.verify(vehicleRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(vehicleRepository, Mockito.times(1)).findByPlate(Mockito.anyString());

    }

    @Test
    void givenAExistentVehicleInSystemWhenCreateVehicleInSystemThenThrowException() throws Exception {

        var plate = "DMS4152";
        var model = "206";
        var manufacturer = "Peugeot";
        var color = "White";
        var status = true;

        Mockito.when(vehicleRepository.findByPlate(Mockito.anyString())).thenReturn(Optional.of(new VehicleJpaEntity()));

        Assertions.assertThrows(VehicleExistentException.class, () -> createVehicleService.create(plate, model, manufacturer, color, status));
        Mockito.verify(vehicleRepository, Mockito.times(0)).save(Mockito.any());
        Mockito.verify(vehicleRepository, Mockito.times(1)).findByPlate(Mockito.anyString());

    }


}
