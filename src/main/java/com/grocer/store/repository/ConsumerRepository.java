package com.grocer.store.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ConsumerRepository {

	@Autowired
	JdbcTemplate template;

	public int addConsumer(String name, String location) {
		String query = "INSERT INTO Consumer (name,location) VALUES(?,?)";
		return template.update(query, name, location);
	}

}
