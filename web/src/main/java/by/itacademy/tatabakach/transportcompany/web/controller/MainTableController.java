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

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.OrderFilter;
import by.itacademy.tatabakach.transportcompany.service.ICompanyService;
import by.itacademy.tatabakach.transportcompany.service.IOrderService;
import by.itacademy.tatabakach.transportcompany.web.converter.OrderFromDTOConverter;
import by.itacademy.tatabakach.transportcompany.web.converter.OrderToDTOConverter;
import by.itacademy.tatabakach.transportcompany.web.dto.OrderDTO;
import by.itacademy.tatabakach.transportcompany.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/")
public class MainTableController extends AbstractController {

	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private IOrderService orderService;

	@Autowired
	private OrderToDTOConverter toDtoConverter;

	@Autowired
	private OrderFromDTOConverter fromDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final OrderFilter filter = new OrderFilter();
		filter.setFetchOurCompany(true);
		prepareFilter(gridState, filter);

		final List<IOrder> entities = orderService.find(filter);
		List<OrderDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(orderService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("order.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IOrder newEntity = orderService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));

		loadCommonFormModels(hashMap);

		return new ModelAndView("order.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final OrderDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			loadCommonFormModels(hashMap);

			return new ModelAndView("order.edit", hashMap);
		} else {
			final IOrder entity = fromDtoConverter.apply(formModel);
			orderService.save(entity);
			return "redirect:/order";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		orderService.delete(id);
		return "redirect:/order";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IOrder dbModel = orderService.getFullInfo(id);
		final OrderDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		loadCommonFormModels(hashMap);

		return new ModelAndView("order.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final OrderDTO dto = toDtoConverter.apply(orderService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		loadCommonFormModels(hashMap);

		return new ModelAndView("order.edit", hashMap);
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		
		final List<ICompany> companies = companyService.getAll();
		Map<Integer, String> companiesMap = new HashMap<Integer, String>();
		for (ICompany iCompany : companies) {
			companiesMap.put(iCompany.getId(), iCompany.getName());
		}
		hashMap.put("companiesChoices", companiesMap);
		
	}

}
