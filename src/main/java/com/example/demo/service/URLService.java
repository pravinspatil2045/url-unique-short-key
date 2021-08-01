package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.UniqueShortKeyNotFoundException;
import com.example.demo.model.URL;
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
		
		URL aURL = aURLRepository.findByAddress(url);
		if(aURL == null) {
			throw new UniqueShortKeyNotFoundException();
		}
		
		return aURL.getUnique_short_key();
	}

}
