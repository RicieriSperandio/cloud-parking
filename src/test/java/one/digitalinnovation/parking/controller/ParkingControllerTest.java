package one.digitalinnovation.parking.controller;

import io.restassured.RestAssured;
import one.digitalinnovation.parking.controller.dto.ParkingCreateDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerTest {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest(){
        RestAssured.port = randomPort;
    }

    @Test
    void whenFindAllThenCheckResult() {
        RestAssured.given()
                .when()
                .get("/parking")
                .then()
                .statusCode(HttpStatus.OK.value());


    }

    @Test
    void WhenCreateThenCheckIsCreated() {
        var createDTO =new ParkingCreateDTO();
        createDTO.setColor("VERDE");
        createDTO.setLicense("ADD-1212");
        createDTO.setModel("OPALA");
        createDTO.setState("PR");

        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createDTO)
                .post("/parking")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("license", Matchers.equalTo("ADD-1212"))
                .body("color", Matchers.equalTo("VERDE"));


    }
}