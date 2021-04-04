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

import ca.mcgill.ecse321.projectgroup04.service.OwnerService;


@CrossOrigin(origins = "*")
@RestController
public class OwnerController {

	@Autowired
	private OwnerService ownerService;


    public OwnerDto convertToDto(Owner owner) {
		if (owner == null) {
			throw new IllegalArgumentException("There is no such Owner!");
		}
		OwnerDto ownerDto = new OwnerDto(owner.getUserId(), owner.getPassword());
		ownerDto.setId(owner.getId());
		return ownerDto;
	}

    @GetMapping(value = { "/owner", "/owner/" })
	public List<OwnerDto> getOwner() { // will return only one owner either ways
		List<OwnerDto> ownerDtos = new ArrayList<>();
		for (Owner owner : ownerService.getOwner()) {
			ownerDtos.add(convertToDto(owner));
		}

		return ownerDtos;
	}

	@GetMapping(value = { "/owner/{Id}", "/owner/{Id}/" })
	public OwnerDto getOwnerById(@PathVariable("Id") Long Id) throws IllegalArgumentException {
		return convertToDto(ownerService.getOwnerByUserId(Id));
	}

	@PostMapping(value = { "/register/owner/{userId}", "/register/owner/{userId}/" })
	public ResponseEntity<?> createOwner(@PathVariable("userId") String userId, @RequestParam String password)
			{
		Owner owner = null;
		try {
			owner = ownerService.createOwner(userId, password);
		} catch(IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(convertToDto(owner), HttpStatus.CREATED);
	}

	@DeleteMapping(value = { "/delete/owner/{Id}", "/delete/owner/{Id}/" })
	public void deleteOwner(@PathVariable("Id") Long Id) throws IllegalArgumentException {
		Owner owner = ownerService.getOwnerByUserId(Id);
		ownerService.deleteOwner(owner);
	}

	@PatchMapping(value = { "/edit/owner/{Id}", "/edit/owner/{Id}/" })
	public void editOwner(@PathVariable("Id") Long Id, @RequestParam String userId, @RequestParam String password)
			throws IllegalArgumentException {
		Owner owner = ownerService.getOwnerByUserId(Id);
		ownerService.editOwner(owner, userId, password);
	}
}