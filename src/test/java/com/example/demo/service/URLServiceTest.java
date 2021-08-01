package com.example.demo.service;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.repository.URLRepository;

@RunWith(MockitoJUnitRunner.class)
public class URLServiceTest {
	
	@Mock
	private URLRepository aURLRepository;

	private URLService aURLService;

	@Before
	public void setUp() throws Exception {
		aURLService = new URLService(aURLRepository);
	}
	
	@Test
	public void getUniqueShortKey_returnUniqueShortKey() throws Exception{
		
		given(aURLRepository.findByURL("google.com")).willReturn("");
		
		String shortKey = aURLService.getUniqueShortKey("google.com");
		assertThat(shortKey).isEqualTo("");
	}

}
