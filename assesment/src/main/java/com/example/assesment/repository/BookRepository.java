package com.example.assesment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.assesment.model.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

}
