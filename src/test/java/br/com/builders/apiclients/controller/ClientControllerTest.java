package br.com.builders.apiclients.controller;

import java.time.LocalDate;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.builders.apiclients.controller.dto.ClientDetailDto;
import br.com.builders.apiclients.controller.form.AddressForm;
import br.com.builders.apiclients.controller.form.ClientForm;
import br.com.builders.apiclients.model.Client;
import br.com.builders.apiclients.model.Gender;
import br.com.builders.apiclients.repository.ClientRepository;
import br.com.builders.apiclients.service.ClientService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {
	
	@MockBean
	private ClientService clientService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private ClientRepository clientRepository;

	@Test
	@DisplayName("should return status code 200 and bring a client by id")
	public void testGetClientId() throws Exception {
		
		AddressForm addressForm = new AddressForm("São José dos Campos", "Manoel Fernades",
								25,
								"Vila Sul",
								"Brasil",
								"12341-901");
		
		ClientForm clientForm = new ClientForm("Beatriz","410.984.203-12",
								LocalDate.of(1995, 05, 16),
								Gender.FEMALE,
								addressForm);
		
		Client client = new Client(clientForm);
	    
		Mockito.when(clientService.findById(1L)).thenReturn(new ClientDetailDto(client));
		
		mockMvc
			.perform(MockMvcRequestBuilders
					.get("/clients/1"))
			.andExpect(MockMvcResultMatchers
					.status().is(200))
			.andExpect(MockMvcResultMatchers
					.jsonPath("name", Matchers.is("Beatriz")));
		
	}
}
