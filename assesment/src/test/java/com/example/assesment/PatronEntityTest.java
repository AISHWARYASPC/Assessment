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

import com.example.assesment.model.PatronEntity;
import com.example.assesment.repository.PatronRepository;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatronEntityTest {
	
	@Autowired
	PatronRepository patronRepository;
	
	@Test
	@Order(1)
	@Rollback(value=false)
	public void savePatronTest() {
	PatronEntity patronEntity = PatronEntity.builder()
			                .address("Dubai")
			                .email("xyz@gmail.com")
			                .phoneNumber("0000000000")
			                .name("xyz")
			                .build();
	
	patronRepository.save(patronEntity);
	Assertions.assertThat(patronEntity.getPatronId()).isGreaterThan(0);
	}
	
	@Test
	@Order(2)
	@Rollback(value=false)
	public void getPatronByIdTest() {
		
		PatronEntity patronEntity= 	patronRepository.findById(1L).get();
		
		Assertions.assertThat(patronEntity.getPatronId()).isEqualTo(1L);
		
	}
	
	@Test
	@Order(3)
	@Rollback(value=false)
	public void getAllPatrons() {
		
		List<PatronEntity> patronEntity= 	patronRepository.findAll();
		
		Assertions.assertThat(patronEntity.size()).isGreaterThan(0);
		
	}
	
	@Test
	@Order(4)
	@Rollback(value=false)
	public void updatePatrons() {
		
		PatronEntity patronEntity= 	patronRepository.findById(1l).get();
		patronEntity.setName("abc");
		patronRepository.save(patronEntity);
		Assertions.assertThat(patronEntity.getName()).isEqualTo("abc");
		
	}
	
	@Test
	@Order(5)
	@Rollback(value=false)
	public void deletePatron() {
		
		PatronEntity patronEntity= 	patronRepository.findById(1L).get();
		patronRepository.delete(patronEntity);
		PatronEntity patronEntity1=null;
		Optional<PatronEntity> patronEntity2= 	patronRepository.findById(1L);
		if(patronEntity2.isPresent())
			patronEntity1=patronEntity2.get();
		Assertions.assertThat(patronEntity1).isNull();
		
	}


}
