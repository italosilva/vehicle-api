package br.com.italosilva.vehicleapi.controller;

import br.com.italosilva.vehicleapi.model.VehicleJpaEntity;
import br.com.italosilva.vehicleapi.repository.VehicleRepository;
import br.com.italosilva.vehicleapi.util.ContractTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.stream.Stream;

public class GetVehiclesByParametersControllerTest extends ContractTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VehicleRepository vehicleRepository;

    @Test
    void givenASearchWhenSearchAllVehiclesAndWithPaginationAndTestContractThenReturnStatusCodeOk() throws Exception {

        var page = new PageImpl<VehicleJpaEntity>(Collections.emptyList());

        Mockito.when(vehicleRepository.findAll(Mockito.any(Pageable.class))).thenReturn(page);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/vehicles?page=0&limit=10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void givenASearchWhenSearchAVehicleByPlateAndWithPaginationAndTestContractThenReturnStatusCodeOk() throws Exception {

        var page = new PageImpl<VehicleJpaEntity>(Collections.emptyList());

        Mockito.when(vehicleRepository.findByPlate(Mockito.anyString(), Mockito.any(Pageable.class))).thenReturn(page);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/vehicles?filter=DMS4152")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void givenASearchWhenSearchAllVehiclesByStatusTrueAndWithPaginationAndTestContractThenReturnStatusCodeOk() throws Exception {

        var page = new PageImpl<VehicleJpaEntity>(Collections.emptyList());

        Mockito.when(vehicleRepository.findByStatusTrue(Mockito.any(Pageable.class))).thenReturn(page);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/vehicles?filter=true")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @ParameterizedTest
    @MethodSource("data")
    void givenASearchWithInvalidParametersInQueryParamWhenSearchAllVehiclesAndTestContractThenReturnStatusCodeBadRequest(String query) throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(query)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of("/v1/vehicles?page=-1&limit=10"),
                Arguments.of("/v1/vehicles?page=0&limit=11"),
                Arguments.of("/v1/vehicles?page=0&limit=-1"),
                Arguments.of("/v1/vehicles?page=a&limit=10"),
                Arguments.of("/v1/vehicles?page=0&limit=a"),
                Arguments.of("/v1/vehicles?page=-&limit=10"),
                Arguments.of("/v1/vehicles?page=0&limit=-"),
                Arguments.of("/v1/vehicles?filter=DMS-41522"),
                Arguments.of("/v1/vehicles?filter=DMS41522"),
                Arguments.of("/v1/vehicles?filter=DMS415"),
                Arguments.of("/v1/vehicles?filter=other")
        );
    }
}
