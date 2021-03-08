package br.com.italosilva.vehicleapi.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class VehicleJpaEntityTest {

    @Test
    void givenValidDataWhenInstanceObjectThenValidGettersOnSuccess() {

        var id = 1L;
        var plate = "DMS4152";
        var model = "206";
        var manufacturer = "Peugeot";
        var color = "Red";
        var status = Boolean.TRUE;
        var createdDate = LocalDateTime.now();

        var vehicleJpa = new VehicleJpaEntity()
                .setId(id)
                .setPlate(plate)
                .setModel(model)
                .setManufacturer(manufacturer)
                .setColor(color)
                .setStatus(status)
                .setCreated(createdDate);

        Assertions.assertEquals(id, vehicleJpa.getId());
        Assertions.assertEquals(plate, vehicleJpa.getPlate());
        Assertions.assertEquals(model, vehicleJpa.getModel());
        Assertions.assertEquals(manufacturer, vehicleJpa.getManufacturer());
        Assertions.assertEquals(color, vehicleJpa.getColor());
        Assertions.assertEquals(status, vehicleJpa.getStatus());
        Assertions.assertEquals(createdDate, vehicleJpa.getCreated());
    }
}
