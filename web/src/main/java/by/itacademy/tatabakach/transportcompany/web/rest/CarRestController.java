package by.itacademy.tatabakach.transportcompany.web.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICar;
import by.itacademy.tatabakach.transportcompany.service.ICarService;
import by.itacademy.tatabakach.transportcompany.web.converter.CarFromDTOConverter;
import by.itacademy.tatabakach.transportcompany.web.converter.CarToDTOConverter;
import by.itacademy.tatabakach.transportcompany.web.dto.CarDTO;

@RestController
public class CarRestController extends AbstractRestController {

	@Autowired
	private ICarService service;

	@Autowired
	private CarToDTOConverter toDtoConverter;

	@Autowired
	private CarFromDTOConverter fromDtoConverter;

	@GetMapping("/cars")
	public List<CarDTO> get() {
		return service.getAll().stream().map(toDtoConverter).collect(Collectors.toList());
	}

	@PostMapping("/cars")
	public ResponseEntity<CarDTO> post(@RequestBody CarDTO newBrandDto) {
		ICar newCar = fromDtoConverter.apply(newBrandDto);
		service.save(newCar);
		return new ResponseEntity<CarDTO>(toDtoConverter.apply(newCar), HttpStatus.CREATED);
	}

	@PutMapping("/cars/{id}")
	public ResponseEntity<Void> put(@PathVariable Integer id, @RequestBody CarDTO updatedCarDto) {
		updatedCarDto.setId(id);
		ICar newCar = fromDtoConverter.apply(updatedCarDto);
		service.save(newCar);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/cars/{id}")
	public ResponseEntity<CarDTO> getById(@PathVariable Integer id) {
		ICar car = service.get(id);

		if (car == null) {
			return new ResponseEntity<CarDTO>(HttpStatus.NOT_FOUND);
		}

		CarDTO dto = toDtoConverter.apply(car);

		return new ResponseEntity<CarDTO>(dto, HttpStatus.OK);
	}

	@DeleteMapping("/cars/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
