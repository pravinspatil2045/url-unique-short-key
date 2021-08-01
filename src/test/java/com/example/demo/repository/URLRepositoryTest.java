package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.URL;

@RunWith(SpringRunner.class)
@DataJpaTest
public class URLRepositoryTest {
	
	@Autowired
	private URLRepository aURLRepository;

	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void findByAddress() {
		URL savedURL = entityManager.persistFlushFind(new URL());
		assertThat(savedURL.getUrl_id()).isNotNull().isNotNegative();
		URL url = aURLRepository.findByAddress("google.com");
		assertThat(url.getAddress()).isEqualTo("google.com");
	}


}
