package com.udacity.pricing;

import com.udacity.pricing.entity.Price;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PricingServiceApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() {
	}

	@Test
	public void getPrices() {
		ResponseEntity<Price> res =
				this.restTemplate.getForEntity("http://localhost:" + port + "/prices", Price.class);
		assertEquals(res.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void getPrice() {
		ResponseEntity<Price> res =
				this.restTemplate.getForEntity("http://localhost:" + port + "/prices/1", Price.class);
		assertEquals(res.getStatusCode(), HttpStatus.OK);
		assertEquals(res.getBody().getPrice().doubleValue(), 22.1, 22.1);
	}

}
