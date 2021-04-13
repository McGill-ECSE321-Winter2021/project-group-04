package ca.mcgill.ecse321.projectgroup04.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup04.dto.*;
import ca.mcgill.ecse321.projectgroup04.model.*;
import ca.mcgill.ecse321.projectgroup04.service.GarageTechnicianService;

@CrossOrigin(origins = "*")
@RestController
public class GarageTechnicianController {

	@Autowired
	private GarageTechnicianService garageService;

	public GarageTechnicianDto convertToDto(GarageTechnician garageTechnician) {
		if (garageTechnician == null) {
			throw new IllegalArgumentException("There is no such GarageTechnician!");
		}
		GarageTechnicianDto garageTechnicianDto = new GarageTechnicianDto(garageTechnician.getName());
		garageTechnicianDto.setTechnicianId(garageTechnician.getTechnicianId());
		return garageTechnicianDto;

	}

	@GetMapping(value = { "/garageTechnicians", "/garageTechnicians/" })
	public List<GarageTechnicianDto> getGarageTechnicians() {
		List<GarageTechnicianDto> garageTechnicianDtos = new ArrayList<>();
		for (GarageTechnician garageTechnician : garageService.getAllGarageTechnicians()) {
			garageTechnicianDtos.add(convertToDto(garageTechnician));
		}

		return garageTechnicianDtos;
	}

	@GetMapping(value = { "/garageTechnician/{Id}", "/garageTechnician/{Id}/" })
	public GarageTechnicianDto getGarageTechnicianById(@PathVariable("Id") Long Id) {
		GarageTechnician garageTechnician = garageService.getGarageTechnicianById(Id);
		return convertToDto(garageTechnician);
	}

	@GetMapping(value = { "/garageTechnicians/{name}", "/garageTechnicians/{name}/" })
	public GarageTechnicianDto getGarageTechnicianIdByName(@PathVariable("name") String name) {

		GarageTechnician garageTechnician = garageService.getGarageTechnicianByName(name);
		if (garageTechnician == null) {
			throw new IllegalArgumentException("No such garage technician exists!");
		}
		return convertToDto(garageTechnician);
	}

	@PostMapping(value = { "/register/garageTechnician/{name}", "/register/garageTechnician/{name}/" })
	public ResponseEntity<?> createGarageTechnician(@PathVariable("name") String name) throws IllegalArgumentException {
		GarageTechnician garageTechnician = null;
		try {
			garageTechnician = garageService.createGarageTechnician(name);

		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(convertToDto(garageTechnician), HttpStatus.CREATED);

	}

	@DeleteMapping(value = { "/delete/garageTechnician/{technicianId}", "/delete/garageTechnician/{technicianId}/" })

	public ResponseEntity<?> deleteGarageTechnician(@PathVariable("technicianId") Long technicianId)
			throws IllegalArgumentException {
		GarageTechnician garageTechnician = null;
		try {
			garageTechnician = garageService.getGarageTechnicianById(technicianId);
			garageService.deleteGarageTechnician(garageTechnician);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);

	}
}