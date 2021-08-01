package com.example.demo.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		if (aURL == null) {
			throw new UniqueShortKeyNotFoundException();
		}
		aURL.setCount(aURL.getCount() + 1);
		aURLRepository.save(aURL);
		return aURL.getUnique_short_key();
	}

	public URL storeurl(String address) {

		System.out.println("URLService : storeurl() : address :" + address);

		URL newURL = new URL();
		newURL.setAddress(address);
		newURL.setUnique_short_key(generateUniqueShortKey(10, address.toCharArray()));
		newURL.setCount(0);
		return aURLRepository.save(newURL);
	}

	public String generateUniqueShortKey(int length, char[] characterSet) {
		StringBuilder sb = new StringBuilder();

		for (int loop = 0; loop < length; loop++) {
			int index = new Random().nextInt(characterSet.length);
			sb.append(characterSet[index]);
		}

		String nonce = sb.toString();
		return nonce;
	}
	
	public int getCount(String url) {

		System.out.println("URLService : getCount() : url :" + url);

		URL aURL = aURLRepository.findByAddress(url);
		if (aURL == null) {
			throw new UniqueShortKeyNotFoundException();
		}
		
		return aURL.getCount();
	}
	
	public List<URL> getAll(String page, String size) {

		System.out.println("URLService : getAll() : page :" + page + " size : "+ size);
		
		Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
		Page<URL> list = aURLRepository.findAll(pageable);
		
		return list.getContent();
	}

}
