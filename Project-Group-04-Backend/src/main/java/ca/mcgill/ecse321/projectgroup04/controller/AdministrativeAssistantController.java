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
import ca.mcgill.ecse321.projectgroup04.service.AdministrativeAssistantService;


@CrossOrigin(origins = "*")
@RestController
public class AdministrativeAssistantController {
    @Autowired
	private AdministrativeAssistantService adminService;


    public AdministrativeAssistantDto convertToDto(AdministrativeAssistant administrativeAssistant) {
		if (administrativeAssistant == null) {
			throw new IllegalArgumentException("There is no such AdministrativeAssistant!");
		}
		AdministrativeAssistantDto administrativeAssistantDto = new AdministrativeAssistantDto(
				administrativeAssistant.getUserId(), administrativeAssistant.getPassword());
		administrativeAssistantDto.setId(administrativeAssistant.getId());
		return administrativeAssistantDto;
	}

    @GetMapping(value = { "/administrativeAssistants", "/administrativeAssistants/" })
	public List<AdministrativeAssistantDto> getAdministrativeAssistants() {
		List<AdministrativeAssistantDto> administrativeAssistantDtos = new ArrayList<>();
		for (AdministrativeAssistant admnistrativeAssistant : adminService.getAllAdministrativeAssistants()) {
			administrativeAssistantDtos.add(convertToDto(admnistrativeAssistant));
		}
		return administrativeAssistantDtos;
	}

	@GetMapping(value = { "/administrativeAssistant/{Id}", "/administrativeAssistant/{Id}/" })
	public AdministrativeAssistantDto getAdministrativeAssistantById(@PathVariable("Id") Long Id)
			throws IllegalArgumentException {
		return convertToDto(adminService.getAdministrativeAssistantById(Id));
	}

	@PostMapping(value = { "/register/administrativeAssistant/{userId}",
			"/register/administrativeAssistant/{userId}/" })
	public ResponseEntity<?> createAdministrativeAssistant(@PathVariable("userId") String userId,
			@RequestParam String password) {
		AdministrativeAssistant administrativeAssistant = null;
		try {
			administrativeAssistant = adminService.createAdministrativeAssistant(userId, password);
		} catch(IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(convertToDto(administrativeAssistant), HttpStatus.CREATED);
	}

    @DeleteMapping(value = { "/delete/administrativeAssistant/{Id}", "/delete/administrativeAssistant/{Id}/" })
	public void deleteAdministrativeAssistant(@PathVariable("Id") Long Id) throws IllegalArgumentException {
		AdministrativeAssistant administrativeAssistant = adminService.getAdministrativeAssistantById(Id);
		adminService.deleteAdministrativeAssistant(administrativeAssistant);
	}

	@PatchMapping(value = { "/edit/administrativeAssistant/{Id}", "/edit/administrativeAssistants/{Id}/" })
	public void editAdministariveAssistant(@PathVariable("Id") Long Id, @RequestParam String userId,
			@RequestParam String password) throws IllegalArgumentException {
		AdministrativeAssistant administrativeAssistant = adminService.getAdministrativeAssistantById(Id);
		adminService.editAdministrativeAssistant(administrativeAssistant, userId, password);
	}

}
