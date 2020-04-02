package by.itacademy.dzhivushko.cars.web.controller;

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

import by.itacademy.dzhivushko.cars.dao.api.entity.table.IBrand;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IEngine;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IModel;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IModelInfo;
import by.itacademy.dzhivushko.cars.dao.api.filter.ModelFilter;
import by.itacademy.dzhivushko.cars.service.IBrandService;
import by.itacademy.dzhivushko.cars.service.IEngineService;
import by.itacademy.dzhivushko.cars.service.IModelService;
import by.itacademy.dzhivushko.cars.web.converter.ModelFromDTOConverter;
import by.itacademy.dzhivushko.cars.web.converter.ModelToDTOConverter;
import by.itacademy.dzhivushko.cars.web.dto.ModelDTO;
import by.itacademy.dzhivushko.cars.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/model")
public class ModelController extends AbstractController {
    @Autowired
    private IEngineService engineService;

    @Autowired
    private IModelService modelService;

    @Autowired
    private IBrandService brandService;

    @Autowired
    private ModelFromDTOConverter fromDtoConverter;

    @Autowired
    private ModelToDTOConverter toDtoConverter;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(final HttpServletRequest req,
            @RequestParam(name = "page", required = false) final Integer pageNumber,
            @RequestParam(name = "sort", required = false) final String sortColumn) {

        final GridStateDTO gridState = getListDTO(req);
        gridState.setPage(pageNumber);
        gridState.setSort(sortColumn, "id");

        final ModelFilter filter = new ModelFilter();
        prepareFilter(gridState, filter);
        gridState.setTotalCount(modelService.getCount(filter));

        filter.setFetchBrand(true);
        final List<IModel> entities = modelService.find(filter);
        List<ModelDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

        final Map<String, Object> models = new HashMap<>();
        models.put("gridItems", dtos);
        return new ModelAndView("model.list", models);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView showForm() {
        final Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("formModel", new ModelDTO());
        loadCommonFormModels(hashMap);
        return new ModelAndView("model.edit", hashMap);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object save(@Valid @ModelAttribute("formModel") final ModelDTO formModel, final BindingResult result) {
        if (result.hasErrors()) {
            final Map<String, Object> hashMap = new HashMap<>();
            hashMap.put("formModel", formModel);
            loadCommonFormModels(hashMap);
            return new ModelAndView("model.edit", hashMap);
        } else {
            final IModel entity = fromDtoConverter.apply(formModel);
            final IModelInfo modelInfo = entity.getModelInfo();
            modelService.save(entity, modelInfo);
            return "redirect:/model";
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
        final IModel dbModel = modelService.getFullInfo(id);
        final ModelDTO dto = toDtoConverter.apply(dbModel);
        final Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("formModel", dto);
        hashMap.put("readonly", true);
        loadCommonFormModels(hashMap);
        return new ModelAndView("model.edit", hashMap);
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
        final ModelDTO dto = toDtoConverter.apply(modelService.getFullInfo(id));

        final Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("formModel", dto);
        loadCommonFormModels(hashMap);
        return new ModelAndView("model.edit", hashMap);
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable(name = "id", required = true) final Integer id) {
        modelService.delete(id);
        return "redirect:/model";
    }

    private void loadCommonFormModels(final Map<String, Object> hashMap) {
        final List<IBrand> brands = brandService.getAll();

        /*
         * final Map<Integer, String> brandsMap = new HashMap<>(); for (final
         * IBrand iBrand : brands) { brandsMap.put(iBrand.getId(),
         * iBrand.getName()); }
         */

        final Map<Integer, String> brandsMap = brands.stream()
                .collect(Collectors.toMap(IBrand::getId, IBrand::getName));
        hashMap.put("brandsChoices", brandsMap);

        final Map<Integer, String> enginesMap = engineService.getAll().stream()
                .collect(Collectors.toMap(IEngine::getId, IEngine::getTitle));
        hashMap.put("engineChoices", enginesMap);
    }

}
