package com.example.assesment;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.example.assesment.model.BookEntity;
import com.example.assesment.model.BorrowingEntity;
import com.example.assesment.model.PatronEntity;
import com.example.assesment.repository.BorrowingRepository;

@DataJpaTest
public class BorrowingEntityTest {
	BookEntity bookEntity=null;
	PatronEntity patronEntity=null;
	
	@Autowired
	BorrowingRepository borrowingRepository;
	
	@BeforeEach
	public void setUp() {
		
		bookEntity = new BookEntity();
		bookEntity.setBookId(1L);
		bookEntity.setAuthor("JK Rowling");
		bookEntity.setISBN((long) 123456789);
		bookEntity.setTitle("Harry Potter");
		bookEntity.setPublication("xyz");
		bookEntity.setYear(2000);
		
		patronEntity = new PatronEntity();
		patronEntity.setAddress("Dubai");
		patronEntity.setEmail("xyz@gmail.com");
		patronEntity.setPhoneNumber("0000000000");
		patronEntity.setName("xyz");
		patronEntity.setPatronId(1L);
	}
	
	@Test
	public void addBorrowingDetails() {
		
        BorrowingEntity borrowingEntity = new BorrowingEntity();
		
		borrowingEntity.setBook(bookEntity);
		borrowingEntity.setPatron(patronEntity);
		borrowingEntity.setBookId(bookEntity.getBookId());
     	borrowingEntity.setPatronId(patronEntity.getPatronId());
		borrowingEntity.setBorrowedDate(LocalDate.now());
		
		borrowingRepository.save(borrowingEntity);
		
		Assertions.assertThat(borrowingEntity.getId()).isGreaterThan(0);
	}
	
	
	
	@AfterEach
	public void tearDown() {
		bookEntity=null;
		patronEntity=null;
		
	}
}
