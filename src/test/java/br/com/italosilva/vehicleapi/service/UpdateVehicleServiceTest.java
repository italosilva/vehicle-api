package br.com.italosilva.vehicleapi.service;

import br.com.italosilva.vehicleapi.model.VehicleJpaEntity;
import br.com.italosilva.vehicleapi.repository.VehicleRepository;
import br.com.italosilva.vehicleapi.service.exception.VehicleNonExistentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class UpdateVehicleServiceTest {

    private UpdateVehicleService updateVehicleService;
    private final VehicleRepository vehicleRepository = Mockito.mock(VehicleRepository.class);

    @BeforeEach
    public void setUp() {
        this.updateVehicleService = new UpdateVehicleService(vehicleRepository);
    }

    @Test
    void givenAExistentVehicleInSystemWhenUpdateVehicleInSystemThenUpdateSuccess() throws Exception {

        var id = 1L;
        var model = "206";
        var manufacturer = "Peugeot";
        var color = "White";
        var status = true;

        Mockito.when(vehicleRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new VehicleJpaEntity()));

        updateVehicleService.update(id, model, manufacturer, color, status);

        Mockito.verify(vehicleRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(vehicleRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    void givenANonExistentVehicleInSystemWhenUpdateVehicleInSystemThenThrowException() throws Exception {

        var id = 1L;
        var model = "206";
        var manufacturer = "Peugeot";
        var color = "White";
        var status = true;

        Mockito.when(vehicleRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(VehicleNonExistentException.class, () -> updateVehicleService.update(id, model, manufacturer, color, status));
        Mockito.verify(vehicleRepository, Mockito.times(0)).save(Mockito.any());
        Mockito.verify(vehicleRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }


}
