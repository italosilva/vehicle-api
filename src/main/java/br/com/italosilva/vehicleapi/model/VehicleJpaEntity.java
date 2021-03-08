package br.com.italosilva.vehicleapi.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicle")
public class VehicleJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String plate;

    private String model;

    private String manufacturer;

    private String color;

    private Boolean status;

    private LocalDateTime created;

    public Long getId() {
        return id;
    }

    public VehicleJpaEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPlate() {
        return plate;
    }

    public VehicleJpaEntity setPlate(String plate) {
        this.plate = plate;
        return this;
    }

    public String getModel() {
        return model;
    }

    public VehicleJpaEntity setModel(String model) {
        this.model = model;
        return this;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public VehicleJpaEntity setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public String getColor() {
        return color;
    }

    public VehicleJpaEntity setColor(String color) {
        this.color = color;
        return this;
    }

    public Boolean getStatus() {
        return status;
    }

    public VehicleJpaEntity setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public VehicleJpaEntity setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }
}
