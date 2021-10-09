package br.com.builders.apiclients.controller.dto;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.data.domain.Page;

import br.com.builders.apiclients.model.Client;
import br.com.builders.apiclients.model.Gender;

public class ClientDto {

	private Long id;
	private String name;
	private Integer age;
	private Gender gender;
	private AddressDto addressDto;
	
	public ClientDto(Client client) {
		this.id = client.getId();
		this.name = client.getName();
		this.age = this.convertToAge(client.getBirthDate());
		this.gender = client.getGender();
		this.addressDto = new AddressDto(client.getAddress().getCountry(), client.getAddress().getCity());
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

	public AddressDto getAddressDto() {
		return addressDto;
	}
	
	private Integer convertToAge(LocalDate birthDate) {
		LocalDate now = LocalDate.now();
		Period period = Period.between(birthDate, now);
		return period.getYears();
	}
	
	public static Page<ClientDto> toDto(Page<Client> client) {
		return client.map(ClientDto::new);
	}

}
