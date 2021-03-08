package br.com.italosilva.vehicleapi.service.exception;

public class VehicleExistentException extends BusinessException {

    public VehicleExistentException(String plate) {
        super("Exists a vehicle with a plate %s in system".formatted(plate));
    }
}
