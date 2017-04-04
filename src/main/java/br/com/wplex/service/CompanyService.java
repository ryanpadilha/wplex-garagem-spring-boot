package br.com.wplex.service;

import java.util.List;

import br.com.wplex.model.entity.Company;

/**
 * The Company Interface Service.
 * 
 * @author Ryan Padilha <ryan.padilha@wplex.com.br>
 * @since 0.1
 *
 */
public interface CompanyService {

	List<Company> list();

	Company get(Long id);

	Company insert(Company company);

	Company update(Long id, Company company);

	Company delete(Long id);
}
