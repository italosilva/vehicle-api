package br.com.italosilva.vehicleapi.service.exception;

public class VehicleNonExistentException extends BusinessException {

    public VehicleNonExistentException() {
        super("Vehicle non existent in system");
    }
}
