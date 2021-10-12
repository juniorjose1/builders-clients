package br.com.builders.apiclients.controller;

import java.net.URI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import br.com.builders.apiclients.model.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private TestEntityManager em;
	
	@BeforeEach
	public void createTestUserBD() {
		User user = new User();
		
		user.setLogin("test");
		user.setPassword("$2a$10$bYWP9dfI/RXXStnuLmoqCOEzAX1aJ7nGAEQiD71yoSvOFYPTxsItC");
		
		em.persist(user);
	}
	
	@Test
	@DisplayName("should return status code 400 if authentication data is invalid")
	public void testFailAuthentication() throws Exception {
		URI uri = new URI("/auth");
		String json = "{\"login\":\"testFalse\",\"password\":\"123456\"}";
		
		mockMvc
			.perform(MockMvcRequestBuilders
					.post(uri)
					.content(json)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers
					.status()
					.is(400));
	}
	
	@Test
	@DisplayName("should return status code 200 if authentication data is correct")
	public void testSuccessAuthentication() throws Exception {
		URI uri = new URI("/auth");
		String json = "{\"login\":\"test\",\"password\":\"123456\"}";
		
		mockMvc
			.perform(MockMvcRequestBuilders
					.post(uri)
					.content(json)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers
					.status()
					.is(200));
	}

}
