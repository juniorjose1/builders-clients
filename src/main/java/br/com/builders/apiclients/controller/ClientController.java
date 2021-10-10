package br.com.builders.apiclients.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.builders.apiclients.controller.dto.ClientDetailDto;
import br.com.builders.apiclients.controller.dto.ClientDto;
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

}
