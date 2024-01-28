package com.example.assesment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.assesment.model.PatronEntity;


@Repository
public interface PatronRepository extends JpaRepository<PatronEntity, Long> {

}
