package br.com.italosilva.vehicleapi.controller;

import br.com.italosilva.vehicleapi.service.DeleteVehicleService;
import br.com.italosilva.vehicleapi.service.exception.VehicleNonExistentException;
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

public class DeleteWriteVehicleControllerTest extends ContractTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DeleteVehicleService deleteVehicleService;

    @Test
    void givenIdExistentInSystemWhenCallDeleteAndTestContractThenReturnStatusCodeNoContent() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/vehicles/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    void givenIdNonExistentInSystemWhenCallDeleteAndTestContractThenReturnStatusCodeNotFound() throws Exception {

        Mockito.doThrow(new VehicleNonExistentException()).when(deleteVehicleService).delete(Mockito.anyLong());

        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/vehicles/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
