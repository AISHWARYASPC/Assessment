package com.example.assesment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.assesment.Exception.ResouceNotFoundException;
import com.example.assesment.model.BorrowingEntity;
import com.example.assesment.service.BorrowingService;

@RestController
@RequestMapping("/api")
public class BorrowingController {
	
	
	@Autowired
	BorrowingService borrowingService;
	
	@PostMapping("/borrow/{bookId}/patron/{patronId}")
	public ResponseEntity<BorrowingEntity> addBorrowingDetails(@PathVariable Long bookId, @PathVariable Long patronId) throws ResouceNotFoundException{
		return borrowingService.addDetails(bookId,patronId);
	}
	
	
	@PutMapping("/return/{bookId}/patron/{patronId}")
	public ResponseEntity<BorrowingEntity> updateBorrowingDetails(@PathVariable Long bookId, @PathVariable Long patronId) throws ResouceNotFoundException{
		return borrowingService.updateDetails(bookId,patronId);
	}

}
