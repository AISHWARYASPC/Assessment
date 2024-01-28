package com.example.assesment.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Book")
public class BookEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long bookId;
	
    @NotBlank(message = "Title should not be blank")
	private String title;
	
    
    @NotBlank(message = "Author should not be blank")
	private String author;
	
    @NotBlank(message = "Publication should not be blank")
	private String publication ;
	
    @Digits(integer = 4, fraction = 0, message = "Year must be exactly 4 digits long")
    @Min(value = 1000, message = "Year must be at least 1000")
	private int year;
	
    @NotNull(message = "ISBN should not be blank")
	private Long ISBN;
	
	

}
