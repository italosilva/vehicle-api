package br.com.italosilva.vehicleapi.controller.dto;

public class VehicleDto {

    private Long id;
    private String plate;
    private String model;
    private String manufacturer;
    private String color;
    private boolean status;

    public Long getId() {
        return id;
    }

    public VehicleDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPlate() {
        return plate;
    }

    public VehicleDto setPlate(String plate) {
        this.plate = plate;
        return this;
    }

    public String getModel() {
        return model;
    }

    public VehicleDto setModel(String model) {
        this.model = model;
        return this;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public VehicleDto setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public String getColor() {
        return color;
    }

    public VehicleDto setColor(String color) {
        this.color = color;
        return this;
    }

    public boolean isStatus() {
        return status;
    }

    public VehicleDto setStatus(boolean status) {
        this.status = status;
        return this;
    }
}
