package br.com.italosilva.vehicleapi.service;

import br.com.italosilva.vehicleapi.model.VehicleJpaEntity;
import br.com.italosilva.vehicleapi.repository.VehicleRepository;
import br.com.italosilva.vehicleapi.service.exception.VehicleNonExistentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class DeleteVehicleServiceTest {

    private DeleteVehicleService deleteVehicleService;
    private final VehicleRepository vehicleRepository = Mockito.mock(VehicleRepository.class);

    @BeforeEach
    public void setUp() {
        this.deleteVehicleService = new DeleteVehicleService(vehicleRepository);
    }

    @Test
    void givenAExistentVehicleInSystemWhenDeleteVehicleInSystemThenDeleteSuccess() {

        var id = 1L;

        Mockito.when(vehicleRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new VehicleJpaEntity()));

        deleteVehicleService.delete(id);

        Mockito.verify(vehicleRepository, Mockito.times(1)).delete(Mockito.any());
        Mockito.verify(vehicleRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    void givenANonExistentVehicleInSystemWhenDeleteVehicleInSystemThenThrowException() {

        var id = 1L;

        Mockito.when(vehicleRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(VehicleNonExistentException.class, () -> deleteVehicleService.delete(id));
        Mockito.verify(vehicleRepository, Mockito.times(0)).delete(Mockito.any());
        Mockito.verify(vehicleRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }


}
