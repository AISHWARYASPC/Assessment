package com.example.assesment.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.example.assesment.model.BookEntity;
import com.example.assesment.service.BookService;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api")
public class BookController {
	
	
	@Autowired
	BookService bookService;
	
	@GetMapping("/books")
	public ResponseEntity<List<BookEntity>> getBooks(){
		
		try {
		List<BookEntity> bookList =bookService.getAllBooks();
		
		if(bookList.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			return ResponseEntity.ok(bookList);
		}
		catch(Exception e) {
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/books/{id}")
	public ResponseEntity<BookEntity> getBooks(@PathVariable Long id) throws ResouceNotFoundException {
		
		return bookService.getBooksById(id);
		
		
	}
	
	
	@PostMapping("/books")
	public ResponseEntity<BookEntity> addBooks(@Valid @RequestBody BookEntity book){
		
		return bookService.addBooks(book);
	}
	
	@PutMapping("/books/{id}")
     public ResponseEntity<BookEntity> updateBooks(@PathVariable("id") Long id,@Valid @RequestBody BookEntity book) throws ResouceNotFoundException{
		
		return bookService.updateBooks(id,book);
	}
	
	@DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBooks(@PathVariable("id") Long id){
		
		return bookService.deleteBook(id);
	}

}
