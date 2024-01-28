package com.example.assesment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.assesment.model.BorrowingEntity;

@Repository
public interface BorrowingRepository extends JpaRepository<BorrowingEntity, Long> {
	
	
	 @Query(value = "SELECT * FROM Borrowing b WHERE b.book_id = :bookId AND b.patron_id = :patronId", nativeQuery = true)
	Optional<BorrowingEntity> findByBookIdAndPatronId(@Param("bookId") Long bookId, @Param("patronId") Long patronId);

	//Optional<BorrowingEntity> findByBookIDAndPatronId(Long bookId, Long patronId);

}
