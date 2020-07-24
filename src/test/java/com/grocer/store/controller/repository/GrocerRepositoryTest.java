package com.grocer.store.controller.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.grocer.store.model.Grocer;
import com.grocer.store.repository.GrocerRepository;

@SpringBootTest
public class GrocerRepositoryTest {

	@Autowired
	GrocerRepository grocerRepository;

	@Test
	public void testGrocerRegistration() {
		int result = grocerRepository.addGrocer(RandomStringUtils.randomAlphanumeric(8),
				Arrays.asList("Sugar", "Berries", "cookies", "lays", "muffins"), "Frisco");
		assertEquals(1, result);
	}

	@Test
	public void stockByLocation() {
		 grocerRepository.addGrocer(RandomStringUtils.randomAlphanumeric(8),
					Arrays.asList("Sugar", "Berries", "cookies", "lays", "muffins"), "Frisco");
		List<Grocer> result = grocerRepository.getGrocerByLocation("Frisco");
		assertTrue(!result.isEmpty());
		assertEquals("Frisco", result.get(0).getLocation());
	}

	@Test
	public void stockByLocationNoData() {
		 grocerRepository.addGrocer(RandomStringUtils.randomAlphanumeric(8),
					Arrays.asList("Sugar", "Berries", "cookies", "lays", "muffins"), "denver");
		List<Grocer> result = grocerRepository.getGrocerByLocation("AAAA");
		assertTrue(result.isEmpty());
	}

	@Test
	public void stockByItem() {
		 grocerRepository.addGrocer(RandomStringUtils.randomAlphanumeric(8),
					Arrays.asList("Sugar", "Berries", "cookies", "lays", "muffins"), "Frisco");
		List<Grocer> result = grocerRepository.getGrocerByItem("Berries");
		assertTrue(!result.isEmpty());
	}

	@Test
	public void stockByItemNoData() {
		 grocerRepository.addGrocer(RandomStringUtils.randomAlphanumeric(8),
					Arrays.asList("Sugar", "Berries", "cookies", "lays", "muffins"), "denver");
		List<Grocer> result = grocerRepository.getGrocerByItem("Berry");
		assertTrue(result.isEmpty());
	}

}
