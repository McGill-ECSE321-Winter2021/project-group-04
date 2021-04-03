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
import ca.mcgill.ecse321.projectgroup04.service.BookableServiceService;


@CrossOrigin(origins = "*")
@RestController
public class BookableServiceController {


	@Autowired
	private BookableServiceService bookService;


    public BookableServiceDto convertToDto(BookableService bookableService) {
		if (bookableService == null) {
			throw new IllegalArgumentException("There is no such BookableService!");
		}
		BookableServiceDto bookableServiceDto = new BookableServiceDto(bookableService.getDuration(),
				bookableService.getPrice(), bookableService.getName());
		bookableServiceDto.setId(bookableService.getServiceId());
		return bookableServiceDto;
	}

    @GetMapping(value = { "/bookableServices", "/bookableServices/" })
	public List<BookableServiceDto> getAllBookableServices() {
		List<BookableServiceDto> bookableServiceDtos = new ArrayList<>();
		for (BookableService bookableService : bookService.getAllBookableServices()) {
			bookableServiceDtos.add(convertToDto(bookableService));
		}

		return bookableServiceDtos;
	}

	@GetMapping(value = { "/bookableService/{Id}", "/bookableService/{Id}/" })
	public BookableServiceDto getBookableServiceById(@PathVariable("Id") Long Id) {
		BookableService bookableService = bookService.getBookableServiceById(Id);
		if (bookableService == null) {
			throw new IllegalArgumentException("No service with such id!");
		}
		return convertToDto(bookableService);
	}

	@PostMapping(value = { "/create/bookableService/{serviceName}", "/create/bookableService/{serviceName}/" })
	public ResponseEntity<?> createBookableService(@PathVariable("serviceName") String name, @RequestParam int duration,
			@RequestParam int price) {
		BookableService bookableService = null;
		try {
			bookableService = bookService.createBookableService(name, price, duration);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(convertToDto(bookableService), HttpStatus.CREATED);
	}

    @DeleteMapping(value = { "/delete/bookableService/{serviceId}", "/delete/bookableService/{serviceId}/" })
	public ResponseEntity<?> deleteBookableService(@PathVariable("serviceId") Long serviceId)
			throws IllegalArgumentException {
		BookableService bookableService = null;
		try {
			bookableService = bookService.getBookableServiceById(serviceId);
			bookService.deleteBookableService(bookableService);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(convertToDto(bookableService), HttpStatus.CREATED);
	}

	@PatchMapping(value = { "/edit/bookableService/{serviceId}", "/edit/bookableService/{serviceId}/" })
	public ResponseEntity<?> editBookableService(@PathVariable("serviceId") Long serviceId, @RequestParam int duration,
			@RequestParam int price) throws IllegalArgumentException {
		BookableService bookableService = null;
		try {
			bookableService = bookService.getBookableServiceById(serviceId);
			bookService.editBookableService(bookableService, duration, price);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(convertToDto(bookableService), HttpStatus.CREATED);
	}
}
