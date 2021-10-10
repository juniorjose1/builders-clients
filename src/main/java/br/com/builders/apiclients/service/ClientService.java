package br.com.builders.apiclients.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.builders.apiclients.controller.dto.ClientDetailDto;
import br.com.builders.apiclients.controller.dto.ClientDto;
import br.com.builders.apiclients.controller.form.ClientForm;
import br.com.builders.apiclients.model.Client;
import br.com.builders.apiclients.repository.ClientRepository;
import br.com.builders.apiclients.specification.ClientSpecification;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	public Page<ClientDto> findAll(Pageable page){
		Page<Client> clients = repository.findAll(page);
		
		return ClientDto.toDto(clients);
	}
	
	public ClientDetailDto findById(Long id) {
		Optional<Client> client = repository.findById(id);
		return new ClientDetailDto(client.get());
	}

	public Page<ClientDto> findByAttribute(String attribute, String value, Pageable page) {
		Page<Client> clients = repository.findAll(Specification.where(ClientSpecification.findByAttribute(attribute, value)),page);
		
		return ClientDto.toDto(clients);
	}
	
	public Client save(ClientForm clientForm) {
		Client client = new Client(clientForm);
		
		return repository.save(client);
	}
	
	/*public ClientDetail findById(Long id) {
		clientRepository.findById(id);
	}
	
	public Client save(ClientForm form) {
		Client client = form.toClient();
		return clientRepository.save(client);
	}
	
	public void update(Long id, ClientForm form) {
		
	}
	
	public void deleteById(Long id) {
		clientRepository.deleteById(id);
	}*/
	
	

}
