package by.itacademy.tatabakach.transportcompany.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CompanyFilter;
import by.itacademy.tatabakach.transportcompany.service.ICompanyService;
import by.itacademy.tatabakach.transportcompany.web.converter.CompanyToDTOConverter;
import by.itacademy.tatabakach.transportcompany.web.dto.CompanyDTO;

@Controller
@RequestMapping(value = "/company")
public class CompanyController {
	
	@Autowired
	private ICompanyService companyService;

	@Autowired
	private CompanyToDTOConverter toDtoConverter;
	

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req) {

		final CompanyFilter filter = new CompanyFilter();

		final List<ICompany> entities = companyService.find(filter);
		List<CompanyDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("company.list", models);
	}

}
