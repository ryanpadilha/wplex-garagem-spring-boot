package br.com.wplex.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wplex.model.entity.Company;
import br.com.wplex.repository.CompanyRepository;
import br.com.wplex.service.CompanyService;

/**
 * The Company Service.
 * 
 * @author Ryan Padilha <ryan.padilha@wplex.com.br>
 * @since 0.1
 *
 */
@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository repository;

	@Override
	public List<Company> list() {
		List<Company> companies = repository.findAll();
		return companies;
	}

	@Override
	public Company get(Long id) {
		Company company = repository.findOne(id);
		return company;
	}

	@Override
	public Company insert(Company company) {
		Company persisted = repository.save(company);
		return persisted;
	}

	@Override
	public Company update(Long id, Company company) {
		Company persisted = repository.findOne(id);

		if (null == persisted)
			return null;

		persisted.setName(company.getName());
		persisted.setInitials(company.getInitials());

		return repository.save(persisted);
	}

	@Override
	public Company delete(Long id) {
		Company persisted = repository.findOne(id);

		if (null == persisted)
			return null;

		repository.delete(persisted);
		return persisted;
	}

}
