package br.com.wplex.resource.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.wplex.model.entity.Garage;
import br.com.wplex.resource.GarageResource;
import br.com.wplex.service.GarageService;

/**
 * The Garage Resource REST API.
 * 
 * @author Ryan Padilha <ryan.padilha@wplex.com.br>
 * @since 0.1
 *
 */
@RestController
@RequestMapping(value = "/api/v1/garage")
public class GarageResourceImpl implements GarageResource {

	@Autowired
	private GarageService service;

	@Override
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<Garage>> list() {
		List<Garage> garages = service.list();

		if (garages.isEmpty())
			return new ResponseEntity<List<Garage>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Garage>>(garages, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Garage> get(@PathVariable("id") Long id) {
		Garage garage = service.get(id);

		if (null == garage)
			return new ResponseEntity<Garage>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<Garage>(garage, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Garage> create(@RequestBody Garage garage) {
		Garage persisted = service.insert(garage);

		if (null == persisted)
			return new ResponseEntity<Garage>(HttpStatus.NOT_MODIFIED);

		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
	}

	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Garage> update(@PathVariable("id") Long id, @RequestBody Garage garage) {
		Garage persisted = service.update(id, garage);

		if (null == persisted)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<Garage>(persisted, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Garage> delete(@PathVariable("id") Long id) {
		Garage persisted = service.delete(id);

		if (null == persisted)
			return new ResponseEntity<Garage>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
