package br.com.builders.apiclients.repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.builders.apiclients.controller.form.AddressForm;
import br.com.builders.apiclients.controller.form.ClientForm;
import br.com.builders.apiclients.model.Client;
import br.com.builders.apiclients.model.Gender;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ClientRepositoryTest {

	@Autowired
	private ClientRepository clientRepository;
	
	@Test
	@DisplayName("should remove all clients, register clients and search for all registered clients")
	public void testRegistrationClients() {
		clientRepository.deleteAll();
		
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
		Client client2 = new Client(clientForm);
		clientRepository.save(client);
		clientRepository.save(client2);
		List<Client> clients = Arrays.asList(client, client2);
		List<Client> clientsFounded = clientRepository.findAll();
		Assertions.assertThat(clientsFounded).isNotNull();
		Assertions.assertThat(clientsFounded).isEqualTo(clients);
	}

}
