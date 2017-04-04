package br.com.wplex.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.wplex.model.entity.Garage;

/**
 * The Garage Resource Interface.
 * 
 * @author Ryan Padilha <ryan.padilha@wplex.com.br>
 * @since 0.1
 *
 */
public interface GarageResource {

	ResponseEntity<List<Garage>> list();

	ResponseEntity<Garage> get(Long id);

	ResponseEntity<Garage> create(Garage garage);

	ResponseEntity<Garage> update(Long id, Garage garage);

	ResponseEntity<Garage> delete(Long id);
}
