package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.UniqueShortKeyNotFoundException;
import com.example.demo.model.URL;
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
	
	
	@GetMapping("/storeurl")
    ResponseEntity<URL> storeurl(@RequestParam("url") String url) {
		
		System.out.println("URLController : getUniqueShortKey() : url :" + url);
        return new ResponseEntity<>(aURLService.storeurl(url), HttpStatus.CREATED);
    }
	
	@GetMapping("/count")
	public int getCount(@RequestParam("url") String url) {
		
		System.out.println("URLController : getCount() : url :" + url);
		
		return aURLService.getCount(url);
	}
	
	@GetMapping("/list")
	public List<URL> getAll(@RequestParam("page") String page, @RequestParam("size") String size) {
		
		System.out.println("URLController : getAll() : page :" + page + " size : "+ size);
		
		return aURLService.getAll(page, size);
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private void UniqueShortKeyNotFoundHandler(UniqueShortKeyNotFoundException ex) {
		System.out.println("Entering and leaving URLController : UniqueShortKeyNotFoundHandler");
	}
	
}
