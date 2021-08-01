package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.URL;

@Repository
public interface URLRepository extends JpaRepository<URL, Long> {

	public URL findByAddress(String string);

}
