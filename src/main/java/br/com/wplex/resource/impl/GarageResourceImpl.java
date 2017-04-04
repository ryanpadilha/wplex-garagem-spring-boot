package br.com.wplex.resource.impl;

import java.util.List;

import javax.validation.Valid;

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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The Garage Resource REST API. <br>
 * API definition documented by Swagger 2.0.
 * 
 * @author Ryan Padilha <ryan.padilha@wplex.com.br>
 * @since 0.1
 *
 */
@Api(value = "/api/v1/garage", tags = { "garage" })
@RestController
@RequestMapping(value = "/api/v1/garage")
public class GarageResourceImpl implements GarageResource {

	@Autowired
	private GarageService service;

	@ApiOperation(value = "Retrieves a list of garages", tags = { "garage" }, code = 200)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retrieves a list of garages", responseContainer = "List", response = Garage.class),
			@ApiResponse(code = 204, message = "No content retrieve", responseContainer = "List", response = Void.class) })
	@Override
	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public ResponseEntity<List<Garage>> list() {
		List<Garage> garages = service.list();

		if (garages.isEmpty())
			return new ResponseEntity<List<Garage>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Garage>>(garages, HttpStatus.OK);
	}

	@ApiOperation(value = "Get a garage by id", tags = { "garage" }, code = 200)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retrieve a garage searched by id", response = Garage.class),
			@ApiResponse(code = 404, message = "Not found retrieve searched by id", response = Void.class) })
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Garage> get(@ApiParam(value = "Garage Id", required = true) @PathVariable("id") Long id) {
		Garage garage = service.get(id);

		if (null == garage)
			return new ResponseEntity<Garage>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<Garage>(garage, HttpStatus.OK);
	}

	@ApiOperation(value = "Create a garage resource", tags = { "garage" }, code = 201)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Create a new garage resource", response = Garage.class),
			@ApiResponse(code = 304, message = "Return a resource not modified", response = Garage.class) })
	@Override
	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	public ResponseEntity<Garage> create(
			@ApiParam(value = "Garage json stream resource", name = "garage", required = true) @Valid @RequestBody Garage garage) {
		Garage persisted = service.insert(garage);

		if (null == persisted)
			return new ResponseEntity<Garage>(HttpStatus.NOT_MODIFIED);

		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update a garage resource", tags = { "garage" }, code = 200)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retrieve an updage garage resource", response = Garage.class),
			@ApiResponse(code = 404, message = "Not found retrieve if no update was process", response = Void.class) })
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Garage> update(@ApiParam(value = "Garage Id", required = true) @PathVariable("id") Long id,
			@ApiParam(value = "Garage json stream resource", required = true) @Valid @RequestBody Garage garage) {
		Garage persisted = service.update(id, garage);

		if (null == persisted)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<Garage>(persisted, HttpStatus.OK);
	}

	@ApiOperation(value = "Delete a garage resource", tags = { "garage" }, code = 204)
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "No content retrieve, deleted garage resource", response = Garage.class),
			@ApiResponse(code = 404, message = "Not found retrieve if no delete was process", response = Void.class) })
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Garage> delete(@ApiParam(value = "Garage Id", required = true) @PathVariable("id") Long id) {
		Garage persisted = service.delete(id);

		if (null == persisted)
			return new ResponseEntity<Garage>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
