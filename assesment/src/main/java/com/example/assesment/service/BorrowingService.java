package com.example.assesment.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.assesment.Exception.ResouceNotFoundException;
import com.example.assesment.model.BookEntity;
import com.example.assesment.model.BorrowingEntity;
import com.example.assesment.model.PatronEntity;
import com.example.assesment.repository.BookRepository;
import com.example.assesment.repository.BorrowingRepository;
import com.example.assesment.repository.PatronRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BorrowingService {
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	PatronRepository patronRepository;
	
	@Autowired
	BorrowingRepository borrowingRepository;
 
	
	public ResponseEntity<BorrowingEntity> addDetails(Long bookId, Long patronId) throws ResouceNotFoundException {
		
		BookEntity book = bookRepository.findById(bookId).orElseThrow( ()-> new ResouceNotFoundException("This book ID does not exist"));
		
		PatronEntity patron = patronRepository.findById(patronId).orElseThrow( ()-> new ResouceNotFoundException("This Patron ID does not exist"));
		
		
		
		
		BorrowingEntity borrowingEntity = new BorrowingEntity();
		
		borrowingEntity.setBook(book);
		borrowingEntity.setPatron(patron);
		borrowingEntity.setBookId(bookId);
     	borrowingEntity.setPatronId(patronId);
		borrowingEntity.setBorrowedDate(LocalDate.now());
		
		borrowingRepository.save(borrowingEntity);
		log.info("Borrowing details saved succesfully");
		return new ResponseEntity<>(borrowingEntity, HttpStatus.OK);
	}

	public ResponseEntity<BorrowingEntity> updateDetails(Long bookId, Long patronId) throws ResouceNotFoundException {
	
		BorrowingEntity borrowingEntity = borrowingRepository.findByBookIdAndPatronId(bookId, patronId).orElseThrow(()->  new ResouceNotFoundException("This book ID and patron ID combination does not exist"));
		borrowingEntity.setReturnDate(LocalDate.now());
		borrowingRepository.save(borrowingEntity);
		log.info("Borrowing details updated succesfully");
		return new ResponseEntity<>(borrowingEntity, HttpStatus.OK);
		
	}
	
	

}
