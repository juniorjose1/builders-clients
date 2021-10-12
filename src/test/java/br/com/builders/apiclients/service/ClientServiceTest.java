package br.com.builders.apiclients.service;

import java.time.LocalDate;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.builders.apiclients.controller.dto.ClientDetailDto;
import br.com.builders.apiclients.controller.form.AddressForm;
import br.com.builders.apiclients.controller.form.ClientForm;
import br.com.builders.apiclients.exception.ClientNotFoundException;
import br.com.builders.apiclients.model.Client;
import br.com.builders.apiclients.model.Gender;
import br.com.builders.apiclients.repository.ClientRepository;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

	@InjectMocks
	private ClientService clientService;
	
	@Mock
	private ClientRepository clientRepository;
	
	@Test
	@DisplayName("Should find the client by searching by id")
	public void testFindClientId() {
		AddressForm addressForm = new AddressForm("São José dos Campos", "Manoel Fernades",
				25,
				"Vila Sul",
				"Brasil",
				"12341-901");

		ClientForm clientForm = new ClientForm("Beatriz","410.984.203-12",
				LocalDate.of(1995, 05, 16),
				Gender.FEMALE,
				addressForm);
		
		Optional<Client> client = Optional.of(new Client(clientForm));
		Mockito.when(clientRepository.findById(7L)).thenReturn(client);
		ClientDetailDto clientDetailDto = clientService.findById(7L);
		Assertions.assertThat(clientDetailDto).isEqualTo(new ClientDetailDto(client.get()));
		Mockito.verify(clientRepository, Mockito.times(1)).findById(7L);
	}
	
	@Test
	@DisplayName("should throw exception if you don't find a client")
	public void testExceptionNotFoundClient() {
		Assertions.assertThatExceptionOfType(ClientNotFoundException.class)
								.isThrownBy(() -> clientService.findById(33L));
	}

}
