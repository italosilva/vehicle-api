package br.com.italosilva.vehicleapi.controller.resource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UpdateVehicleResource {

    @NotNull
    @Pattern(regexp = "(?=.*[\\-.a-zA-Z0-9])[\\-.a-zA-Z0-9\s]{1,30}", message = "Pattern invalid for field model")
    private String model;

    @NotNull
    @Pattern(regexp = "(?=.*[\\-.a-zA-Z])[\\-.a-zA-Z\s]{1,25}", message = "Pattern invalid for field manufacturer")
    private String manufacturer;

    @NotNull
    @Pattern(regexp = "(?=.*[\\-.a-zA-Z])[\\-.a-zA-Z\s]{1,25}", message = "Pattern invalid for field color")
    private String color;

    private boolean status;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
