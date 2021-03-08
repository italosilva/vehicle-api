package br.com.italosilva.vehicleapi.controller;

import br.com.italosilva.vehicleapi.controller.dto.VehicleDto;
import br.com.italosilva.vehicleapi.controller.mapper.VehicleWebMapper;
import br.com.italosilva.vehicleapi.repository.VehicleRepository;
import br.com.italosilva.vehicleapi.service.exception.VehicleNonExistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping(value = "/v1/vehicles")
@Validated
public class ReadVehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleWebMapper vehicleWebMapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<VehicleDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleRepository.findById(id).map(vehicleWebMapper::mapToDto).orElseThrow(VehicleNonExistentException::new));
    }

    @GetMapping
    public Page<VehicleDto> getAll( @RequestParam(required = false) @Pattern(regexp = "([a-zA-Z0-9]{7})|(true)", message = "The filter parameter value is not in the expected standard") String filter,
                                    @RequestParam(defaultValue = "0") @Min(value = 0, message = "The value of the page parameter cannot be less than 0") int page,
                                    @RequestParam(defaultValue = "10") @Min(value = 1, message = "The value of the limit parameter cannot be less than 1") @Max(value = 10, message = "The value of the limit parameter cannot be greater than 10") int limit) {

        var pageable = PageRequest.of(page , limit);

        if(filter == null){
            return vehicleRepository.findAll(pageable).map(vehicleWebMapper::mapToDto);
        } else if(filter.matches("[a-zA-Z0-9]{7}"))  {
            return vehicleRepository.findByPlate(filter, pageable).map(vehicleWebMapper::mapToDto);
        } else {
            return vehicleRepository.findByStatusTrue(pageable).map(vehicleWebMapper::mapToDto);
        }
    }
}
