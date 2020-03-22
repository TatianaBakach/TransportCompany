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

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITransactionCost;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.TransactionCostFilter;
import by.itacademy.tatabakach.transportcompany.service.ITransactionCostService;
import by.itacademy.tatabakach.transportcompany.web.converter.TransactionCostFromDTOConverter;
import by.itacademy.tatabakach.transportcompany.web.converter.TransactionCostToDTOConverter;
import by.itacademy.tatabakach.transportcompany.web.dto.TransactionCostDTO;
import by.itacademy.tatabakach.transportcompany.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/transactionCost")
public class TransactionCostController extends AbstractController {
	
	@Autowired
	private ITransactionCostService transactionCostService;

	@Autowired
	private TransactionCostToDTOConverter toDtoConverter;
	
	@Autowired
	private TransactionCostFromDTOConverter fromDtoConverter;
	

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req, 
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		
		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final TransactionCostFilter filter = new TransactionCostFilter();
		prepareFilter(gridState, filter);

		final List<ITransactionCost> entities = transactionCostService.find(filter);
		List<TransactionCostDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(transactionCostService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("transactionCost.list", models);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final ITransactionCost newEntity = transactionCostService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));

		return new ModelAndView("transactionCost.edit", hashMap);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final TransactionCostDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "transactionCost.edit";
		} else {
			final ITransactionCost entity = fromDtoConverter.apply(formModel);
			transactionCostService.save(entity);
			return "redirect:/transactionCost";
		}
	}
	
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		transactionCostService.delete(id);
		return "redirect:/transactionCost";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ITransactionCost dbModel = transactionCostService.get(id);
		final TransactionCostDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("transactionCost.edit", hashMap);
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final TransactionCostDTO dto = toDtoConverter.apply(transactionCostService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("transactionCost.edit", hashMap);
	}

}