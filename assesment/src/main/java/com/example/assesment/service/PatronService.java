package com.example.assesment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.assesment.Exception.ResouceNotFoundException;
import com.example.assesment.model.PatronEntity;
import com.example.assesment.repository.PatronRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PatronService {
	
	@Autowired
	PatronRepository patronRepository;

	@Cacheable("patrons")
	public ResponseEntity<List<PatronEntity>> getAllPatrons() {
		try {
			
			List<PatronEntity> patronsList =patronRepository.findAll();
			log.info("The patron details fetched succesfully");
			
			if(patronsList.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			else
				return ResponseEntity.ok(patronsList);
			}
			catch(Exception e) {
				log.error("Error occured while fetching patron details",e.getMessage());
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}

	@Cacheable("patronsById")
	public ResponseEntity<PatronEntity> getPatronsById(Long id) throws ResouceNotFoundException{
			PatronEntity patronEntity = patronRepository.findById(id).orElseThrow( ()-> new ResouceNotFoundException("This Patron ID does not exist"));
			log.info("The patron details with given ID fetched succesfully");
				return new ResponseEntity<>(patronEntity, HttpStatus.OK);
			
	}
	
	
	public ResponseEntity<PatronEntity> addPatrons(PatronEntity patron) {

		try {
			PatronEntity patronEntity = patronRepository.save(patron);
			log.info("The patron details saved succesfully");
			return new ResponseEntity<>(patronEntity, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Error occured while adding new patron details",e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	public ResponseEntity<PatronEntity> updatePatrons(Long id, PatronEntity patron) throws ResouceNotFoundException {
		
			PatronEntity updateEnity = patronRepository.findById(id).orElseThrow( ()-> new ResouceNotFoundException("This Patron ID does not exist"));
			log.info("The patron details fetched succesfully");

				//PatronEntity updateEnity = patronList;
				updateEnity.setAddress(patron.getAddress());
				updateEnity.setEmail(patron.getEmail());
				updateEnity.setName(patron.getName());
				updateEnity.setPhoneNumber(patron.getPhoneNumber());

				patronRepository.save(updateEnity);
				log.info("The patron details updated succesfully");
				return new ResponseEntity<>(updateEnity, HttpStatus.OK);
	}
	
	public ResponseEntity<?> deletePatron(Long id) {
		patronRepository.deleteById(id);
		log.info("The patron details deleted succesfully");
		return new ResponseEntity<>("Data deleted",HttpStatus.OK);
	}

}
