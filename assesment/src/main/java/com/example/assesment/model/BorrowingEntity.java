package com.example.assesment.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Borrowing")
public class BorrowingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@ManyToOne
	@JoinColumn(name = "bookId",insertable=false, updatable=false)
	private BookEntity book;
	
	@ManyToOne
	@JoinColumn(name = "patronId", insertable=false, updatable=false)
	private PatronEntity patron;
	
	
	private LocalDate borrowedDate;
	
	@Column(nullable = true)
	private LocalDate returnDate;
	
	
	@Column(name = "bookId")
	private Long bookId;
	
	@Column(name = "patronId")
	private Long patronId;
	
}
