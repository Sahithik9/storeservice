package com.grocer.store.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.grocer.store.model.Consumer;
import com.grocer.store.model.Grocer;

@SpringBootTest
public class StockControllerTest {

	@Autowired
	StockController stockController;

	@Test
	public void grocerRegistration() {
		Grocer grocer = new Grocer(RandomStringUtils.randomAlphanumeric(8),
				Arrays.asList("Flour", "Candies", "Bread", "milk", "icecream"), "Frisco");
		ResponseEntity<String> str = stockController.registerGrocer(grocer);
		assertEquals("200 OK", str.getStatusCode().toString());
	}

	@Test
	public void consumerRegistration() {
		Consumer payload = new Consumer(RandomStringUtils.randomAlphanumeric(8), "Centennial");
		ResponseEntity<String> str = stockController.registerConsumer(payload);
		assertEquals("200 OK", str.getStatusCode().toString());
	}

	@Test
	public void stockByLocation() {
		Grocer payload = new Grocer(RandomStringUtils.randomAlphanumeric(8),
				Arrays.asList("Flour", "Sugar", "cookies", "lays", "muffins"), "Centennial");
		stockController.registerGrocer(payload);
		ResponseEntity<List<Grocer>> result = stockController.getGrocerByLocation("Centennial");
		assertEquals("200 OK", result.getStatusCode().toString());
		assertEquals(payload.getLocation(), result.getBody().get(0).getLocation());
		assertTrue(!result.getBody().isEmpty());
	}
	
	@Test
	public void stockByLocationNoMatch() {
		Grocer payload = new Grocer(RandomStringUtils.randomAlphanumeric(8),
				Arrays.asList("Flour", "Sugar", "cookies", "lays", "muffins"), "Centennial");
		stockController.registerGrocer(payload);
		ResponseEntity<List<Grocer>> result = stockController.getGrocerByLocation("CCCCC");
		assertTrue(result.getBody().isEmpty());
	}

	@Test
	public void stockByItem() {
		Grocer payload = new Grocer(RandomStringUtils.randomAlphanumeric(8),
				Arrays.asList("Oranges", "Bread", "Coffee", "Lays", "Eggs"), "Centennial");
		stockController.registerGrocer(payload);
		ResponseEntity<List<Grocer>> result = stockController.getGrocerByItem("Coffee");
		assertEquals("200 OK", result.getStatusCode().toString());
		assertTrue(!result.getBody().isEmpty());
	}
	
	@Test
	public void stockByItemNoMatch() {
		Grocer payload = new Grocer(RandomStringUtils.randomAlphanumeric(8),
				Arrays.asList("Oranges", "Bread", "Coffee", "Lays", "Eggs"), "Centennial");
		stockController.registerGrocer(payload);
		ResponseEntity<List<Grocer>> result = stockController.getGrocerByItem("cake");
		assertEquals("200 OK", result.getStatusCode().toString());
		assertTrue(result.getBody().isEmpty());
	}

}
