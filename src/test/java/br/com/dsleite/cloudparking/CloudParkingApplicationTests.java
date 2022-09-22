package br.com.dsleite.cloudparking;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import br.com.dsleite.cloudparking.dto.ParkingCreateDTO;
import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudParkingApplicationTests {

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
			.statusCode(HttpStatus.OK.value())
			.body("license[0]", Matchers.equalTo("DMS-1111"));
	}

	@Test
	void whenCreatedThenCheckIsCreated() {

		String license = "ABC-9900";
		ParkingCreateDTO createDTO = new ParkingCreateDTO();
		createDTO.setColor("AMARELO");
		createDTO.setLicense(license);
		createDTO.setModel("FUSCA");
		createDTO.setModel("MG");
		
		RestAssured.given()
			.when()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(createDTO)
			.post("/parking")
			.then()
			.statusCode(HttpStatus.CREATED.value())
			.body("license", Matchers.equalTo(license));
	}

}
