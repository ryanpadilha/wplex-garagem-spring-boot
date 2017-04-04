package br.com.wplex.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Override
	public Garage insert(Garage garage) {
		garage.setInitials(garage.getInitials().toUpperCase());
		return repository.save(garage);
	}

	@Override
	public Garage update(Long id, Garage garage) {
		Garage persisted = repository.findOne(id);

		if (null == persisted)
			return null;

		persisted.setName(garage.getName());
		persisted.setInitials(garage.getInitials().toUpperCase());

		return repository.save(persisted);
	}

	@Override
	public Garage delete(Long id) {
		Garage persisted = repository.findOne(id);

		if (null == persisted)
			return null;

		repository.delete(persisted);
		return persisted;
	}

}
