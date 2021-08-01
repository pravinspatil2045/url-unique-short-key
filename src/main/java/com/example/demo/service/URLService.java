package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.URLRepository;

@Service
public class URLService {

	URLRepository aURLRepository;

	@Autowired
	public URLService(URLRepository aURLRepository) {
		this.aURLRepository = aURLRepository;
	}

	public String getUniqueShortKey(String url) {
		
		System.out.println("URLService : getUniqueShortKey() : url :" + url);
		
		return aURLRepository.findByURL(url);
	}

}
