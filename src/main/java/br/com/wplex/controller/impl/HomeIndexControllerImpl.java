package br.com.wplex.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * The HomeIndex WEB-MVC Controller.
 * 
 * @author Ryan Padilha <ryan.padilha@wplex.com.br>
 * @since 0.1
 *
 */
@Controller
@RequestMapping(value = { "", "/" })
public class HomeIndexControllerImpl {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView formIndex() {
		ModelAndView modelAndView = new ModelAndView("HomeIndex");
		return modelAndView;
	}
}
