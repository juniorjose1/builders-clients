package br.com.builders.apiclients.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.builders.apiclients.controller.dto.ClientDto;
import br.com.builders.apiclients.model.Client;
import br.com.builders.apiclients.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	public Page<ClientDto> findAll(Pageable page){
		Page<Client> client = clientRepository.findAll(page);
		
		return ClientDto.toDto(client);
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
