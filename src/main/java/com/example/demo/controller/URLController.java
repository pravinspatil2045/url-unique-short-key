package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.UniqueShortKeyNotFoundException;
import com.example.demo.service.URLService;

@RestController
public class URLController {

	private URLService aURLService;
	
	@Autowired
	public URLController(URLService aURLService) {
		this.aURLService = aURLService;
	}
	
	@GetMapping("/get")
	public String getUniqueShortKey(@RequestParam("url") String url) {
		
		System.out.println("URLController : getUniqueShortKey() : url :" + url);
		
		return aURLService.getUniqueShortKey(url);
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private void UniqueShortKeyNotFoundHandler(UniqueShortKeyNotFoundException ex) {
		System.out.println("Entering and leaving URLController : UniqueShortKeyNotFoundHandler");
	}
	
}
