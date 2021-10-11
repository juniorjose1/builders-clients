package br.com.builders.apiclients.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.builders.apiclients.controller.dto.ClientDetailDto;
import br.com.builders.apiclients.controller.dto.ClientDto;
import br.com.builders.apiclients.controller.form.ClientForm;
import br.com.builders.apiclients.controller.form.ClientFormUpdate;
import br.com.builders.apiclients.model.Client;
import br.com.builders.apiclients.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientService service;
	
	@GetMapping
	@Cacheable(value = "allClients")
	public ResponseEntity<Page<ClientDto>> findAll(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable page){
		return ResponseEntity.ok(service.findAll(page));
	}
	
	@GetMapping("/{id}")
	@Cacheable(value = "detailClient")
	public ResponseEntity<ClientDetailDto> findById(@PathVariable Long id){
		return ResponseEntity.ok(service.findById(id));
	}
	
	@GetMapping("/search")
	@Cacheable(value = "searchClient")
	public ResponseEntity<Page<ClientDto>> findByAttribute(@RequestParam(required = true) String attribute, 
											@RequestParam(required = true) String value, Pageable page){
		return ResponseEntity.ok(service.findByAttribute(attribute, value, page));
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "allClients", allEntries = true)
	public ResponseEntity<ClientDetailDto> save(@RequestBody @Valid ClientForm clientForm, UriComponentsBuilder builder){
		Client client = service.save(clientForm);
		URI uri = builder.path("/clients/{id}").buildAndExpand(client.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new ClientDetailDto(client));
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = {"allClients", "searchClient", "detailClient"}, allEntries = true)
	public ResponseEntity<?> update(@RequestBody @Valid ClientFormUpdate clientFormUpdate, @PathVariable Long id){
		service.update(id, clientFormUpdate);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = {"allClients", "searchClient", "detailClient"}, allEntries = true)
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		service.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}

}
