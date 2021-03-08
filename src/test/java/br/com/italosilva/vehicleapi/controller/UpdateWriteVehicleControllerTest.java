package br.com.italosilva.vehicleapi.controller;

import br.com.italosilva.vehicleapi.controller.resource.UpdateVehicleResource;
import br.com.italosilva.vehicleapi.service.UpdateVehicleService;
import br.com.italosilva.vehicleapi.service.exception.VehicleNonExistentException;
import br.com.italosilva.vehicleapi.util.ContractTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.Stream;

public class UpdateWriteVehicleControllerTest extends ContractTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UpdateVehicleService updateVehicleService;

    @ParameterizedTest
    @MethodSource("validData")
    void givenValidDataWhenCallPutAndTestContractThenReturnStatusCodeNoContent(String manufacturer, String model, String color) throws Exception {

        var body = new UpdateVehicleResource();
        body.setModel(model);
        body.setManufacturer(manufacturer);
        body.setColor(color);

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/vehicles/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    private static Stream<Arguments> validData() {
        return Stream.of(
                Arguments.of("Audi", "AA", "Vermelho"),
                Arguments.of("Audi Sport", "AA", "Vermelho"),
                Arguments.of("Audi-Sport", "AA", "Vermelho"),
                Arguments.of("Audi.Sport", "AA", "Vermelho"),
                Arguments.of("Audi", "A5", "Vermelho"),
                Arguments.of("Audi", "A 5", "Vermelho"),
                Arguments.of("Audi", "A-5", "Vermelho"),
                Arguments.of("Audi", "A.5", "Vermelho"),
                Arguments.of("Audi", "AA", "Azul Marinho"),
                Arguments.of("Audi", "AA", "Azul-Marinho"),
                Arguments.of("Audi", "AA", "Azul.Marinho")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidData")
    void givenInvalidDataWhenCallPostAndTestContractThenReturnStatusCodeBadRequest(String manufacturer, String model, String color) throws Exception {

        var body = new UpdateVehicleResource();
        body.setModel(model);
        body.setManufacturer(manufacturer);
        body.setColor(color);

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/vehicles/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private static Stream<Arguments> invalidData() {
        return Stream.of(
                Arguments.of( null, "206", "Red"),
                Arguments.of( "Chevrolet Chevrolet Chevrolet", "206", "Red"),
                Arguments.of( "Peugeot", null, "Red"),
                Arguments.of( "Peugeot", "1234567890123456789012345678901", "Red"),
                Arguments.of( "Peugeot", "206", null),
                Arguments.of( "Peugeot", "206", "12345")
        );
    }

    @Test
    void givenIdNonExistentInSystemWhenCallDeleteAndTestContractThenReturnStatusCodeNotFound() throws Exception {

        var model = "206";
        var manufacturer = "Peugeot";
        var color = "Red";

        var body = new UpdateVehicleResource();
        body.setModel(model);
        body.setManufacturer(manufacturer);
        body.setColor(color);

        Mockito.doThrow(new VehicleNonExistentException()).when(updateVehicleService).update(Mockito.anyLong(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyBoolean());

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/vehicles/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
