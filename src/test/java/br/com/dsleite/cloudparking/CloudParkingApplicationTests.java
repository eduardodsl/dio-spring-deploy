package br.com.dsleite.cloudparking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

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
	void contextLoads() {
	}

}
