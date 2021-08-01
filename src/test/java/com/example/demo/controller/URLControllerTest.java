package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.exception.UniqueShortKeyNotFoundException;
import com.example.demo.model.URL;
import com.example.demo.service.URLService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(URLController.class)
public class URLControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	URLService aURLService;
	
	@Test
	public void getUniqueShortKey_returnUniqueShortKey() throws Exception{
		
		given(aURLService.getUniqueShortKey(anyString())).willReturn("abc");
		
		mockMvc.perform(MockMvcRequestBuilders.get("/get?url=google.com")).andExpect(status().isOk());
	}
	
	@Test
	public void getUniqueShortKey_notFound() throws Exception {
		given(aURLService.getUniqueShortKey(anyString()))
				.willThrow(new UniqueShortKeyNotFoundException());

		mockMvc.perform(MockMvcRequestBuilders.get("/get?url=yahoo.com"))
				.andExpect(status().isNotFound());

	}
	
	@Test
	public void storeurl_shouldSaveURL() throws Exception{
		
		URL newURL = new URL(101L, "yahoo.com", "xyz", 0);
		when(aURLService.storeurl(anyString())).thenReturn(newURL);
		
		ObjectMapper objectMapper = new ObjectMapper();
        String urlJSON = objectMapper.writeValueAsString(newURL);
        
        ResultActions result = mockMvc.perform(post("/storeurl?url=yahoo.com")
                .contentType(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.address").value("yahoo.com"))
                .andExpect(jsonPath("$.unique_short_key").value("xyz"));
	}

}
