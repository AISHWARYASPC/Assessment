package com.example.assesment.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.assesment.Exception.ResouceNotFoundException;
import com.example.assesment.model.BookEntity;
import com.example.assesment.repository.BookRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;

	@Cacheable("books")
	public List<BookEntity> getAllBooks() {

		return bookRepository.findAll();
	}
   
	@Cacheable("bookById")
	public ResponseEntity<BookEntity> getBooksById(Long id) throws ResouceNotFoundException{
		
		BookEntity bookEntity = bookRepository.findById(id).orElseThrow(()-> new ResouceNotFoundException("This book ID does not exist"));	
		log.info("Book with given ID fetched succesfully");
			return new ResponseEntity<>(bookEntity, HttpStatus.OK);
		
	}

	public ResponseEntity<BookEntity> addBooks(BookEntity book) {

		try {
			BookEntity bookEntity = bookRepository.save(book);
			log.info("Given book data saved succesfully");
			return new ResponseEntity<>(bookEntity, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Error occurred while adding new book data", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<BookEntity> updateBooks(Long id, BookEntity book) throws ResouceNotFoundException{
		
			BookEntity updateEnity = bookRepository.findById(id).orElseThrow( ()-> new ResouceNotFoundException("This book ID does not exist"));
			log.info("The book with given ID fetched succesfully");
				//BookEntity updateEnity = new BookEntity();
				updateEnity.setAuthor(book.getAuthor());
				updateEnity.setTitle(book.getTitle());
				updateEnity.setYear(book.getYear());
				updateEnity.setISBN(book.getISBN());
				updateEnity.setPublication(book.getPublication());

				bookRepository.save(updateEnity);
				log.info("The book with given ID updated succesfully");
				return new ResponseEntity<>(updateEnity, HttpStatus.OK);
			
	}

	public ResponseEntity<?> deleteBook(Long id) {
		bookRepository.deleteById(id);
		log.info("Book with given ID deleted succesfully");
		return new ResponseEntity<>("Data deleted",HttpStatus.OK);
	}

}
