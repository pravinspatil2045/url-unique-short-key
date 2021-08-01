package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.URL;


public interface URLRepository extends JpaRepository<URL, Long> {

	public String findByURL(String string);

}
