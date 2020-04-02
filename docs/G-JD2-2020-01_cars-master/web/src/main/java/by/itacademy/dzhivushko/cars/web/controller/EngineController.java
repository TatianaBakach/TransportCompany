package by.itacademy.dzhivushko.cars.web.controller;

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

import by.itacademy.dzhivushko.cars.dao.api.entity.enums.EngineType;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IEngine;
import by.itacademy.dzhivushko.cars.dao.api.filter.EngineFilter;
import by.itacademy.dzhivushko.cars.service.IEngineService;
import by.itacademy.dzhivushko.cars.web.converter.EngineFromDTOConverter;
import by.itacademy.dzhivushko.cars.web.converter.EngineToDTOConverter;
import by.itacademy.dzhivushko.cars.web.dto.EngineDTO;
import by.itacademy.dzhivushko.cars.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/engine")
public class EngineController extends AbstractController {

    @Autowired
    private IEngineService engineService;
    @Autowired
    private EngineFromDTOConverter fromDtoConverter;

    @Autowired
    private EngineToDTOConverter toDtoConverter;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(final HttpServletRequest req,
            @RequestParam(name = "page", required = false) final Integer pageNumber,
            @RequestParam(name = "sort", required = false) final String sortColumn) {

        final GridStateDTO gridState = getListDTO(req);
        gridState.setPage(pageNumber);
        gridState.setSort(sortColumn, "id");

        EngineFilter filter = new EngineFilter();
        prepareFilter(gridState, filter);

        final List<IEngine> entities = engineService.find(filter);
        List<EngineDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
        gridState.setTotalCount(engineService.getCount(filter));

        final HashMap<String, Object> models = new HashMap<>();
        models.put("gridItems", dtos);
        return new ModelAndView("engine.list", models);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView showForm() {
        final Map<String, Object> hashMap = new HashMap<>();
        final EngineDTO dto = new EngineDTO();
        dto.setVolume(1200); // set default minimum volume for engine
        hashMap.put("formModel", dto);
        loadComboboxesModels(hashMap);
        return new ModelAndView("engine.edit", hashMap);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object save(@Valid @ModelAttribute("formModel") final EngineDTO formModel, final BindingResult result) {
        if (result.hasErrors()) {
            Map<String, Object> hashMap = new HashMap<>();
            loadComboboxesModels(hashMap);
            return new ModelAndView("engine.edit", hashMap) ;
        } else {
            final IEngine entity = fromDtoConverter.apply(formModel);
            engineService.save(entity);
            return "redirect:/engine";
        }
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable(name = "id", required = true) final Integer id) {
        engineService.delete(id);
        return "redirect:/engine";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
        final IEngine dbModel = engineService.get(id);
        final EngineDTO dto = toDtoConverter.apply(dbModel);
        final Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("formModel", dto);
        hashMap.put("readonly", true);
        loadComboboxesModels(hashMap);
        return new ModelAndView("engine.edit", hashMap);
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
        final EngineDTO dto = toDtoConverter.apply(engineService.get(id));

        final Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("formModel", dto);
        loadComboboxesModels(hashMap);
        return new ModelAndView("engine.edit", hashMap);
    }

    private void loadComboboxesModels(final Map<String, Object> hashMap) {

        final List<EngineType> engineTypesList = Arrays.asList(EngineType.values());
        final Map<String, String> engineTypesMap = engineTypesList.stream()
                .collect(Collectors.toMap(EngineType::name, EngineType::name));

        hashMap.put("typeChoices", engineTypesMap);

    }

}
