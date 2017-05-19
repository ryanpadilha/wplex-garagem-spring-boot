package br.com.wplex.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		return repository.findAll();
	}

	@Override
	public Company get(Long id) {
		return repository.findOne(id);
	}

	@Transactional
	@Override
	public Company insert(Company company) {
		company.setInitials(company.getInitials().toUpperCase());
		return repository.save(company);
	}

	@Transactional
	@Override
	public Company update(Long id, Company company) {
		Company persisted = repository.findOne(id);

		if (null == persisted)
			return null;

		persisted.setName(company.getName());
		persisted.setInitials(company.getInitials().toUpperCase());

		return repository.save(persisted);
	}

	@Transactional
	@Override
	public Company delete(Long id) {
		Company persisted = repository.findOne(id);

		if (null == persisted)
			return null;

		repository.delete(persisted);
		return persisted;
	}

}
