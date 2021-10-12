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
import br.com.builders.apiclients.controller.form.ClientFormUpdate;
import br.com.builders.apiclients.exception.ClientNotFoundException;
import br.com.builders.apiclients.exception.ResourceNotFoundException;
import br.com.builders.apiclients.model.Client;
import br.com.builders.apiclients.repository.ClientRepository;
import br.com.builders.apiclients.specification.ClientSpecification;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	public Page<ClientDto> findAll(Pageable page){
		Page<Client> clients = repository.findAll(page);
		if(clients != null) {
			return ClientDto.toDto(clients);
		}
		throw new ResourceNotFoundException();
	}
	
	public ClientDetailDto findById(Long id) {
		Optional<Client> client = repository.findById(id);
		Client clientFounded = client.orElseThrow(() -> new ClientNotFoundException(id));
		return new ClientDetailDto(clientFounded);
	}

	public Page<ClientDto> findByAttribute(String attribute, String value, Pageable page) {
		Page<Client> clients = repository.findAll(Specification.where(ClientSpecification.findByAttribute(attribute, value)),page);
		if(clients.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return ClientDto.toDto(clients);
	}
	
	public Client save(ClientForm clientForm) {
		Client client = new Client(clientForm);
		
		return repository.save(client);
	}
	
	public void update(Long id, ClientFormUpdate clientFormUpdate) {
		Optional<Client> client = repository.findById(id);
		Client clientFounded = client.orElseThrow(() -> new ClientNotFoundException(id)); 
		clientFounded.setName(clientFormUpdate.getName() == null ? clientFounded.getName() : clientFormUpdate.getName());
		clientFounded.setGender(clientFormUpdate.getGender() == null ? clientFounded.getGender() : clientFormUpdate.getGender());
		clientFounded.setCpf(clientFormUpdate.getCpf() == null ? clientFounded.getCpf() : clientFormUpdate.getCpf());
		clientFounded.setBirthDate(clientFormUpdate.getBirthDate() == null ? clientFounded.getBirthDate() : clientFormUpdate.getBirthDate());
		clientFounded.setAddress(clientFormUpdate.getAddress() == null ? clientFounded.getAddress() : clientFormUpdate.getAddress().toAddress());
	}
	
	public void deleteById(Long id) {
		Optional<Client> client = repository.findById(id);
		client.orElseThrow(() -> new ClientNotFoundException(id));
		repository.deleteById(id);
	}

}
