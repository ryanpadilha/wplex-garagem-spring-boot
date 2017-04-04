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
		List<Garage> garages = repository.findAll();
		return garages;
	}

	@Override
	public Garage get(Long id) {
		Garage garage = repository.findOne(id);
		return garage;
	}

	@Override
	public Garage insert(Garage garage) {
		Garage persisted = repository.save(garage);
		return persisted;
	}

	@Override
	public Garage update(Long id, Garage garage) {
		Garage persisted = repository.findOne(id);

		if (null == persisted)
			return null;

		persisted.setName(garage.getName());
		persisted.setInitials(garage.getInitials());

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
