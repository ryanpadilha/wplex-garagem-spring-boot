package br.com.wplex.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.wplex.model.entity.Company;

/**
 * The Company Resource Interface.
 * 
 * @author Ryan Padilha <ryan.padilha@wplex.com.br>
 * @since 0.1
 *
 */
public interface CompanyResource {

	ResponseEntity<List<Company>> list();

	ResponseEntity<Company> get(Long id);

	ResponseEntity<Company> create(Company company);

	ResponseEntity<Company> update(Long id, Company company);

	ResponseEntity<Company> delete(Long id);
}
