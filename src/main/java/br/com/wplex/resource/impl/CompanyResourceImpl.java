package br.com.wplex.resource.impl;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.wplex.model.entity.Company;
import br.com.wplex.resource.CompanyResource;
import br.com.wplex.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The Company Resource REST API.<br>
 * API definition documented by Swagger 2.0.
 * 
 * @author Ryan Padilha <ryan.padilha@wplex.com.br>
 * @since 0.1
 *
 */
@Api(value = "/api/v1/companies", tags = { "company" })
@RestController
@RequestMapping(value = "/api/v1/companies")
public class CompanyResourceImpl implements CompanyResource {

	@Autowired
	private CompanyService service;

	@ApiOperation(value = "Retrieves a list of companies", tags = { "company" }, code = 200)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retrieves a list of companies", responseContainer = "List", response = Company.class),
			@ApiResponse(code = 204, message = "No content retrieve, empty list", responseContainer = "List", response = Company.class) })
	@Override
	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public ResponseEntity<List<Company>> list() {
		final List<Company> companies = service.list();

		if (companies.isEmpty())
			return new ResponseEntity<List<Company>>(Collections.emptyList(), HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Company>>(companies, HttpStatus.OK);
	}

	@ApiOperation(value = "Get a company by id", tags = { "company" }, code = 200)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retrieve a company searched by id", response = Company.class),
			@ApiResponse(code = 204, message = "No content retrieve searched by id", response = Company.class) })
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Company> get(@ApiParam(value = "Company Id", required = true) @PathVariable("id") Long id) {
		final Company company = service.get(id);

		if (null == company)
			return new ResponseEntity<Company>(new Company(), HttpStatus.NO_CONTENT);

		return new ResponseEntity<Company>(company, HttpStatus.OK);
	}

	@ApiOperation(value = "Create a company resource", tags = { "company" }, code = 201)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Create a new company resource", response = Company.class),
			@ApiResponse(code = 304, message = "Return a resource not modified", response = Company.class),
			@ApiResponse(code = 400, message = "Client Bad Request", response = Void.class) })
	@Override
	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	public ResponseEntity<Company> create(
			@ApiParam(value = "Company json stream resource", name = "company", required = true) @Valid @RequestBody Company company) {
		final Company persisted = service.insert(company);

		if (null == persisted)
			return new ResponseEntity<Company>(company, HttpStatus.NOT_MODIFIED);

		return new ResponseEntity<Company>(persisted, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update a company resource", tags = { "company" }, code = 200)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retrieve an update company resource", response = Company.class),
			@ApiResponse(code = 404, message = "Not found retrieve if no update was process", response = Company.class),
			@ApiResponse(code = 400, message = "Client Bad Request", response = Void.class) })
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Company> update(@ApiParam(value = "Company Id", required = true) @PathVariable("id") Long id,
			@ApiParam(value = "Company json stream resource", required = true) @Valid @RequestBody Company company) {
		final Company persisted = service.update(id, company);

		if (null == persisted)
			return new ResponseEntity<>(new Company(), HttpStatus.NOT_FOUND);

		return new ResponseEntity<Company>(persisted, HttpStatus.OK);
	}

	@ApiOperation(value = "Delete a company resource", tags = { "company" }, code = 204)
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "No content retrive, deleted company resource", response = Company.class),
			@ApiResponse(code = 404, message = "Not found retrieve if no delete was process", response = Company.class) })
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Company> delete(
			@ApiParam(value = "Company Id", required = true) @PathVariable("id") Long id) {
		final Company persisted = service.delete(id);

		if (null == persisted)
			return new ResponseEntity<Company>(new Company(), HttpStatus.NOT_FOUND);

		return new ResponseEntity<Company>(new Company(), HttpStatus.NO_CONTENT);
	}

}
