package br.com.wplex.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.wplex.controller.ApiDefinitionController;

/**
 * The API Definition WEB-MVC Controller.
 * 
 * @author Ryan Padilha <ryan.padilha@wplex.com.br>
 * @since 0.1
 *
 */
@Controller
@RequestMapping(value = "/api-definition")
public class ApiDefinitionControllerImpl implements ApiDefinitionController {

	@Override
	@RequestMapping(value = "/v1", method = RequestMethod.GET)
	public ModelAndView definitionVersion1() {
		new ModelAndView("");
		return null;
	}

}
