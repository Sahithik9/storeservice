package com.grocer.store.controller;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.grocer.store.model.Consumer;
import com.grocer.store.model.Grocer;
import com.grocer.store.service.StoreService;

/**
 * @author skodur003c
 *
 */
@RestController
@RequestMapping("/mystore")
public class StockController {

	private final static Logger LOGGER = LoggerFactory.getLogger(StockController.class);

	@Autowired
	StoreService storeService;

	/**
	 * GET method to get list of grocers based on location.
	 *
	 * @param location location requested
	 * @return List<Grocer> list of grocers available stock in the particular
	 *         location
	 */
	@GetMapping("/stocklist/location/{location}")
	public @ResponseBody ResponseEntity<List<Grocer>> getGrocerByLocation(@PathVariable("location") String location) {
		try {
			LOGGER.info("Request Received for getGrocerByLocation");
			return new ResponseEntity<List<Grocer>>(storeService.getGrocerByLocation(location), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Exception Occured for getGrocerByLocation" + e);
			return new ResponseEntity<List<Grocer>>(storeService.getGrocerByLocation(location), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * GET method to get list of grocers based on item.
	 *
	 * @param item item requested
	 * @return List<Grocer> list of grocers who sells particular item
	 */
	@GetMapping("/stocklist/item/{item}")
	public @ResponseBody ResponseEntity<List<Grocer>> getGrocerByItem(@PathVariable("item") String item) {
		try {
			LOGGER.info("Request Received for getGrocerByItem");
			return new ResponseEntity<List<Grocer>>(storeService.getGrocerByItem(item), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Exception Occured for getGrocerByItem" + e);
			return new ResponseEntity<List<Grocer>>(storeService.getGrocerByItem(item), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * POST method to register grocer.
	 *
	 * @param payload request PayLoad for Grocer that contains grocer's name, 5 sale
	 *                items and location.
	 * @return JSON string with 16 digit alphanumeric UID and HTTP code .
	 */
	@PostMapping(value = "/register/grocer", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> registerGrocer(@RequestBody Grocer payload) {
		JSONObject response = new JSONObject();
		try {
			LOGGER.info("Request Received to register grocer");
			storeService.registerGrocer(payload.getName(), payload.getItems(), payload.getLocation());
			response.put("id", RandomStringUtils.randomAlphanumeric(16));
			response.put("statusCode", HttpStatus.OK.toString());
		} catch (Exception e) {
			LOGGER.error("Exception Occured to register grocer" + e);
			response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.toString());
			return new ResponseEntity<String>(response.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
	}

	/**
	 * POST method to register consumer.
	 *
	 * @param payload request PayLoad for consumer that contains consumer's name,
	 *                location.
	 * @return JSON string with 16 digit alphanumeric UID and HTTP code .
	 */
	@PostMapping(value = "/register/consumer", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> registerConsumer(@RequestBody Consumer payload) {
		JSONObject response = new JSONObject();
		try {
			LOGGER.info("Request Received to register consumer");
			storeService.addConsumer(payload.getName(), payload.getLocation());
			response.put("id", RandomStringUtils.randomAlphanumeric(16));
			response.put("statusCode", HttpStatus.OK.toString());
		} catch (Exception e) {
			LOGGER.error("Exception Occured to register consumer" + e);
			response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.toString());
			return new ResponseEntity<String>(response.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
	}
}
