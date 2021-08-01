package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
}
