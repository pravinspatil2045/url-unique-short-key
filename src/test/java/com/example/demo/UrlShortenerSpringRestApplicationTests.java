package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UrlShortenerSpringRestApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
    private int port;
	
	@Test
	public void getUniqueShortKey_returnUniqueShortKey() throws Exception{
		// arrange

		// act
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:"+ port +"/get?url=google.com",String.class);
		
		// assert
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
	}

}
