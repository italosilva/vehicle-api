package br.com.italosilva.vehicleapi.service;

import br.com.italosilva.vehicleapi.repository.VehicleRepository;
import br.com.italosilva.vehicleapi.service.exception.VehicleNonExistentException;
import org.springframework.stereotype.Service;

@Service
public class DeleteVehicleService {

    private final VehicleRepository vehicleRepository;

    public DeleteVehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public void delete(Long id) {

        var vehicleJpa = vehicleRepository.findById(id).orElseThrow(VehicleNonExistentException::new);

        vehicleRepository.delete(vehicleJpa);
    }
}
