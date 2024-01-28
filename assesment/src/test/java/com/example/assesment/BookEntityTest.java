package com.example.assesment;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.assesment.model.BookEntity;
import com.example.assesment.repository.BookRepository;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookEntityTest {
	
	
	@Autowired
	BookRepository bookRepository;
	
	@Test
	@Order(1)
	@Rollback(value=false)
	public void saveBookTest() {
	BookEntity bookEntity = BookEntity.builder()
			                .author("JK Rowling")
			                .ISBN((long) 123456789)
			                .title("Harry Potter")
			                .publication("xyz")
			                .year(2000)
			                .build();
	
	bookRepository.save(bookEntity);
	Assertions.assertThat(bookEntity.getBookId()).isGreaterThan(0);
	}
	
	
	@Test
	@Order(2)
	@Rollback(value=false)
	public void getBookByIdTest() {
		
		BookEntity bookEntity= 	bookRepository.findById(1L).get();
		
		Assertions.assertThat(bookEntity.getBookId()).isEqualTo(1L);
		
	}
	
	@Test
	@Order(3)
	@Rollback(value=false)
	public void getAllBooks() {
		
		List<BookEntity> bookEntity= 	bookRepository.findAll();
		
		Assertions.assertThat(bookEntity.size()).isGreaterThan(0);
		
	}
	
	@Test
	@Order(4)
	@Rollback(value=false)
	public void updateBooks() {
		
		BookEntity bookEntity= 	bookRepository.findById(1l).get();
		bookEntity.setPublication("abc");
		bookRepository.save(bookEntity);
		Assertions.assertThat(bookEntity.getPublication()).isEqualTo("abc");
		
	}
	
	@Test
	@Order(5)
	@Rollback(value=false)
	public void deleteBooks() {
		
		BookEntity bookEntity= 	bookRepository.findById(1L).get();
		bookRepository.delete(bookEntity);
		BookEntity bookEntity1=null;
		Optional<BookEntity> bookEntity2= 	bookRepository.findById(1L);
		if(bookEntity2.isPresent())
			bookEntity1=bookEntity2.get();
		Assertions.assertThat(bookEntity1).isNull();
		
	}

}
