package br.com.wplex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wplex.model.entity.Company;

/**
 * The Company Repository.
 * 
 * @author Ryan Padilha <ryan.padilha@wplex.com.br>
 * @since 0.1
 *
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
