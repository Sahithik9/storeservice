package com.grocer.store.controller.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.grocer.store.repository.ConsumerRepository;

@SpringBootTest
public class ConsumerRepositoryTest {
	@Autowired
	ConsumerRepository consumerRepository;

	@Test
	public void testGrocerRegistration() {
		int result = consumerRepository.addConsumer(RandomStringUtils.randomAlphanumeric(8), "Frisco");
		assertEquals(1, result);
	}
}
