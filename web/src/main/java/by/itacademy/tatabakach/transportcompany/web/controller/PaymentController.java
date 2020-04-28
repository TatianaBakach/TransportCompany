package by.itacademy.tatabakach.transportcompany.web.controller;

import java.util.Arrays;
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

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Currency;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IPayment;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.PaymentFilter;
import by.itacademy.tatabakach.transportcompany.service.ICompanyService;
import by.itacademy.tatabakach.transportcompany.service.IOrderService;
import by.itacademy.tatabakach.transportcompany.service.IPaymentService;
import by.itacademy.tatabakach.transportcompany.web.converter.PaymentFromDTOConverter;
import by.itacademy.tatabakach.transportcompany.web.converter.PaymentToDTOConverter;
import by.itacademy.tatabakach.transportcompany.web.dto.PaymentDTO;
import by.itacademy.tatabakach.transportcompany.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/payment")
public class PaymentController extends AbstractController {
	
	@Autowired
	private IPaymentService paymentService;
	
	@Autowired
	private IOrderService orderService;

	@Autowired
	private ICompanyService companyService;

	@Autowired
	private PaymentToDTOConverter toDtoConverter;
	
	@Autowired
	private PaymentFromDTOConverter fromDtoConverter;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req, 
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final PaymentFilter filter = new PaymentFilter();
		filter.setFetchOrder(true);
		filter.setFetchCompany(true);
		prepareFilter(gridState, filter);

		final List<IPayment> entities = paymentService.find(filter);
		List<PaymentDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(paymentService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("payment.list", models);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IPayment newEntity = paymentService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));
		loadCommonFormModels(hashMap);

		return new ModelAndView("payment.edit", hashMap);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final PaymentDTO formModel, final BindingResult result) {
		
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			loadCommonFormModels(hashMap);

			return new ModelAndView("payment.edit", hashMap);
		} else {
			final IPayment entity = fromDtoConverter.apply(formModel);
			paymentService.save(entity);
			return "redirect:/payment";
		}
	}
	
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		paymentService.delete(id);
		return "redirect:/payment";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IPayment dbModel = paymentService.getFullInfo(id);
		final PaymentDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		
		loadCommonFormModels(hashMap);

		return new ModelAndView("payment.edit", hashMap);
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final PaymentDTO dto = toDtoConverter.apply(paymentService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		
		loadCommonFormModels(hashMap);

		return new ModelAndView("payment.edit", hashMap);
	}
	
	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		
		final List<Currency> currencyTypesList = Arrays.asList(Currency.values());
        final Map<String, String> currencyTypesMap = currencyTypesList.stream()
                .collect(Collectors.toMap(Currency::name, Currency::name));

        hashMap.put("currencyChoices", currencyTypesMap);
		
		final List<IOrder> orders = orderService.getAll();
		Map<Integer, String> ordersMap = new HashMap<Integer, String>();
		for (IOrder iOrder : orders) {
			ordersMap.put(iOrder.getId(), iOrder.getNumber());
		}
		hashMap.put("ordersChoices", ordersMap);
		
		final List<ICompany> companies = companyService.getAll();
		Map<Integer, String> companiesMap = new HashMap<Integer, String>();
		for (ICompany iCompany : companies) {
			companiesMap.put(iCompany.getId(), iCompany.getName());
		}
		hashMap.put("companiesChoices", companiesMap);

	}

}