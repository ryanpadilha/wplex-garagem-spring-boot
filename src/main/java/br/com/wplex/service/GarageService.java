package br.com.wplex.service;

import java.util.List;

import br.com.wplex.model.entity.Garage;

/**
 * The Garage Service Interface.
 * 
 * @author Ryan Padilha <ryan.padilha@wplex.com.br>
 * @since 0.1
 *
 */
public interface GarageService {

	List<Garage> list();

	Garage get(Long id);

	Garage insert(Garage garage);

	Garage update(Long id, Garage garage);

	Garage delete(Long id);

	List<Garage> findByCompanyId(Long id);
}
