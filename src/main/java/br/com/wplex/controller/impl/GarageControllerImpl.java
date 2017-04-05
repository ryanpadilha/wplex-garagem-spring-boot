package br.com.wplex.controller.impl;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.wplex.controller.GarageController;
import br.com.wplex.model.entity.Garage;
import br.com.wplex.service.CompanyService;
import br.com.wplex.service.GarageService;

/**
 * The Garage WEB-MVC Controller.
 * 
 * @author Ryan Padilha <ryan.padilha@wplex.com.br>
 * @since 0.1
 *
 */
@Controller
@RequestMapping(value = "/garage")
public class GarageControllerImpl implements GarageController {

	@Autowired
	private GarageService service;

	@Autowired
	private CompanyService companyService;

	@Override
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("GarageForm");

		modelAndView.addObject(new Garage());
		modelAndView.addObject("companies", companyService.list());
		return modelAndView;
	}

	@Override
	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("GarageList");

		List<Garage> garages = service.list();
		modelAndView.addObject("garages", garages);

		return modelAndView;
	}

	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView get(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("CompanyList");

		Garage garage = service.get(id);
		modelAndView.addObject("garage", garage);

		return modelAndView;
	}

	@Override
	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	public ModelAndView create(@Valid Garage garage, BindingResult result) {
		final String htmlview;

		if (result.hasErrors()) {
			htmlview = "GarageForm";
		} else {
			service.insert(garage);
			htmlview = "redirect:/garage/";
		}

		final ModelAndView modelAndView = new ModelAndView(htmlview);
		return modelAndView;
	}

	@Override
	public ModelAndView update(Long id, Garage garage) {
		throw new NotYetImplementedException();
	}

	@Override
	public ModelAndView delete(Long id) {
		throw new NotYetImplementedException();
	}

}
