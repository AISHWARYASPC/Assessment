package com.example.assesment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Patron")
public class PatronEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long patronId;
	
	
	@NotBlank(message = "Name should not be blank")
	private String name;
	
	
	private String address;
	
	@Size(min = 10, max = 15, message = "Number should have at least 10 or less than 15 digits")
	private String phoneNumber;
	
	@Email(message = "Email should be of valid format")
	@NotBlank(message = "Email should not be blank")
	private String email;
	

}
