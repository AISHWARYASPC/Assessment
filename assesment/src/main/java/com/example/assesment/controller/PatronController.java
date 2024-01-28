package com.example.assesment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.assesment.Exception.ResouceNotFoundException;
import com.example.assesment.model.PatronEntity;
import com.example.assesment.service.PatronService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PatronController {

	@Autowired
	PatronService patronService;

	@GetMapping("/patrons")
	public ResponseEntity<List<PatronEntity>> getPatrons() {

		return patronService.getAllPatrons();

	}

	@GetMapping("/patrons/{id}")
	public ResponseEntity<PatronEntity> getPatrons(@PathVariable Long id) throws ResouceNotFoundException {

		return patronService.getPatronsById(id);

	}

	@PostMapping("/patrons")
	public ResponseEntity<PatronEntity> addPatrons(@Valid @RequestBody PatronEntity patron) {

		return patronService.addPatrons(patron);
	}

	@PutMapping("/patrons/{id}")
	public ResponseEntity<PatronEntity> updatePatrons(@PathVariable("id") Long id, @Valid @RequestBody PatronEntity patron) throws ResouceNotFoundException {

		return patronService.updatePatrons(id, patron);
	}

	@DeleteMapping("/patrons/{id}")
	public ResponseEntity<?> deletePatrons(@PathVariable("id") Long id) {

		return patronService.deletePatron(id);
	}

}
