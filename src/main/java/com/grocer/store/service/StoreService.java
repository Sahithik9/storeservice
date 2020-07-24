package com.grocer.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocer.store.model.Grocer;
import com.grocer.store.repository.ConsumerRepository;
import com.grocer.store.repository.GrocerRepository;

@Service
public class StoreService {

	@Autowired
	GrocerRepository grocerRepo;

	@Autowired
	ConsumerRepository consumerRepo;

	public List<Grocer> getGrocerByLocation(String location) {
		return grocerRepo.getGrocerByLocation(location);

	}

	public List<Grocer> getGrocerByItem(String item) {
		return grocerRepo.getGrocerByItem(item);

	}

	public int registerGrocer(String name, List<String> items, String location) {
		return grocerRepo.addGrocer(name, items, location);
	}

	public int addConsumer(String name, String location) {
		return consumerRepo.addConsumer(name, location);
	}

}
