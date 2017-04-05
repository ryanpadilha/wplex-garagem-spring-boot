package br.com.wplex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.wplex.model.entity.Garage;

/**
 * The Garage Repository.
 * 
 * @author Ryan Padilha <ryan.padilha@wplex.com.br>
 * @since 0.1
 *
 */
public interface GarageRepository extends JpaRepository<Garage, Long> {

	@Query("SELECT g FROM Garage g WHERE g.company.id = :id")
	List<Garage> findByCompanyId(@Param("id") Long id);
}
