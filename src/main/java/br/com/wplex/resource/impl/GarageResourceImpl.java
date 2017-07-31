package br.com.wplex.resource.impl;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@Api(value = "/api/v1/garages", tags = { "garage" })
@RestController
@RequestMapping(value = "/api/v1/garages")
public class GarageResourceImpl implements GarageResource {

	@Autowired
	private GarageService service;

	@ApiOperation(value = "Retrieves a list of garages", tags = { "garage" }, code = 200)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retrieves a list of garages", responseContainer = "List", response = Garage.class),
			@ApiResponse(code = 204, message = "No content retrieve", responseContainer = "List", response = Garage.class) })
	@Override
	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public ResponseEntity<List<Garage>> list() {
		final List<Garage> garages = service.list();

		if (garages.isEmpty())
			return new ResponseEntity<List<Garage>>(Collections.emptyList(), HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Garage>>(garages, HttpStatus.OK);
	}

	@ApiOperation(value = "Get a garage by id", tags = { "garage" }, code = 200)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retrieve a garage searched by id", response = Garage.class),
			@ApiResponse(code = 204, message = "No content retrieve searched by id", response = Garage.class) })
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Garage> get(@ApiParam(value = "Garage Id", required = true) @PathVariable("id") Long id) {
		final Garage garage = service.get(id);

		if (null == garage)
			return new ResponseEntity<Garage>(new Garage(), HttpStatus.NO_CONTENT);

		return new ResponseEntity<Garage>(garage, HttpStatus.OK);
	}

	@ApiOperation(value = "Create a garage resource", tags = { "garage" }, code = 201)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Create a new garage resource", response = Garage.class),
			@ApiResponse(code = 304, message = "Return a resource not modified", response = Garage.class),
			@ApiResponse(code = 400, message = "Client Bad Request", response = Void.class) })
	@Override
	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	public ResponseEntity<Garage> create(
			@ApiParam(value = "Garage json stream resource", name = "garage", required = true) @Valid @RequestBody Garage garage) {
		final Garage persisted = service.insert(garage);

		if (null == persisted)
			return new ResponseEntity<Garage>(garage, HttpStatus.NOT_MODIFIED);

		return new ResponseEntity<>(persisted, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update a garage resource", tags = { "garage" }, code = 200)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retrieve an updage garage resource", response = Garage.class),
			@ApiResponse(code = 400, message = "Client Bad Request", response = Void.class),
			@ApiResponse(code = 404, message = "Not found retrieve if no update was process", response = Garage.class) })
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Garage> update(@ApiParam(value = "Garage Id", required = true) @PathVariable("id") Long id,
			@ApiParam(value = "Garage json stream resource", required = true) @Valid @RequestBody Garage garage) {
		final Garage persisted = service.update(id, garage);

		if (null == persisted)
			return new ResponseEntity<>(new Garage(), HttpStatus.NOT_FOUND);

		return new ResponseEntity<Garage>(persisted, HttpStatus.OK);
	}

	@ApiOperation(value = "Delete a garage resource", tags = { "garage" }, code = 204)
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "No content retrieve, deleted garage resource", response = Garage.class),
			@ApiResponse(code = 404, message = "Not found retrieve if no delete was process", response = Void.class) })
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Garage> delete(@ApiParam(value = "Garage Id", required = true) @PathVariable("id") Long id) {
		final Garage persisted = service.delete(id);

		if (null == persisted)
			return new ResponseEntity<>(new Garage(), HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(new Garage(), HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Search garages by company id", tags = { "garage" }, code = 200)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retrieves a list of garages"),
			@ApiResponse(code = 404, message = "No content found by id") })
	@RequestMapping(value = "/search/company/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Garage>> findByCompanyId(
			@ApiParam(value = "Company Id", required = true) @PathVariable("id") Long id) {
		final List<Garage> garages = service.findByCompanyId(id);

		if (garages.isEmpty())
			return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);

		return new ResponseEntity<>(garages, HttpStatus.OK);
	}

	@ApiOperation(value = "Search garages and paginating", tags = { "garage" }, code = 200)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retrieves a list of garages paginated"),
			@ApiResponse(code = 404, message = "No content found") })
	@RequestMapping(value = "/search/page", method = RequestMethod.GET)
	public ResponseEntity<Page<Garage>> getPage(
			@ApiParam(value = "Number of the page. Default is 0.", name = "page", required = false) @RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@ApiParam(value = "Limit of the page. Default is 10.", name = "limit", required = false) @RequestParam(name = "limit", defaultValue = "10", required = false) int limit,
			@ApiParam(value = "Field name of order. Default is name.", name = "order", required = false) @RequestParam(name = "order", defaultValue = "name", required = false) String order,
			@ApiParam(value = "Sorting flag of filed. Default is ASC [DESC].", name = "direction", required = false) @RequestParam(name = "direction", defaultValue = "ASC", required = false) Sort.Direction direction) {
		final Page<Garage> garages = service.findAllByPage(page, limit, order, direction);

		if (null != garages && garages.getContent().isEmpty())
			return new ResponseEntity<>(new PageImpl<>(Collections.emptyList()), HttpStatus.NO_CONTENT);

		return new ResponseEntity<>(garages, HttpStatus.OK);
	}

}
