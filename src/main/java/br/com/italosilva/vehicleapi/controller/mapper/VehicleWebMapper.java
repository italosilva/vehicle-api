package br.com.italosilva.vehicleapi.controller.mapper;

import br.com.italosilva.vehicleapi.controller.dto.VehicleDto;
import br.com.italosilva.vehicleapi.model.VehicleJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class VehicleWebMapper {

    public VehicleDto mapToDto(VehicleJpaEntity vehicleJpaEntity) {
        return new VehicleDto()
                .setId(vehicleJpaEntity.getId())
                .setPlate(vehicleJpaEntity.getPlate())
                .setModel(vehicleJpaEntity.getModel())
                .setManufacturer(vehicleJpaEntity.getManufacturer())
                .setColor(vehicleJpaEntity.getColor())
                .setStatus(vehicleJpaEntity.getStatus());
    }
}
