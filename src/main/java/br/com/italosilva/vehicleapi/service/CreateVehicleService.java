package br.com.italosilva.vehicleapi.service;

import br.com.italosilva.vehicleapi.model.VehicleJpaEntity;
import br.com.italosilva.vehicleapi.repository.VehicleRepository;
import br.com.italosilva.vehicleapi.service.exception.VehicleExistentException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateVehicleService {

    private final VehicleRepository vehicleRepository;

    public CreateVehicleService(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }

    public void create(String plate, String model, String manufacturer, String color, boolean status) {

        if(vehicleRepository.findByPlate(plate).isPresent())
            throw new VehicleExistentException(plate);

        var vehicleJpa = new VehicleJpaEntity();
        vehicleJpa.setPlate(plate);
        vehicleJpa.setModel(model);
        vehicleJpa.setManufacturer(manufacturer);
        vehicleJpa.setColor(color);
        vehicleJpa.setStatus(status);
        vehicleJpa.setCreated(LocalDateTime.now());

        vehicleRepository.save(vehicleJpa);

    }
}
