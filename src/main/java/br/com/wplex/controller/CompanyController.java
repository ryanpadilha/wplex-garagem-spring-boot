package br.com.wplex.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import br.com.wplex.model.entity.Company;

/**
 * The Company WEB-MVC Controller Interface.
 * 
 * @author Ryan Padilha <ryan.padilha@wplex.com.br>
 * @since 0.1
 *
 */
public interface CompanyController {

	ModelAndView form();

	ModelAndView list();

	ModelAndView get(Long id);

	ModelAndView create(Company company, BindingResult result);

	ModelAndView update(Long id, Company company);

	ModelAndView delete(Long id);
}
