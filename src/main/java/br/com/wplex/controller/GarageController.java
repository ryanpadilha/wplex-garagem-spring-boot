package br.com.wplex.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import br.com.wplex.model.entity.Garage;

/**
 * The Garage WEB-MVC Controller Interface.
 * 
 * @author Ryan Padilha <ryan.padilha@wplex.com.br>
 * @since 0.1
 *
 */
public interface GarageController {

	ModelAndView form();

	ModelAndView list();

	ModelAndView get(Long id);

	ModelAndView create(Garage garage, BindingResult result);

	ModelAndView update(Long id, Garage garage);

	ModelAndView delete(Long id);
}
