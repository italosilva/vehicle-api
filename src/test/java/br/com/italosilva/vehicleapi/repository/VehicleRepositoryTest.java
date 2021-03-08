package br.com.italosilva.vehicleapi.repository;

import br.com.italosilva.vehicleapi.model.VehicleJpaEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class VehicleRepositoryTest {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    void givenAJpaEntityWhenSaveThenSaveWithSuccess() {

        var plate = "DMS4152";
        var model = "206";
        var manufacturer = "Peugeot";
        var color = "Red";
        var status = Boolean.TRUE;
        var createdDate = LocalDateTime.now();

        var vehicleJpa = new VehicleJpaEntity()
                .setPlate(plate)
                .setModel(model)
                .setManufacturer(manufacturer)
                .setColor(color)
                .setStatus(status)
                .setCreated(createdDate);

        vehicleRepository.save(vehicleJpa);

        Assertions.assertNotNull(vehicleJpa.getId());
        Assertions.assertTrue(vehicleJpa.getId() > 0);
    }

    @Test
    void givenAJpaEntityWhenGetByPlateThenQueryReturnResult() {

        var plate = "DMS4152";
        var model = "206";
        var manufacturer = "Peugeot";
        var color = "Red";
        var status = Boolean.TRUE;
        var createdDate = LocalDateTime.now();

        var vehicleJpa = new VehicleJpaEntity()
                .setPlate(plate)
                .setModel(model)
                .setManufacturer(manufacturer)
                .setColor(color)
                .setStatus(status)
                .setCreated(createdDate);

        vehicleRepository.save(vehicleJpa);

        var optionalVehicle = vehicleRepository.findByPlate(plate);

        Assertions.assertTrue(optionalVehicle.isPresent());
    }


    @Test
    void givenAJpaEntityWhenGetByIdThenFindSuccess() {

        var plate = "DMS4154";
        var model = "206";
        var manufacturer = "Peugeot";
        var color = "Red";
        var status = Boolean.TRUE;
        var createdDate = LocalDateTime.now();

        var vehicleJpa = new VehicleJpaEntity()
                .setPlate(plate)
                .setModel(model)
                .setManufacturer(manufacturer)
                .setColor(color)
                .setStatus(status)
                .setCreated(createdDate);

        vehicleRepository.save(vehicleJpa);

        var optionalVehicle = vehicleRepository.findById(vehicleJpa.getId());

        Assertions.assertTrue(optionalVehicle.isPresent());

        var jpaEntity = optionalVehicle.get();

        Assertions.assertEquals(plate, jpaEntity.getPlate());
    }

    @Test
    void givenASearchWhenSearchAVehicleByPlateThenQueryReturnResult() {

        var plate = "DMS4155";
        var model = "206";
        var manufacturer = "Peugeot";
        var color = "Red";
        var status = Boolean.TRUE;
        var createdDate = LocalDateTime.now();

        var vehicleJpa = new VehicleJpaEntity()
                .setPlate(plate)
                .setModel(model)
                .setManufacturer(manufacturer)
                .setColor(color)
                .setStatus(status)
                .setCreated(createdDate);

        var page = 0;
        var size = 1;
        var pageable = PageRequest.of(page, size);

        vehicleRepository.save(vehicleJpa);

        var pageVehicle = vehicleRepository.findByPlate(plate, pageable);

        Assertions.assertNotNull(pageVehicle.getContent());
    }

    @Test
    void givenASearchWhenSearchAllVehiclesThenFindSuccess() {

        var plate = "DMS4157";
        var model = "206";
        var manufacturer = "Peugeot";
        var color = "Red";
        var status = Boolean.TRUE;
        var createdDate = LocalDateTime.now();

        var vehicleJpa = new VehicleJpaEntity()
                .setPlate(plate)
                .setModel(model)
                .setManufacturer(manufacturer)
                .setColor(color)
                .setStatus(status)
                .setCreated(createdDate);

        var page = 0;
        var size = 1;
        var pageable = PageRequest.of(page, size);

        vehicleRepository.save(vehicleJpa);

        var pageVehicle = vehicleRepository.findAll(pageable);

        Assertions.assertNotNull(pageVehicle.getContent());
        Assertions.assertTrue(pageVehicle.getContent().size() > 0);
    }


}