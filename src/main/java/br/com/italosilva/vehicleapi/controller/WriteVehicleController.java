package br.com.italosilva.vehicleapi.controller;

import br.com.italosilva.vehicleapi.controller.resource.UpdateVehicleResource;
import br.com.italosilva.vehicleapi.controller.resource.CreateVehicleResource;
import br.com.italosilva.vehicleapi.service.CreateVehicleService;
import br.com.italosilva.vehicleapi.service.DeleteVehicleService;
import br.com.italosilva.vehicleapi.service.UpdateVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/vehicles")
@Validated
public class WriteVehicleController {

    @Autowired
    private CreateVehicleService createVehicleService;

    @Autowired
    private UpdateVehicleService updateVehicleService;

    @Autowired
    private DeleteVehicleService deleteVehicleService;

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody CreateVehicleResource createVehicleResource) {

        createVehicleService.create(createVehicleResource.getPlate(), createVehicleResource.getModel(), createVehicleResource.getManufacturer(), createVehicleResource.getColor(), createVehicleResource.isStatus());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @Valid @RequestBody UpdateVehicleResource updateVehicleResource) {

        updateVehicleService.update(id, updateVehicleResource.getModel(), updateVehicleResource.getManufacturer(), updateVehicleResource.getColor(), updateVehicleResource.isStatus());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        try {
            deleteVehicleService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
