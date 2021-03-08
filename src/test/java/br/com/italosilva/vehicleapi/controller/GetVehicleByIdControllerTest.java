package br.com.italosilva.vehicleapi.controller;

import br.com.italosilva.vehicleapi.controller.mapper.VehicleWebMapper;
import br.com.italosilva.vehicleapi.model.VehicleJpaEntity;
import br.com.italosilva.vehicleapi.repository.VehicleRepository;
import br.com.italosilva.vehicleapi.util.ContractTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

public class GetVehicleByIdControllerTest extends ContractTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VehicleRepository vehicleRepository;

    @MockBean
    private VehicleWebMapper vehicleWebMapper;

    @Test
    void givenAValidIdWhenSearchAVehicleAndTestContractThenReturnStatusCodeOk() throws Exception {

        var vehicleJpa = new VehicleJpaEntity();
        vehicleJpa.setStatus(Boolean.TRUE);

        Mockito.when(vehicleRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(vehicleJpa));
        Mockito.when(vehicleWebMapper.mapToDto(Mockito.any())).thenCallRealMethod();

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/vehicles/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void givenAInvalidIdWhenSearchAVehicleAndTestContractThenReturnStatusCodeNotFound() throws Exception {

        Mockito.when(vehicleRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/vehicles/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
