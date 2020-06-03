package by.itacademy.tatabakach.transportcompany.web.controller;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.itacademy.tatabakach.transportcompany.web.jndi.SMTPCredentials;

/*@Controller
@RequestMapping(value = "/")*/
public class HomePageController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HomePageController.class);

	@Autowired
	private SMTPCredentials smtpCredentials;

	@PostConstruct
	private void init() {
		Validate.notEmpty(smtpCredentials.getEmail());
		LOGGER.info("email from custom JNDI resource:{}", smtpCredentials.getEmail());
	}

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		LOGGER.info("HomePageController method has been called");

		return "home";
	}

}