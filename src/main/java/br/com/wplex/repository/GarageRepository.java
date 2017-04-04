package br.com.wplex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wplex.model.entity.Garage;

/**
 * The Garage Repository.
 * 
 * @author Ryan Padilha <ryan.padilha@wplex.com.br>
 * @since 0.1
 *
 */
public interface GarageRepository extends JpaRepository<Garage, Long> {

}
