package br.com.wplex.controller.impl;

import java.util.List;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.wplex.controller.CompanyController;
import br.com.wplex.model.entity.Company;
import br.com.wplex.service.CompanyService;

/**
 * The Company WEB-MVC Controller.
 * 
 * @author Ryan Padilha <ryan.padilha@wplex.com.br>
 * @since 0.1
 *
 */
@Controller
@RequestMapping(value = "/company")
public class CompanyControllerImpl implements CompanyController {

	@Autowired
	private CompanyService service;

	@Override
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("CompanyForm");
		modelAndView.addObject(new Company());
		return modelAndView;
	}

	@Override
	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("CompanyList");

		List<Company> companies = service.list();
		modelAndView.addObject("companies", companies);

		return modelAndView;
	}

	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView get(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("CompanyList");

		Company company = service.get(id);
		modelAndView.addObject("company", company);

		return modelAndView;
	}

	@Override
	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	public ModelAndView create(Company company) {
		ModelAndView modelAndView = new ModelAndView("redirect:/company/");

		service.insert(company);
		return modelAndView;
	}

	@Override
	public ModelAndView update(Long id, Company company) {
		throw new NotYetImplementedException();
	}

	@Override
	public ModelAndView delete(Long id) {
		throw new NotYetImplementedException();
	}

}
