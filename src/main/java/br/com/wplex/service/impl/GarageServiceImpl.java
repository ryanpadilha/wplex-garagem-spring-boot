package br.com.wplex.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.wplex.model.entity.Garage;
import br.com.wplex.repository.GarageRepository;
import br.com.wplex.service.GarageService;

/**
 * The Garage Service.
 * 
 * @author Ryan Padilha <ryan.padilha@wplex.com.br>
 * @since 0.1
 *
 */
@Service
public class GarageServiceImpl implements GarageService {

	@Autowired
	private GarageRepository repository;

	@Override
	public List<Garage> list() {
		return repository.findAll();
	}

	@Override
	public Garage get(Long id) {
		return repository.findOne(id);
	}

	@Transactional
	@Override
	public Garage insert(Garage garage) {
		garage.setInitials(garage.getInitials().toUpperCase());
		return repository.save(garage);
	}

	@Transactional
	@Override
	public Garage update(Long id, Garage garage) {
		Garage persisted = repository.findOne(id);

		if (null == persisted)
			return null;

		persisted.setName(garage.getName());
		persisted.setInitials(garage.getInitials().toUpperCase());

		return repository.save(persisted);
	}

	@Transactional
	@Override
	public Garage delete(Long id) {
		Garage persisted = repository.findOne(id);

		if (null == persisted)
			return null;

		repository.delete(persisted);
		return persisted;
	}

	@Override
	public List<Garage> findByCompanyId(Long id) {
		return repository.findByCompanyId(id);
	}

	@Override
	public Page<Garage> findAllByPage(int page, int limit, String order, Sort.Direction direction) {
		return repository.findAll(new PageRequest(page, limit, new Sort(direction, order)));
	}

}
