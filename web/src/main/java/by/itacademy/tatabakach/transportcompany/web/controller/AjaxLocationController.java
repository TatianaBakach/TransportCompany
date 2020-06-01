package by.itacademy.tatabakach.transportcompany.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICountry;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ILocality;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRegion;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CountryFilter;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.LocalityFilter;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.RegionFilter;
import by.itacademy.tatabakach.transportcompany.service.ICountryService;
import by.itacademy.tatabakach.transportcompany.service.ILocalityService;
import by.itacademy.tatabakach.transportcompany.service.IRegionService;
import by.itacademy.tatabakach.transportcompany.web.dto.AddressDTO1;
import by.itacademy.tatabakach.transportcompany.web.dto.LocationDTO;

@Controller
@RequestMapping(value = "/ajax-samples")
public class AjaxLocationController {

	@Autowired
	private ICountryService countryService;

	@Autowired
	private IRegionService regionService;

	@Autowired
	private ILocalityService localityService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AjaxLocationController.class);

	@RequestMapping(value = "/countries", method = RequestMethod.GET)
	public ResponseEntity<List<LocationDTO>> getCountries() {
		final CountryFilter filter = new CountryFilter();
		final List<ICountry> entities = countryService.find(filter);
		List<LocationDTO> countries = entities.stream().map((entity) -> {
			return new LocationDTO(entity.getId(), entity.getName());
		}).collect(Collectors.toList());
		return new ResponseEntity<List<LocationDTO>>(countries, HttpStatus.OK);
	}

	@RequestMapping(value = "/regions", method = RequestMethod.GET)
	public ResponseEntity<List<LocationDTO>> getRegions(
			@RequestParam(name = "countryId", required = true) final Integer countryId) {
		final RegionFilter filter = new RegionFilter();
		filter.setCountryId(countryId);
		final List<IRegion> entities = regionService.find(filter);
		List<LocationDTO> regions = entities.stream().map((entity) -> {
			return new LocationDTO(entity.getId(), entity.getName());
		}).collect(Collectors.toList());
		return new ResponseEntity<List<LocationDTO>>(regions, HttpStatus.OK);
	}

	@RequestMapping(value = "/cities", method = RequestMethod.GET)
	public ResponseEntity<List<LocationDTO>> getCities(
			@RequestParam(name = "regionId", required = true) final Integer regionId) {
		final LocalityFilter filter = new LocalityFilter();
		filter.setRegionId(regionId);
		final List<ILocality> entities = localityService.find(filter);
		List<LocationDTO> cities = entities.stream().map((entity) -> {
			return new LocationDTO(entity.getId(), entity.getName());
		}).collect(Collectors.toList());
		return new ResponseEntity<List<LocationDTO>>(cities, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("addressForm") final AddressDTO1 formModel, final BindingResult result) {
		LOGGER.info("FORM RECEIVED: {}", formModel);
		return "redirect:/ajax-samples";
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showPage() {
		return new ModelAndView("ajax-samples", "addressForm", new AddressDTO1());
	}
}