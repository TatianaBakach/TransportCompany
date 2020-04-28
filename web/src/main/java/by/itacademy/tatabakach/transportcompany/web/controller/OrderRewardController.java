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

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderReward;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderRewardPercent;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.OrderRewardFilter;
import by.itacademy.tatabakach.transportcompany.service.IEmployeeService;
import by.itacademy.tatabakach.transportcompany.service.IOrderRewardPercentService;
import by.itacademy.tatabakach.transportcompany.service.IOrderRewardService;
import by.itacademy.tatabakach.transportcompany.service.IOrderService;
import by.itacademy.tatabakach.transportcompany.web.converter.OrderRewardFromDTOConverter;
import by.itacademy.tatabakach.transportcompany.web.converter.OrderRewardToDTOConverter;
import by.itacademy.tatabakach.transportcompany.web.dto.OrderRewardDTO;
import by.itacademy.tatabakach.transportcompany.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/orderReward")
public class OrderRewardController extends AbstractController {

	@Autowired
	private IOrderRewardService orderRewardService;
	
	@Autowired
	private IOrderService orderService;

	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IOrderRewardPercentService orderRewardPercentService;

	@Autowired
	private OrderRewardToDTOConverter toDtoConverter;

	@Autowired
	private OrderRewardFromDTOConverter fromDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final OrderRewardFilter filter = new OrderRewardFilter();
		filter.setFetchOrder(true);
		filter.setFetchEmployee(true);
		filter.setFetchOrderRewardRercent(true);
		prepareFilter(gridState, filter);

		final List<IOrderReward> entities = orderRewardService.find(filter);
		List<OrderRewardDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(orderRewardService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("orderReward.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IOrderReward newEntity = orderRewardService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));

		loadCommonFormModels(hashMap);

		return new ModelAndView("orderReward.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final OrderRewardDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			loadCommonFormModels(hashMap);

			return new ModelAndView("orderReward.edit", hashMap);
		} else {
			final IOrderReward entity = fromDtoConverter.apply(formModel);
			orderRewardService.save(entity);
			return "redirect:/orderReward";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		orderRewardService.delete(id);
		return "redirect:/orderReward";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IOrderReward dbModel = orderRewardService.getFullInfo(id);
		final OrderRewardDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		loadCommonFormModels(hashMap);

		return new ModelAndView("orderReward.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final OrderRewardDTO dto = toDtoConverter.apply(orderRewardService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		loadCommonFormModels(hashMap);

		return new ModelAndView("orderReward.edit", hashMap);
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		
		final List<IOrder> orders = orderService.getAll();
		Map<Integer, String> ordersMap = new HashMap<Integer, String>();
		for (IOrder iOrder : orders) {
			ordersMap.put(iOrder.getId(), iOrder.getNumber());
		}
		hashMap.put("ordersChoices", ordersMap);

		final List<IEmployee> employees = employeeService.getAll();
		Map<Integer, String> employeesMap = new HashMap<Integer, String>();
		for (IEmployee iEmployee : employees) {
			employeesMap.put(iEmployee.getId(), String.format("%s %s",iEmployee.getLastName(), iEmployee.getFirstName()));
		}
		hashMap.put("employeesChoices", employeesMap);
		
		final List<IOrderRewardPercent> orderRewardPercents = orderRewardPercentService.getAll();
		Map<Integer, String> orderRewardPercentsMap = new HashMap<Integer, String>();
		for (IOrderRewardPercent iOrderRewardPercent : orderRewardPercents) {
			orderRewardPercentsMap.put(iOrderRewardPercent.getId(), iOrderRewardPercent.getName());
		}
		hashMap.put("orderRewardPercentsChoices", orderRewardPercentsMap);

	}

}
