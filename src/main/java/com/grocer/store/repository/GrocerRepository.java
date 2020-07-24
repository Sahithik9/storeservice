package com.grocer.store.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.grocer.store.model.Grocer;

@Repository
public class GrocerRepository {

	private final static Logger LOGGER = LoggerFactory.getLogger(GrocerRepository.class);

	@Autowired
	JdbcTemplate template;

	public List<Grocer> getGrocerByItem(String item) {
		LOGGER.info("Querying grocer by Item");

		List<Grocer> items = template.query("SELECT * FROM Grocer WHERE ARRAY_CONTAINS(items,?)",
				(result, rowNum) -> new Grocer(result.getString("name"),
						Stream.of(result.getString("items").split(",")).collect(Collectors.toList()),
						result.getString("location")),
				item);
		return items;
	}

	public List<Grocer> getGrocerByLocation(String location) {
		LOGGER.info("Querying grocer by location");
		List<Grocer> items = template.query("SELECT * FROM Grocer WHERE location=?",
				(result, rowNum) -> new Grocer(result.getString("name"),
						Stream.of(result.getString("items").split(",")).collect(Collectors.toList()),
						result.getString("location")),
				location);
		return items;
	}

	public int addGrocer(String name, List<String> items, String location) {
		LOGGER.info("Adding data to database");
		String query = "INSERT INTO Grocer (name,items,location) VALUES(?,?,?)";
		return template.update(query, name, items.toArray(), location);
	}

}
