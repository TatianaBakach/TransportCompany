package by.itacademy.tatabakach.transportcompany.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRouteItem;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.AddressFilter;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.RouteItemFilter;
import by.itacademy.tatabakach.transportcompany.service.IAddressService;
import by.itacademy.tatabakach.transportcompany.service.IOrderService;
import by.itacademy.tatabakach.transportcompany.service.IRouteItemService;
import by.itacademy.tatabakach.transportcompany.web.converter.RouteItemFromDTOConverter;
import by.itacademy.tatabakach.transportcompany.web.converter.RouteItemToDTOConverter;
import by.itacademy.tatabakach.transportcompany.web.dto.RouteItemDTO;
import by.itacademy.tatabakach.transportcompany.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/routeItem")
public class RouteItemController extends AbstractController {
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IAddressService addressService;
	
	@Autowired
	private IRouteItemService routeItemService;

	@Autowired
	private RouteItemToDTOConverter toDtoConverter;
	
	@Autowired
	private RouteItemFromDTOConverter fromDtoConverter;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req, 
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final RouteItemFilter filter = new RouteItemFilter();
		filter.setFetchOrder(true);
		filter.setFetchAddress(true);
		filter.setFetchCustom(true);
		prepareFilter(gridState, filter);

		final List<IRouteItem> entities = routeItemService.find(filter);
		List<RouteItemDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(routeItemService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("routeItem.list", models);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IRouteItem newEntity = routeItemService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));
		loadCommonFormModels(hashMap);

		return new ModelAndView("routeItem.edit", hashMap);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final RouteItemDTO formModel, final BindingResult result) {
		
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			loadCommonFormModels(hashMap);

			return new ModelAndView("routeItem.edit", hashMap);
		} else {
			final IRouteItem entity = fromDtoConverter.apply(formModel);
			routeItemService.save(entity);
			return "redirect:/routeItem";
		}
	}
	
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		routeItemService.delete(id);
		return "redirect:/routeItem";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IRouteItem dbModel = routeItemService.getFullInfo(id);
		final RouteItemDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		
		loadCommonFormModels(hashMap);

		return new ModelAndView("routeItem.edit", hashMap);
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final RouteItemDTO dto = toDtoConverter.apply(routeItemService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		
		loadCommonFormModels(hashMap);

		return new ModelAndView("routeItem.edit", hashMap);
	}
	
	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		
		final List<IOrder> orders = orderService.getAll();
		Map<Integer, String> ordersMap = new HashMap<Integer, String>();
		for (IOrder iOrder : orders) {
			ordersMap.put(iOrder.getId(), iOrder.getNumber());
		}
		hashMap.put("ordersChoices", ordersMap);
		
		final AddressFilter filter = new AddressFilter();
		filter.setFetchLocality(true);
		final List<IAddress> adresses = addressService.find(filter);
		Map<Integer, String> addressesMap = new HashMap<Integer, String>();
		for (IAddress iAddress : adresses) {
			addressesMap.put(iAddress.getId(), String.format("%s %s %s", iAddress.getPostcode(), iAddress.getLocality().getName(), iAddress.getExactAddress()));
		}
		hashMap.put("addressesChoices", addressesMap);
	}

}