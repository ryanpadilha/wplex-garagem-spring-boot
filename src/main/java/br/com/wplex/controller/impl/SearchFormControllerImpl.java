package br.com.wplex.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.wplex.model.entity.Company;
import br.com.wplex.model.entity.Garage;
import br.com.wplex.service.CompanyService;
import br.com.wplex.service.GarageService;

/**
 * The SearchForm WEB-MVC Controller.
 * 
 * @author Ryan Padilha <ryan.padilha@wplex.com.br>
 * @since 0.1
 *
 */
@Controller
@RequestMapping(value = "/search")
public class SearchFormControllerImpl {

	@Autowired
	private CompanyService service;

	@Autowired
	private GarageService garageService;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("SearchForm");

		List<Company> companies = service.list();
		modelAndView.addObject(new Garage());
		modelAndView.addObject("companies", companies);

		return modelAndView;
	}

	@RequestMapping(value = "/garages", method = RequestMethod.POST)
	public ModelAndView process(Garage garage) {
		ModelAndView modelAndView = new ModelAndView("SearchForm");

		List<Company> companies = service.list();
		modelAndView.addObject("companies", companies);

		List<Garage> garages = garageService.findByCompanyId(garage.getCompany().getId());
		modelAndView.addObject("garages", garages);

		return modelAndView;
	}
}
