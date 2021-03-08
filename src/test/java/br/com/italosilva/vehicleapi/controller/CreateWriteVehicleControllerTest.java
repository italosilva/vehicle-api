package br.com.italosilva.vehicleapi.controller;

import br.com.italosilva.vehicleapi.controller.resource.CreateVehicleResource;
import br.com.italosilva.vehicleapi.service.CreateVehicleService;
import br.com.italosilva.vehicleapi.util.ContractTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.Stream;

public class CreateWriteVehicleControllerTest extends ContractTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateVehicleService createVehicleService;

    @ParameterizedTest
    @MethodSource("validData")
    void givenValidDataWhenCallPostAndTestContractThenReturnStatusCodeOk(String plate, String manufacturer, String model, String color) throws Exception {

        var body = new CreateVehicleResource();
        body.setPlate(plate);
        body.setModel(model);
        body.setManufacturer(manufacturer);
        body.setColor(color);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    private static Stream<Arguments> validData() {
        return Stream.of(
                Arguments.of("DMS4152", "Audi", "AA", "Vermelho"),
                Arguments.of("DMS4152", "Audi Sport", "AA", "Vermelho"),
                Arguments.of("DMS4152", "Audi-Sport", "AA", "Vermelho"),
                Arguments.of("DMS4152", "Audi.Sport", "AA", "Vermelho"),
                Arguments.of("DMS4152", "Audi", "A5", "Vermelho"),
                Arguments.of("DMS4152", "Audi", "A 5", "Vermelho"),
                Arguments.of("DMS4152", "Audi", "A-5", "Vermelho"),
                Arguments.of("DMS4152", "Audi", "A.5", "Vermelho"),
                Arguments.of("DMS4152", "Audi", "AA", "Azul Marinho"),
                Arguments.of("DMS4152", "Audi", "AA", "Azul-Marinho"),
                Arguments.of("DMS4152", "Audi", "AA", "Azul.Marinho")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidData")
    void givenInvalidDataWhenCallPostAndTestContractThenReturnStatusCodeBadRequest(String plate, String manufacturer, String model, String color) throws Exception {

        var body = new CreateVehicleResource();
        body.setPlate(plate);
        body.setModel(model);
        body.setManufacturer(manufacturer);
        body.setColor(color);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private static Stream<Arguments> invalidData() {
        return Stream.of(
                Arguments.of(null, "Peugeot", "206", "Red"),
                Arguments.of("DM-4152", "Peugeot", "206", "Red"),
                Arguments.of("DMS44152", "Peugeot", "206", "Red"),
                Arguments.of("DMS4152", null, "206", "Red"),
                Arguments.of("DMS4152", "Chevrolet Chevrolet Chevrolet", "206", "Red"),
                Arguments.of("DMS4152", "Peugeot", null, "Red"),
                Arguments.of("DMS4152", "Peugeot", "1234567890123456789012345678901", "Red"),
                Arguments.of("DMS4152", "Peugeot", "206", null),
                Arguments.of("DMS4152", "Peugeot", "206", "12345")
        );
    }
}
