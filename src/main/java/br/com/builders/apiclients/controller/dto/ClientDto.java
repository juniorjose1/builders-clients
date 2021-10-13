package br.com.builders.apiclients.controller.dto;

import org.springframework.data.domain.Page;

import br.com.builders.apiclients.model.Client;
import br.com.builders.apiclients.model.Gender;
import br.com.builders.apiclients.util.ClientUtils;

public class ClientDto {

	private Long id;
	private String name;
	private Integer age;
	private Gender gender;
	private AddressDto address = new AddressDto();
	
	public ClientDto(Client client) {
		this.id = client.getId();
		this.name = client.getName();
		this.age = ClientUtils.convertToAge(client.getBirthDate());
		this.gender = client.getGender();
		this.address = new AddressDto(client.getAddress().getCountry(), client.getAddress().getCity());
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}

	public Gender getGender() {
		return gender;
	}

	public AddressDto getAddress() {
		return address;
	}
	
	public static Page<ClientDto> toDto(Page<Client> client) {
		return client.map(ClientDto::new);
	}

}
