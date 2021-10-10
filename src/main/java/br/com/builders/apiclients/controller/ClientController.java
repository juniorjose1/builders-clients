package br.com.builders.apiclients.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Page<ClientDto>> findAll(Pageable page){
		return ResponseEntity.ok(service.findAll(page));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClientDetailDto> findById(@PathVariable Long id){
		return ResponseEntity.ok(service.findById(id));
	}
	
	@GetMapping("/search")
	public ResponseEntity<Page<ClientDto>> findByAttribute(@RequestParam(required = true) String attribute, 
											@RequestParam(required = true) String value, Pageable page){
		return ResponseEntity.ok(service.findByAttribute(attribute, value, page));
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ClientDetailDto> save(@RequestBody @Valid ClientForm clientForm, UriComponentsBuilder builder){
		Client client = service.save(clientForm);
		URI uri = builder.path("/clients/{id}").buildAndExpand(client.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new ClientDetailDto(client));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> update(@RequestBody @Valid ClientFormUpdate clientFormUpdate, @PathVariable Long id){
		service.update(id, clientFormUpdate);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		service.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}

}
