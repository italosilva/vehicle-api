package br.com.italosilva.vehicleapi.service;

import br.com.italosilva.vehicleapi.repository.VehicleRepository;
import br.com.italosilva.vehicleapi.service.exception.VehicleNonExistentException;
import org.springframework.stereotype.Service;

@Service
public class UpdateVehicleService {

    private final VehicleRepository vehicleRepository;

    public UpdateVehicleService(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }

    public void update(Long id, String model, String manufacturer, String color, boolean status) {

        var vehicleJpa = vehicleRepository.findById(id).orElseThrow(VehicleNonExistentException::new);

        vehicleJpa.setModel(model);
        vehicleJpa.setManufacturer(manufacturer);
        vehicleJpa.setColor(color);
        vehicleJpa.setStatus(status);

        vehicleRepository.save(vehicleJpa);
    }
}
