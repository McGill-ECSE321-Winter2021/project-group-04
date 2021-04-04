package ca.mcgill.ecse321.projectgroup04.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup04.dto.*;
import ca.mcgill.ecse321.projectgroup04.model.*;
import ca.mcgill.ecse321.projectgroup04.service.FieldTechnicianService;


@CrossOrigin(origins = "*")
@RestController
public class FieldTechnicianController {

	@Autowired
	private FieldTechnicianService fieldService;


    private FieldTechnicianDto convertToDto(FieldTechnician fieldTechnician) {
		if (fieldTechnician == null) {
			throw new IllegalArgumentException("There is no such Field Technician!");
		}

		FieldTechnicianDto fieldTechnicianDto = new FieldTechnicianDto(fieldTechnician.getName(),
				fieldTechnician.getIsAvailable());
		fieldTechnicianDto.setId(fieldTechnician.getTechnicianId());
		return fieldTechnicianDto;

	}

    @GetMapping(value = { "/fieldTechnician", "/fieldTechnician/" })
	public List<FieldTechnicianDto> getFieldTechnicians() {
		List<FieldTechnicianDto> fieldTechnicianDtos = new ArrayList<>();
		for (FieldTechnician fieldTechnician : fieldService.getAllFieldTechnicians()) {
			fieldTechnicianDtos.add(convertToDto(fieldTechnician));
		}

		return fieldTechnicianDtos;
	}

	@GetMapping(value = { "/fieldTechnician/{Id}", "/fieldTechnician/{Id}/" })
	public FieldTechnicianDto getFieldTechnicianById(@PathVariable("Id") Long Id) {
		return convertToDto(fieldService.getFieldTechnicianById(Id));
	}

	@PostMapping(value = { "/register/fieldTechnician/{name}", "/register/fieldTechnician/{name}/" })
	public ResponseEntity<?> createFieldTechnician(@PathVariable("name") String name) throws IllegalArgumentException {
		FieldTechnician fieldTechnician = null;
		try{
		 fieldTechnician = fieldService.createFieldTechnician(name);
			}

			catch(IllegalArgumentException e){
				return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<>(convertToDto(fieldTechnician),HttpStatus.CREATED);
		
	}

	@DeleteMapping(value = { "/delete/fieldTechnician/{Id}", "/delete/fieldTechnician/{Id}/" })
	public ResponseEntity<?> deleteFieldTechnician(@PathVariable("Id") Long id) throws IllegalArgumentException {
		FieldTechnician fieldTechnician = null;
		try{
			fieldTechnician = fieldService.getFieldTechnicianById(id);
		
			fieldService.deleteFieldTechnician(fieldTechnician);
		}
		catch(IllegalArgumentException e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PatchMapping(value = { "/edit/fieldTechnician/{Id}", "/edit/fieldTechnician/{Id}/" })
	public ResponseEntity<?> editFieldTechnician(@PathVariable("Id") Long id, @RequestParam String name)
			throws IllegalArgumentException {
				FieldTechnician fieldTechnician = null;
		try{
			fieldTechnician = fieldService.getFieldTechnicianById(id);
		fieldService.editFieldTechnician(fieldTechnician, name);
		}
		catch(IllegalArgumentException e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new  ResponseEntity<>(convertToDto(fieldTechnician),HttpStatus.CREATED);
	}
	@GetMapping(value = { "/fieldTechnicians/{name}", "/fieldTechnicians/{name}/" })
	public FieldTechnicianDto getFieldTechnicianIdByName(@PathVariable("name") String name) {

		FieldTechnician fieldTechnician = fieldService.getFieldTechnicianByName(name);
		if (fieldTechnician == null) {
			throw new IllegalArgumentException("No such field technician exists!");
		}
		return convertToDto(fieldTechnician);
	}
}